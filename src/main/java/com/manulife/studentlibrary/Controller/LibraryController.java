package com.manulife.studentlibrary.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manulife.studentlibrary.Entity.Book;
import com.manulife.studentlibrary.Repository.BookRepository;

@RestController
@RequestMapping("/api")
public class LibraryController {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private StudentAuthController authController;

	@GetMapping("/")
	public String library() {
		
		return "Welcome to Student Library";
	}
	@GetMapping("/books/all")
	public List<Book> getAllBooks() {
		
		List<Book> allBooks = bookRepository.findAll();
		
		return allBooks;
	}

}
