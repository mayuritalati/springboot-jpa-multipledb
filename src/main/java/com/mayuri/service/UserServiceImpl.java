package com.mayuri.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mayuri.repositroy.UserRepository;
import com.mayuri.user.data.UserEntity;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<UserEntity> getUsers() {
		return userRepository.findAll();
	}

	
}
