package com.qwerty.learn.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.qwerty.learn.domain.User;
import com.qwerty.learn.repository.UserRepository;

@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

//	 @Autowired
//	  PasswordEncoder passwordEncoder;
	 
	 public UserDetailsServiceImpl() {
	 }


//	public User register(String username, String password) {
//		User user = new User();
//		user.setPassword(passwordEncoder.encode(password));
//		return userRepository.save(user);
//	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User>  userOptional = userRepository.findByUserName(username);
		if(userOptional.isPresent()) {
			return userOptional.get();
		}else {
			throw new UsernameNotFoundException("User " + username + " not found");
		}
	}

}
