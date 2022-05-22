package com.manulife.studentlibrary.Controller;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manulife.studentlibrary.Dto.LoginDto;
import com.manulife.studentlibrary.Dto.RegisterDto;
import com.manulife.studentlibrary.Dto.StudentResponse;
import com.manulife.studentlibrary.Entity.Role;
import com.manulife.studentlibrary.Entity.Roles;
import com.manulife.studentlibrary.Entity.Student;
import com.manulife.studentlibrary.Repository.BookRepository;
import com.manulife.studentlibrary.Repository.RoleRepository;
import com.manulife.studentlibrary.Repository.StudentRepository;
import com.manulife.studentlibrary.Security.Jwt.JWTUtils;
import com.manulife.studentlibrary.Service.StudentDetailsImpl;

@RestController
@RequestMapping("/api/auth")
public class StudentAuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	  JWTUtils jwtUtils;
	@Autowired
	  PasswordEncoder encoder;
	
	@PostMapping("/login/{id}")
    public ResponseEntity<?> authenticateUser(@PathVariable Long id){
		Student student = studentRepository.getById(id);
		System.out.println("Student in controller is"+student);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        		student.getName(), student.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        StudentDetailsImpl studentDetailsImpl = (StudentDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(studentDetailsImpl);
        List<String> roles = studentDetailsImpl.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
            .body(new StudentResponse(studentDetailsImpl.getId(),
            		studentDetailsImpl.getUsername(),
            		studentDetailsImpl.getEmail(),
                                       roles));
    }
	
	@PostMapping("/register")
	public ResponseEntity<?> signUpStudent(@RequestBody RegisterDto registerDto){
		
		if(studentRepository.existsById(registerDto.getId())) {
			return new ResponseEntity<>("Student Id already exists in the system", HttpStatus.BAD_REQUEST);
		}
		
		Student student = new Student();
		student.setName(registerDto.getName());
		student.setEmail(registerDto.getEmail());
		student.setPhoneNumber(registerDto.getPhoneNumber());
		student.setPhotoPath(registerDto.getPhotoPath());
		student.setPassword(encoder.encode(registerDto.getPassword()));
		// Create new user's account
		
	    Set<String> strRoles = registerDto.getRole();
	    Set<Role> roles = new HashSet<>();
	    if (strRoles == null) {
	      Role userRole = roleRepository.findByName(Roles.ROLE_USER)
	          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	      roles.add(userRole);
	    } else {
	      strRoles.forEach(role -> {
	        switch (role) {
	        case "admin":
	          Role adminRole = roleRepository.findByName(Roles.ROLE_ADMIN)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(adminRole);
	          break;
	        default:
	          Role userRole = roleRepository.findByName(Roles.ROLE_USER)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(userRole);
	        }
	      });
	    }
	    student.setRoles(roles);
	    studentRepository.save(student);
		return new ResponseEntity<>("Student registered successfully", HttpStatus.OK);
		
	}
}
