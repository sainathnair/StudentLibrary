package com.manulife.studentlibrary.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manulife.studentlibrary.Entity.Book;
import com.manulife.studentlibrary.Entity.Student;

@Repository
public interface  BookRepository  extends JpaRepository<Book, Long>{

	boolean existsById(Long Id);
	
}
