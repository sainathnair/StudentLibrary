package com.manulife.studentlibrary.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manulife.studentlibrary.Entity.Book;
import com.manulife.studentlibrary.Entity.Role;
import com.manulife.studentlibrary.Entity.Roles;
import com.manulife.studentlibrary.Entity.Student;

@Repository
public interface  RoleRepository  extends JpaRepository<Role, Long>{

	boolean existsById(Long Id);
	Optional<Role> findByName(Roles name);
	
}
