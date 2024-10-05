package com.test.spring_boot_mockito.service;


import com.test.spring_boot_mockito.Dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserService {

	 UserDto getUser(long id);
	 UserDto getUser(String Email);

	 UserDto Save(UserDto userDto);
	 UserDto update(long id,UserDto userDto);
	 UserDto updatePartitions(long id, Map<String ,Object> attributes);

	 boolean delete(UserDto userDto);
	 boolean deletebyId(long  id);



}
