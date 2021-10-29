package my.company.springbootexample.service;

import my.company.springbootexample.model.User;
import my.company.springbootexample.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    void findById() {
        User user = new User();

        when(userRepository.findById(7L)).thenReturn(java.util.Optional.of(user));
        assertEquals(user, userService.findById(7L));
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

        when(userRepository.findAll()).thenReturn(list);
        assertEquals(list, userService.findAllUser());

    }

    @Test
    void loadUserByUsername() {
        User user = new User();

        when(userRepository.getByFirstName("Anton")).thenReturn(user);
        assertEquals(user, userService.loadUserByUsername("Anton"));
    }
}
