package dev.connorbuckley.invest_mock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.connorbuckley.invest_mock.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    public boolean existsByUsername(String username);
    public Optional<User> findByUsername(String username);
}
