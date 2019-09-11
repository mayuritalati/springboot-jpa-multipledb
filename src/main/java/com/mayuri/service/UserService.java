package com.mayuri.service;

import java.util.List;

import com.mayuri.user.data.UserEntity;

public interface UserService {
	List<UserEntity> getUsers();
}
