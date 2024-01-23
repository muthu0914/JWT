package com.qwerty.learn.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qwerty.learn.domain.User;
import com.qwerty.learn.repository.UserRepository;
import com.qwerty.learn.utils.JwtGeneratorUtil;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	 @Autowired
	  PasswordEncoder passwordEncoder;
	@Autowired
	UserDetailsService userDetailsService;
	public UserController() {}


//	@GetMapping("/login")
//	public ResponseEntity<?> loginUser(@RequestParam String user) {
//		try {
//			return new ResponseEntity<>(JwtGeneratorUtil.generateToken(user), HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
//		}
//	}
//	
	@PostMapping("/register")
	public Object register(@RequestParam("userName") String userName, @RequestParam("password") String password) {
		try {
			User user = new User();
			user.setId(UUID.randomUUID());
			user.setUserName(userName);
			user.setPassword(passwordEncoder.encode(password));
			return userRepository.save(user);
//			return userDetailsService.register(userName, password);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
}

