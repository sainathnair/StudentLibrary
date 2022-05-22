package com.manulife.studentlibrary.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manulife.studentlibrary.Entity.Student;

public class StudentDetailsImpl implements UserDetails{

	private static final long serialVersionUID = 1L;
	  private Long id;
	  private String email;
	  @JsonIgnore
	  private String password;
	  private Collection<? extends GrantedAuthority> authorities;
	  public StudentDetailsImpl(Long id,  String email, String password,
	      Collection<? extends GrantedAuthority> authorities) {
	    this.id = id;
	    this.email = email;
	    this.password = password;
	    this.authorities = authorities;
	  }
	  public static StudentDetailsImpl build(Student student) {
	    List<GrantedAuthority> authorities = student.getRoles().stream()
	        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
	        .collect(Collectors.toList());
	    return new StudentDetailsImpl(
	    		student.getId(), 
	    		student.getEmail(),
	    		student.getPassword(), 
	        authorities);
	  }
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	public Long getId() {
	    return id;
	  }
	  public String getEmail() {
	    return email;
	  }
	  @Override
	  public String getPassword() {
	    return password;
	  }
	  @Override
	  public boolean isAccountNonExpired() {
	    return true;
	  }
	  @Override
	  public boolean isAccountNonLocked() {
	    return true;
	  }
	  @Override
	  public boolean isCredentialsNonExpired() {
	    return true;
	  }
	  @Override
	  public boolean isEnabled() {
	    return true;
	  }
	  @Override
	  public boolean equals(Object o) {
	    if (this == o)
	      return true;
	    if (o == null || getClass() != o.getClass())
	      return false;
	    StudentDetailsImpl user = (StudentDetailsImpl) o;
	    return Objects.equals(id, user.id);
	  }
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	}