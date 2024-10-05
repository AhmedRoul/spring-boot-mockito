package com.test.spring_boot_mockito.service.Imps;

import com.test.spring_boot_mockito.Dto.UserDto;
import com.test.spring_boot_mockito.Repository.UserRepository;
import com.test.spring_boot_mockito.model.User;
import com.test.spring_boot_mockito.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto getUser(long id) {
        Optional<User> user= repository.findById(id);
        if(user.isPresent()){
            return modelMapper.map(user.get(),UserDto.class);
        }
        return null;
    }

    @Override
    public UserDto getUser(String Email) {
        Optional<User> user= repository.findByEmail(Email);
        if(user.isPresent()){
            return modelMapper.map(user.get(),UserDto.class);
        }
        return null;
    }

    @Override
    public UserDto Save(UserDto userDto) {
        User user= modelMapper.map(userDto,User.class);
        User  Saved=repository.save(user);

        return modelMapper.map(Saved,UserDto.class);
    }

    @Override
    public UserDto update(long id, UserDto userDto) {
        Optional<User> user= repository.findById(id);
        if(user.isPresent()){
           User old= modelMapper.map(userDto,User.class);
            User  Saved=repository.save(old);
           return modelMapper.map(Saved,UserDto.class);

        }
        return null;
    }

    @Override
    public UserDto updatePartitions(long id, Map<String, Object> attributes) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            User user1 = user.get();

            attributes.forEach((key, value) -> {
                try {
                    Field field = User.class.getDeclaredField(key);
                    field.setAccessible(true);
                    field.set(user1, value);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException("Error updating user1 field: " + key, e);
                }
            });
            return update(id,modelMapper.map(user1, UserDto.class));
        }
        return null;
    }

    @Override
    public boolean delete(UserDto userDto) {
        return deletebyId(userDto.getId());

    }

    @Override
    public boolean deletebyId(long  id) {
        Optional<User> user= repository.findById(id);
        if(user.isPresent()){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
