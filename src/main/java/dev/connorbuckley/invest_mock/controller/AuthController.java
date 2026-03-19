package dev.connorbuckley.invest_mock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import dev.connorbuckley.invest_mock.dto.AuthUserRequest;
import dev.connorbuckley.invest_mock.exception.InvalidLoginException;
import dev.connorbuckley.invest_mock.exception.UsernameAlreadyExistsException;
import dev.connorbuckley.invest_mock.service.AuthService;
import dev.connorbuckley.invest_mock.service.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private UserService userService;
    private AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }
    
    @PostMapping("/signup")
    public String signup(AuthUserRequest userRequest, Model model) {
        try {
            userService.registerUser(userRequest);
            return "redirect:/auth/login";
        } catch (UsernameAlreadyExistsException e) {
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }

    @PostMapping("/login")
    public String login(AuthUserRequest userRequest, Model model, HttpServletResponse response) {
        try {
            var loginResponse = authService.login(userRequest);

            Cookie cookie = new Cookie("token", loginResponse.token());
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);

            return "redirect:/dashboard";
        } catch (InvalidLoginException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", "");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/auth/login";
    }
}
