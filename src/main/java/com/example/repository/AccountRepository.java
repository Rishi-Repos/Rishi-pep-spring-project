package com.example.repository;

import com.example.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    
    //named query to find an account by username
    Account findAccountByUsername(String username);

    Account findAccountByAccountId(int accountId);

}
