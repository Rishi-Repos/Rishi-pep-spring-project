package com.example.repository;

import com.example.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AccountRepository extends JpaRepository<Account, Long> {
    
    //named query to find accounts by username
    List<Account> findAccountsByUsername(String username);

}
