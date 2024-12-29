package com.app.servicesimpl;

import java.util.List;
import java.util.Optional;

import com.app.daos.UserDao;
import com.app.dtos.UserReqDto;
import com.app.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dtos.LoginDTO;
import com.app.entities.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private final UserDao userRepository;

	public UserServiceImpl(UserDao userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User addUser(UserReqDto dto) {
		User user = modelMapper.map(dto, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public Optional<User> login(LoginDTO loginDTO) {
		Optional<User> user = userRepository.findByEmail(loginDTO.getEmail());
		if (user.isPresent() && user.get().getPassword().equals(loginDTO.getPassword())) {
			return user;
		}
		return Optional.empty();
	}
}
	