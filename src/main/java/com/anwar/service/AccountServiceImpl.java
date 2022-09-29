package com.anwar.service;

import com.anwar.ApplicationUserDetails;
import com.anwar.dto.Account.RegisterAccountDto;
import com.anwar.dto.Response.ResponseDto;
import com.anwar.entity.Account;
import com.anwar.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService, UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseDto register(RegisterAccountDto dto) {
        var hashedPassword = passwordEncoder.encode(dto.getPassword());

        Account newAccount = new Account(
                dto.getUsername(),
                hashedPassword,
                dto.getFirstName(),
                dto.getLastName(),
                dto.getBalance() == null ? new BigDecimal(0) : dto.getBalance(),
                dto.getAddress(),
                dto.getEmail(),
                "User"
        );

        accountRepository.save(newAccount);

        ResponseDto response = new ResponseDto(
                HttpStatus.CREATED.value(),
                "New Account is created successfully",
                System.currentTimeMillis()
        );

        return response;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findById(username).get();

        return new ApplicationUserDetails(account);
    }
}
