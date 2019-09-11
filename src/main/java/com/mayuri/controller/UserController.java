package com.mayuri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mayuri.service.UserService;
import com.mayuri.user.data.UserEntity;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value= "/user", method=RequestMethod.GET)
	public ResponseEntity<List<UserEntity>> getUser(){
		return ResponseEntity.ok().body(userService.getUsers());
	}

}
