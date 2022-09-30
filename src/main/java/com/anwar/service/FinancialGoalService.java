package com.anwar.service;

import com.anwar.dto.FinancialGoal.AddMoneyDto;
import com.anwar.dto.FinancialGoal.FinancialGoalDto;
import com.anwar.dto.Response.ResponseDto;

public interface FinancialGoalService {
    ResponseDto createFinancialGoal(FinancialGoalDto dto);

    ResponseDto addMoney(Long id, AddMoneyDto dto);

    Long countFinancialGoalById(Long id);
}
