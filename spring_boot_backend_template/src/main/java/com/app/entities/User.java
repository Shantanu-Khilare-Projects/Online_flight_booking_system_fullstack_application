package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity{

	@Column(name = "first_name", nullable = false, length = 25)
	private String firstName;
	
	@Column(name = "last_name", nullable = false, length = 25)
	private String lastName;
	
	@Column(name = "email", nullable = false,unique = true, length = 25)
	private String email;
	
	@Column(name = "password", nullable = false, length = 25)
	private String password;
	
	@Column(name = "phone_number", nullable = false, length = 13)
	private String phoneNumber;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private int age;
}
