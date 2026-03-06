package dev.connorbuckley.invest_mock.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import dev.connorbuckley.invest_mock.dto.AdminCreateUserRequest;
import dev.connorbuckley.invest_mock.entity.User.Role;
import dev.connorbuckley.invest_mock.repository.UserRepository;
import dev.connorbuckley.invest_mock.service.UserService;

@Configuration
public class DataSeeder {
    @Value("${admin.username}")
    private String username;
    @Value("${admin.password}")
    private String password;

    @Bean
    CommandLineRunner seedUsers(UserRepository userRepository, PasswordEncoder passwordEncoder, 
                                UserService userService) {
        return args -> {
            if (userRepository.findByUsername(username).isEmpty()) {

                userService.adminCreateUser(new AdminCreateUserRequest(
                    username,
                    password,
                    Role.ADMIN
                ));
                System.out.println("Admin user seeded");
            } else {
                System.out.println("Admin already exists");
            }
        };
    }
}
