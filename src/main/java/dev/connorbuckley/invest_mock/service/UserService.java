package dev.connorbuckley.invest_mock.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.connorbuckley.invest_mock.dto.AdminCreateUserRequest;
import dev.connorbuckley.invest_mock.dto.AuthUserRequest;
import dev.connorbuckley.invest_mock.dto.UserResponse;
import dev.connorbuckley.invest_mock.entity.Account;
import dev.connorbuckley.invest_mock.entity.User;
import dev.connorbuckley.invest_mock.entity.User.Role;
import dev.connorbuckley.invest_mock.exception.UsernameAlreadyExistsException;
import dev.connorbuckley.invest_mock.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse registerUser(AuthUserRequest request) throws UsernameAlreadyExistsException {
        return createUserInternal(
            request.username(),
            request.password(),
            Role.USER
        );
    }

    public UserResponse adminCreateUser(AdminCreateUserRequest request) throws UsernameAlreadyExistsException {
        return createUserInternal(
            request.username(),
            request.password(),
            request.role()
        );
    }

    private UserResponse createUserInternal(String username, String rawPassword, Role role) throws UsernameAlreadyExistsException {
        if (userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyExistsException("User already exists");
        }

        User user = new User();
        Account account = new Account();

        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(role);

        user.setAccount(account);
        account.setUser(user);

        userRepository.save(user);

        return UserResponse.from(user);
    }

    public List<UserResponse> getUsers() {
        List<User> user = userRepository.findAll();

        return user.stream()
            .map(UserResponse::from)
            .toList();
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
