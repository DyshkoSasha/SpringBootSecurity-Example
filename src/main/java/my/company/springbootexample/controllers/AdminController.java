package my.company.springbootexample.controllers;

import lombok.RequiredArgsConstructor;
import my.company.springbootexample.model.User;
import my.company.springbootexample.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> allUser = userService.findAllUser();
        model.addAttribute("allUser", allUser);
        return "users";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id) {
        userService.deletedById(id);
        return "redirect:/admin";
    }
}
