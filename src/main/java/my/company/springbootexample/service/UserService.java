package my.company.springbootexample.service;

import my.company.springbootexample.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void addUser(User user);

    void deletedById(Long id);

    User findById(Long id);

    List<User> findAllUser();
}
