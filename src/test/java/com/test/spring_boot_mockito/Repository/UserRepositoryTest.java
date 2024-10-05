package com.test.spring_boot_mockito.Repository;

import com.test.spring_boot_mockito.model.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@DataMongoTest

@TestPropertySource(properties = {
        "spring.data.mongodb.database=students"
})
public class UserRepositoryTest {
    @Autowired
    private  UserRepository userRepository;

    @Test
    public void UserRepository_saveAll()
    {
        User user1 =User.builder().id(10).age(20).name("Ahmed ").address("address").build();
        User user2 =User.builder().id(10).age(20).name("Ahmed ").address("address").build();

        userRepository.save(user1);
        userRepository.save(user2);
        List<User> users=userRepository.findAll();

        Assertions.assertNotNull(users);
        Assertions.assertTrue(users.size()==2);
    }



}
