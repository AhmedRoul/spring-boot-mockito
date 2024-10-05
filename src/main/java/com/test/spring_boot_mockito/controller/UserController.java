package com.test.spring_boot_mockito.controller;


import com.test.spring_boot_mockito.Dto.UserDto;
import com.test.spring_boot_mockito.model.User;
import com.test.spring_boot_mockito.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
	@Autowired
	private UserService service;

	@PostMapping(value = "/save")
	public UserDto saveUser(@RequestBody UserDto userDto) {
		return service.Save(userDto);
	}


}
