package com.qwerty.learn.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtGeneratorUtil {

	public static Map<String,String> generateToken(String username){
		String jwtToken = "";
		Date expirationTime = new Date(System.currentTimeMillis() + 120000); // set 50 seconds
		jwtToken = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretKey")
				.setExpiration(expirationTime)
				.compact();
	    Map<String, String> jwtTokenMap = new HashMap<>();
	    jwtTokenMap.put("token", jwtToken);
	    return jwtTokenMap;
	}
}
