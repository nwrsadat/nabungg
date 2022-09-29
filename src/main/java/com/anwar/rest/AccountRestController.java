package com.anwar.rest;

import com.anwar.dto.Account.RegisterAccountDto;
import com.anwar.dto.Response.ResponseDto;
import com.anwar.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/account")
public class AccountRestController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterAccountDto dto) {
        var response = accountService.register(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
