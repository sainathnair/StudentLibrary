package com.manulife.studentlibrary.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manulife.studentlibrary.Dto.RegisterDto;
import com.manulife.studentlibrary.Entity.Student;
import com.manulife.studentlibrary.Repository.BookRepository;
import com.manulife.studentlibrary.Repository.StudentRepository;

@RestController
@RequestMapping("/api/auth")
public class StudentAuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private BookRepository bookRepository;
	
	@PostMapping("/login")
	public ResponseEntity<String> loginStudent(@RequestBody Long Id){
		Authentication authentication = authenticationManager.authenticate
				(new UsernamePasswordAuthenticationToken(Id, Id));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseEntity<>("Login Successful!", HttpStatus.OK);
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
		studentRepository.save(student);
		return new ResponseEntity<>("Student registered successfully", HttpStatus.OK);
		
	}
}
