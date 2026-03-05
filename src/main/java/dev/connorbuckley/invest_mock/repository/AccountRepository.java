package dev.connorbuckley.invest_mock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.connorbuckley.invest_mock.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
    
}
