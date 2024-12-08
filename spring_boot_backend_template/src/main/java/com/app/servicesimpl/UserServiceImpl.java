package com.app.servicesimpl;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.daos.UserDao;
import com.app.dtos.ApiResponse;
import com.app.dtos.LoginDto;
import com.app.dtos.UserReqDto;
import com.app.entities.User;
import com.app.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserDao userDao;

	@Override
	public ApiResponse register(UserReqDto dto) {
		User user=mapper.map(dto, User.class);
		userDao.save(user);
		return new ApiResponse("user registered successfully!!!");
	}

	@Override
	public ApiResponse login(LoginDto dto) {
		User user=userDao.findByEmailAndPassword(dto.getEmail(), dto.getPassword())
						.orElseThrow(()->new RuntimeException("Invalid credentials..."));
		return new ApiResponse("login success!!!");
	}
	
	

}
