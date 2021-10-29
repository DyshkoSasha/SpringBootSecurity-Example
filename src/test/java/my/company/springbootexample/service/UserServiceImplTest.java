package my.company.springbootexample.service;

import my.company.springbootexample.model.Adress;
import my.company.springbootexample.model.User;
import my.company.springbootexample.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;


    @Test
    void addUser() {
        User user = new User();
        userService.addUser(user);
        verify(userRepository).save(user);

    }

    @Test
    void getById() {
        List<User> list = List.of(
                new User()
        );

        when(userRepository.findById(7L)).thenReturn(java.util.Optional.ofNullable(list.get(0)));
        assertEquals(list.get(0), userService.getById(7L));
    }

    @Test
    void deletedById() {
        userService.deletedById(8L);
        verify(userRepository).deleteById(8L);
    }

    @Test
    void findAllUser() {
        List<User> list = List.of(
                new User(),
                new User()
        );

        doReturn(list).when(userRepository).findAll();
        assertEquals(list, userService.findAllUser());

    }

    @Test
    void loadUserByUsername() {
        List<User> list = List.of(
                new User()
        );

        when(userRepository.getByFirstName("Anton")).thenReturn(list.get(0));
        assertEquals(list.get(0), userService.loadUserByUsername("Anton"));
    }
}
