package com.qwerty.learn.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.qwerty.learn.domain.CustomUserDetails;
import com.qwerty.learn.domain.User;
import com.qwerty.learn.dto.ResponseDTO;
import com.qwerty.learn.repository.UserRepository;
import com.qwerty.learn.service.UserService;
import com.qwerty.learn.utils.JwtUtil;

@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService,UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	 
	 public UserDetailsServiceImpl() {
	 }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOptional = userRepository.findByUserName(username);
		if (userOptional.isPresent()) {
			CustomUserDetails customUserDetails = new CustomUserDetails(userOptional.get().getUserName(),
					userOptional.get().getPassWord());
			return customUserDetails;
		} else {
			throw new UsernameNotFoundException("User " + username + " not found");
		}
	}
	

	@Override
	public ResponseDTO<Map<String, Object>> login(User user) {
		Map<String, Object> resultBody = new HashMap<>();
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					user.getUserName(), user.getPassWord()));
			if (authentication.isAuthenticated()) {
				String token = jwtUtil.generateTokenFromUserName( user.getUserName()); 
				String userName = ((CustomUserDetails) authentication.getPrincipal()).getUsername();
				resultBody.put("userName", userName);
				resultBody.put("accessToken", token);
			} else {
				throw new UsernameNotFoundException("invalid user request..!!");
			}
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		return new ResponseDTO<>(false,200,"Login Successfull",resultBody);
	}

}
