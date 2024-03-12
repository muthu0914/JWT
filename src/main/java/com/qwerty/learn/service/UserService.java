package com.qwerty.learn.service;

import java.util.Map;

import com.qwerty.learn.domain.User;
import com.qwerty.learn.dto.ResponseDTO;

public interface UserService {

	public ResponseDTO<Map<String,Object>> login(User user);
}
