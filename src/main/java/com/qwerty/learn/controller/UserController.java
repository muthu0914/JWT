package com.qwerty.learn.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qwerty.learn.domain.User;
import com.qwerty.learn.dto.ResponseDTO;
import com.qwerty.learn.repository.UserRepository;
import com.qwerty.learn.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	UserService userService;
	
	public UserController() {
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseDTO<Map<String,Object>>> login(@RequestBody User userDTO) {
		return new ResponseEntity<>(userService.login(userDTO),HttpStatus.OK);
	}

	@PostMapping("/register")
	public Object register(@RequestParam("userName") String userName, @RequestParam("password") String password) {
		try {
			User user = new User();
			user.setId(UUID.randomUUID());
			user.setUserName(userName);
			user.setPassWord(passwordEncoder.encode(password));
			return userRepository.save(user);
//			return userDetailsService.register(userName, password);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
}

