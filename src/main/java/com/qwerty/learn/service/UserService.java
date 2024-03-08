package com.qwerty.learn.service;

import java.util.Map;

import com.qwerty.learn.domain.User;

public interface UserService {

	public Map<String,Object> login(User user);
}
