package com.anwar.service;

import com.anwar.dto.Response.ResponseDto;
import com.anwar.dto.Spending.SpendMoneyDto;
import com.anwar.dto.Spending.SpendingGridDto;
import com.anwar.entity.Spending;
import org.springframework.data.domain.Page;

public interface SpendingService {
    ResponseDto spendMoney(SpendMoneyDto dto);

    ResponseDto deleteSpending(Long id);

    Long countSpending(Long id);

    Page<SpendingGridDto> getAllSpendings(Integer page);

    Spending getSpendingbyId(Long id);
}
