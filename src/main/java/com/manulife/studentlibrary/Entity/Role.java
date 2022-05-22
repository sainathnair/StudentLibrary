package com.manulife.studentlibrary.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

	public Role() {
	  }
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Integer id;
	  @Enumerated(EnumType.STRING)
	  @Column(length = 20)
	  private Roles name;
	  
	  public Role(Roles name) {
	    this.name = name;
	  }
	  public Integer getId() {
	    return id;
	  }
	  public void setId(Integer id) {
	    this.id = id;
	  }
	  public Roles getName() {
	    return name;
	  }
	  public void setName(Roles name) {
	    this.name = name;
	  }
}
