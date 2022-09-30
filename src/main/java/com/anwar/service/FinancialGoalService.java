package com.anwar.service;

import com.anwar.dto.FinancialGoal.FinancialGoalDto;
import com.anwar.dto.Response.ResponseDto;

public interface FinancialGoalService {
    ResponseDto createFinancialGoal(FinancialGoalDto dto);
}
