package dev.connorbuckley.invest_mock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.connorbuckley.invest_mock.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    public boolean existsByUsername(String username);
}
