package com.app.services;

import com.app.dtos.ApiResponse;
import com.app.dtos.LoginDto;
import com.app.dtos.UserReqDto;

public interface UserService {

	ApiResponse register(UserReqDto dto);
	ApiResponse login(LoginDto dto);
}
