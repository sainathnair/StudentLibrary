package com.manulife.studentlibrary.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manulife.studentlibrary.Entity.Student;

@Repository
public interface  StudentRepository  extends JpaRepository<Student, Long>{

	Optional<Student> findByEmail(String email);
    Optional<Student> findByNameOrEmail(String username, String email);
    public Optional<Student> findById(Long Id);
    boolean existsById(Long Id);
    Boolean existsByEmail(String email);
	
}
