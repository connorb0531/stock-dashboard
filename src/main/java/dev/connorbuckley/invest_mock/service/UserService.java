package dev.connorbuckley.invest_mock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.connorbuckley.invest_mock.dto.CreateUserRequest;
import dev.connorbuckley.invest_mock.dto.UserResponse;
import dev.connorbuckley.invest_mock.entity.Account;
import dev.connorbuckley.invest_mock.entity.User;
import dev.connorbuckley.invest_mock.exception.UsernameAlreadyExistsException;
import dev.connorbuckley.invest_mock.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse addUser(CreateUserRequest userRequest) throws UsernameAlreadyExistsException {
        if (userRepository.existsByUsername(userRequest.username())) {
            throw new UsernameAlreadyExistsException("User already exists");
        }

        User user = new User();
        Account account = new Account();

        user.setAccount(account);
        user.setUsername(userRequest.username());
        user.setPassword(userRequest.password());
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
