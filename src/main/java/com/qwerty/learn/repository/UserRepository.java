package com.qwerty.learn.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qwerty.learn.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>  {

	Optional<User> findByUserName(String username); 
}
