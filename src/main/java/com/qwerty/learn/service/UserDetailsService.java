package com.qwerty.learn.service;

import org.springframework.stereotype.Service;

import com.qwerty.learn.domain.User;

@Service
public interface UserDetailsService {

	User register(String username,String password);
}
