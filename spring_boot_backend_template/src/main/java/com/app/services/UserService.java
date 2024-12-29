package com.app.services;

import com.app.dtos.ApiResponse;
import com.app.dtos.LoginDTO;
import com.app.dtos.UserReqDto;
import com.app.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

	User addUser(UserReqDto dto);
	Optional<User> getUserById(Long id);
	List<User> getAllUsers();
	Optional<User> login(LoginDTO loginDTO);
}
