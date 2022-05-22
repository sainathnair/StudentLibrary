package com.manulife.studentlibrary.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manulife.studentlibrary.Entity.Book;
import com.manulife.studentlibrary.Entity.Student;
import com.manulife.studentlibrary.Repository.StudentRepository;

@Service
public class StudentService implements UserDetailsService{

@Autowired
private StudentRepository studentRepository;
@Transactional
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  Student student = studentRepository.findByNameOrEmail(username,username)
      .orElseThrow(() -> new UsernameNotFoundException("Student Not Found with name or email: " + username));
  return StudentDetailsImpl.build(student);
}
	

}
