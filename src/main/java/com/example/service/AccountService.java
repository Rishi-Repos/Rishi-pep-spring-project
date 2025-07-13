package com.example.service;

import com.example.entity.Account;
import com.example.exception.DuplicateUsernameException;
import com.example.exception.UnsuccessfulRegistrationException;
import com.example.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    AccountRepository accountRepository;
    /* @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    } */

    // 1: Our API should be able to process new User registrations.
    public Account registerAccount(Account account){
        return accountRepository.save(account);
        /*if(accountRepository.findAccountsByUsername(account.getUsername()).size()>0){
            throw new DuplicateUsernameException("Duplicate username");
        } else if(account.getUsername().length()>0 && account.getPassword().length()>4){
            return accountRepository.save(account); //accountId automatically generated and returned
        } else{
            throw new UnsuccessfulRegistrationException("Blank username or password length is less than 4");
        }
        */
    }



}
