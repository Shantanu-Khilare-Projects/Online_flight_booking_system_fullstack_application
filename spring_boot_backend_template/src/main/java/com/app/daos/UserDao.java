package com.app.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.User;


public interface UserDao extends JpaRepository<User, Long>{
	Optional<User> findByEmailAndPassword(String email, String password);
}
