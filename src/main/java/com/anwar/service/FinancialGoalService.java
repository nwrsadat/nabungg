package com.anwar.service;

import com.anwar.dto.FinancialGoal.AddMoneyDto;
import com.anwar.dto.FinancialGoal.FinancialGoalDto;
import com.anwar.dto.FinancialGoal.NewFinancialGoalDto;
import com.anwar.dto.Response.ResponseDto;
import org.springframework.data.domain.Page;

public interface FinancialGoalService {
    ResponseDto createFinancialGoal(NewFinancialGoalDto dto);

    ResponseDto addMoney(Long id, AddMoneyDto dto);

    Long countFinancialGoalById(Long id);

    Page<FinancialGoalDto> getAllGoals(Integer page);
}
