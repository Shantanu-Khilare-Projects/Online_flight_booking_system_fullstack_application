package com.app.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.app.dtos.LoginDTO;
import com.app.dtos.SigninResponse;
import com.app.dtos.UserReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.User;
import com.app.security.CustomUserDetails;
import com.app.security.JwtUtils;
import com.app.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*" )
public class UserController {

	private final UserService userService;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private AuthenticationManager authMgr;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody
											  @Valid LoginDTO request) {
		//System.out.println("in sign in" + request);
		//create a token to store un verified user email n pwd
		UsernamePasswordAuthenticationToken token=new
				UsernamePasswordAuthenticationToken(request.getEmail(),
				request.getPassword());
		//invoke auth mgr's authenticate method;
		Authentication verifiedToken = authMgr.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(verifiedToken);
		//=> auth successful !
		//System.out.println(verifiedToken.getPrincipal().getClass());//custom user details object
		CustomUserDetails userDetail= (CustomUserDetails)verifiedToken.getPrincipal();
		User user= userDetail.getUser();
		//create JWT n send it to the clnt in response
		SigninResponse resp=new SigninResponse
				(jwtUtils.generateJwtToken(verifiedToken),
						"Successful Auth!!!!",user);
		return ResponseEntity.
				status(HttpStatus.CREATED).body(resp);
	}

	@PostMapping("/register")
	public ResponseEntity<?> addUser(@RequestBody UserReqDto dto) {
		System.out.println(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(dto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		Optional<User> user = userService.getUserById(id);
		if (user.isPresent()) {
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/admin")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

}
