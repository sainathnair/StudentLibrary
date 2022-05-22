package com.manulife.studentlibrary.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryController {
	
	@GetMapping("/")
	public String library() {
		
		return "Welcome to Student Library";
	}

}
