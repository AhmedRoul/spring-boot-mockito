package com.test.spring_boot_mockito.Repository;


import com.test.spring_boot_mockito.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Long> {

	
	Optional<User> findByAddress(String address);
	Optional<User> findByEmail(String email);



}
