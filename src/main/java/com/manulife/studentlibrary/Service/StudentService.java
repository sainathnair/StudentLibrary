package com.manulife.studentlibrary.Service;

import java.util.Collection;
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

import com.manulife.studentlibrary.Entity.Book;
import com.manulife.studentlibrary.Entity.Student;
import com.manulife.studentlibrary.Repository.StudentRepository;

@Service
public class StudentService implements UserDetailsService{

@Autowired
private StudentRepository studentRepository;
	@Override
	public UserDetails loadUserByUsername(String studentId) throws UsernameNotFoundException {
		Student student = studentRepository.findById(Long.parseLong(studentId)).orElseThrow(() -> 
		new UsernameNotFoundException("Student not found with id"+studentId));
		return new User(student.getName(),student.getId().toString(), mapBookstoStudents(student.getBooks()));
	}
	private Collection< ? extends GrantedAuthority> mapBookstoStudents(Set<Book> books){
        return books.stream().map(book -> new SimpleGrantedAuthority(book.getTitle())).collect(Collectors.toList());
    }

}
