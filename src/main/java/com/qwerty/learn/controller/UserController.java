package com.qwerty.learn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qwerty.learn.utils.JwtGeneratorUtil;

@RestController
@RequestMapping("/user")
public class UserController {

	public UserController() {}

	@GetMapping("/login")
	public ResponseEntity<?> loginUser(@RequestParam String user) {
		try {
			return new ResponseEntity<>(JwtGeneratorUtil.generateToken(user), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
}

