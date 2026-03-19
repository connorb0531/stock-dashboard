package dev.connorbuckley.invest_mock.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import dev.connorbuckley.invest_mock.dto.AuthUserRequest;
import dev.connorbuckley.invest_mock.dto.LoginUserResponse;
import dev.connorbuckley.invest_mock.entity.User;
import dev.connorbuckley.invest_mock.exception.InvalidLoginException;
import dev.connorbuckley.invest_mock.repository.UserRepository;
import dev.connorbuckley.invest_mock.security.CustomUserDetails;
import dev.connorbuckley.invest_mock.security.JwtService;

@Service
public class AuthService {
    private UserRepository userRepository;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginUserResponse login(AuthUserRequest userRequest) throws InvalidLoginException {
        User user = userRepository.findByUsername(userRequest.username())
        .orElseThrow(() -> new InvalidLoginException("Invalid username or password"));

        if (!passwordEncoder.matches(userRequest.password(), user.getPassword())) {
            throw new InvalidLoginException("Invalid username or password");
        }
        return proccessUserLogin(user);
    }

    private LoginUserResponse proccessUserLogin(User user) {
        String token = jwtService.generateToken(new CustomUserDetails(user));
        return new LoginUserResponse(user.getUsername(), token);
    }
}
