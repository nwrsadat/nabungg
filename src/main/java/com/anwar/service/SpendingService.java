package com.anwar.service;

import com.anwar.dto.Response.ResponseDto;
import com.anwar.dto.Spending.SpendMoneyDto;
import com.anwar.entity.Spending;

public interface SpendingService {
    ResponseDto spendMoney(SpendMoneyDto dto);

    ResponseDto deleteSpending(Long id);

    Long getSpending(Long id);
}
