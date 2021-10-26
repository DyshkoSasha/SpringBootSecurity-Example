package my.company.springbootexample.controllers;

import lombok.AllArgsConstructor;
import my.company.springbootexample.model.Adress;
import my.company.springbootexample.model.Role;
import my.company.springbootexample.model.User;
import my.company.springbootexample.service.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth")
@AllArgsConstructor
@Controller
public class AllController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Secured(value = {"ROLE_USER","ROLE_ADMIN"})
    @GetMapping
    public String getAllUsers(Model model) {
        List<User> allUser = userService.findAllUser();
        model.addAttribute("allUser", allUser);
        return "users";
    }

    @Secured(value = {"ROLE_ADMIN"})
    @GetMapping("/delete")
    public String delete(@RequestParam Long id) {
        userService.deletedById(id);
        return "redirect:/auth";
    }

    @Secured(value = {"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/adress")
    public String getUsersAdress(Model model, @RequestParam Long id) {
        model.addAttribute(userService.getById(id));
        return "adress";
    }

    @Secured(value = {"ROLE_USER","ROLE_ADMIN"})
    @GetMapping( "/add")
    public String getPageSave(Model model, User user, @RequestParam(required = false) Long id) {
        if (id != null) {
            model.addAttribute(userService.getById(id));
        }
        return "userForm";
    }

    @Secured(value = {"ROLE_USER","ROLE_ADMIN"})
    @PostMapping
    public String saveUsers(@RequestParam(required = false) Long id,
                            @RequestParam String firstName,
                            @RequestParam String lastName,
                            @RequestParam Integer age,
                            @RequestParam String password,
                            @RequestParam(name = "adress.city") String city,
                            @RequestParam(name = "adress.street") String street,
                            @RequestParam(name = "adress.house") String house,
                            @RequestParam String role) {

        String encryptedPassword = passwordEncoder.encode(password);

        User user = new User(id, firstName, lastName, age, encryptedPassword, new Adress(city, street, house),
                new Role(role));
        userService.addUser(user);
        return "redirect:/auth";
    }
}
