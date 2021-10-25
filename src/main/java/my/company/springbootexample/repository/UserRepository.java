package my.company.springbootexample.repository;

import my.company.springbootexample.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByFirstName(String s);

//   List<User> getAllByRoleRole(String role);
}