package com.test.spring_boot_mockito.Service;

import com.test.spring_boot_mockito.Dto.UserDto;
import com.test.spring_boot_mockito.Repository.UserRepository;
import com.test.spring_boot_mockito.model.User;
import com.test.spring_boot_mockito.service.Imps.UserServiceImp;
import com.test.spring_boot_mockito.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService userService =new UserServiceImp();

    @Test
    public void UserService_CreateNew_ReturnUserDto() {

        User user = User.builder().age(10).email("Ahmed@gmail.com").name("Ahmed nagy").address("Giza").build();
        UserDto userDto = UserDto.builder().age(10).email("Ahmed@gmail.com").name("Ahmed nagy").address("Giza")
                .build();

        when(modelMapper.map(userDto, User.class)).thenReturn(user);
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);

        UserDto savedUserDto = userService.Save(userDto);
        Assertions.assertNotNull(savedUserDto);
        Assertions.assertEquals(savedUserDto.getEmail(), "Ahmed@gmail.com");
    }

    @Test
    public void getUserById_UserExists_ReturnsUserDto() {
        User user = User.builder().id(1L).name("Ahmed").email("ahmed@gmail.com").build();
        UserDto userDto = UserDto.builder().id(1).name("Ahmed").address("Giza").build();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
        UserDto result = userService.getUser(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(userDto.getEmail(), result.getEmail());
        verify(userRepository).findById(1L);

    }
    @Test
    public void getUserById_UserDoesNotExist_ReturnsNull() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        UserDto result = userService.getUser(1L);

        Assertions.assertNull(result);
        verify(userRepository).findById(1L);
    }
    @Test
    public void update_UserExists_ReturnsUpdatedUserDto() {
        User user = User.builder().id(1L).name("Ahmed").email("ahmed@gmail.com").build();
        UserDto userDto = UserDto.builder().id(1).name("Ahmed Updated").email("ahmed.updated@gmail.com").build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(modelMapper.map(userDto, User.class)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);

        UserDto result = userService.update(1L, userDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(userDto.getEmail(), result.getEmail());
        verify(userRepository).findById(1L);
        verify(userRepository).save(user);
    }

    @Test
    public void update_UserDoesNotExist_ReturnsNull() {
        UserDto userDto = UserDto.builder().id(1).name("Ahmed Updated").email("ahmed.updated@gmail.com").build();

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        UserDto result = userService.update(1L, userDto);

        Assertions.assertNull(result);
        verify(userRepository).findById(1L);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void delete_UserExists_ReturnsTrue() {
        User user = User.builder().id(1L).name("Ahmed").email("ahmed@gmail.com").build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        boolean result = userService.deletebyId(1L);

        Assertions.assertTrue(result);
        verify(userRepository).deleteById(1L);
    }

    @Test
    public void delete_UserDoesNotExist_ReturnsFalse() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        boolean result = userService.deletebyId(1L);

        Assertions.assertFalse(result);
        verify(userRepository, never()).deleteById(anyLong());
    }

}
