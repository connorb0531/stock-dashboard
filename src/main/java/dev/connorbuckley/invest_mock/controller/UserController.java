package dev.connorbuckley.invest_mock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.connorbuckley.invest_mock.dto.CreateUserRequest;
import dev.connorbuckley.invest_mock.exception.UsernameAlreadyExistsException;
import dev.connorbuckley.invest_mock.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String addUser(CreateUserRequest userRequest, Model model) {
        try {
            userService.addUser(userRequest);
            return "redirect:/users";
        } catch (UsernameAlreadyExistsException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("users", userService.getUsers());
            return "users";
        }
    }

    @GetMapping
    public String displayUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}