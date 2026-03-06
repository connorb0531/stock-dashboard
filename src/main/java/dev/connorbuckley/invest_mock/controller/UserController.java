package dev.connorbuckley.invest_mock.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import dev.connorbuckley.invest_mock.dto.AdminCreateUserRequest;
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
    @PreAuthorize("hasRole('ADMIN')")
    public String adminCreateUser(AdminCreateUserRequest userRequest, Model model) {
        try {
            userService.adminCreateUser(userRequest);
            return "redirect:/users";
        } catch (UsernameAlreadyExistsException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("users", userService.getUsers());
            return "users";
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String displayUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}