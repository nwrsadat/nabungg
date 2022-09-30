package com.anwar.rest;

import com.anwar.JwtToken;
import com.anwar.dto.Account.AddBalanceDto;
import com.anwar.dto.Account.RegisterAccountDto;
import com.anwar.dto.Account.RequestTokenDto;
import com.anwar.dto.Account.ResponseTokenDto;
import com.anwar.dto.Response.ResponseDto;
import com.anwar.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/account")
public class AccountRestController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtToken;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterAccountDto dto) {
        var response = accountService.register(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Object> login(@RequestBody RequestTokenDto dto) {
        try {
            var token = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
            var authentication = authenticationManager.authenticate(token);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not authenticate", exception);
        }

        var role = accountService.getAccountRole(dto.getUsername());
        var token = jwtToken.generateToken(dto.getSubject(), dto.getUsername(), dto.getSecretKey(), role, dto.getAudience());
        var response = new ResponseTokenDto(dto.getUsername(), role, token);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/add-balance")
    public ResponseEntity<Object> addBalance(@RequestBody AddBalanceDto dto) {
        var response = accountService.addBalance(dto);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
