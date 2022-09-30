package com.anwar.service;

import com.anwar.dto.Account.AddBalanceDto;
import com.anwar.dto.Account.RegisterAccountDto;
import com.anwar.dto.Response.ResponseDto;

public interface AccountService {
    ResponseDto register(RegisterAccountDto dto);

    String getAccountRole(String username);

    ResponseDto addBalance(AddBalanceDto dto);
}
