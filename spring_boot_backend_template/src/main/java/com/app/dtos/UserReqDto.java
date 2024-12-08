package com.app.dtos;

import com.app.entities.Role;

import lombok.Data;

@Data
public class UserReqDto {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phoneNumber;
	private Role role;
	private int age;
}
