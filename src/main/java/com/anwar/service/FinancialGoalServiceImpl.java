package com.anwar.service;

import com.anwar.dto.FinancialGoal.FinancialGoalDto;
import com.anwar.dto.Response.ResponseDto;
import com.anwar.entity.Account;
import com.anwar.entity.FinancialGoal;
import com.anwar.repository.AccountRepository;
import com.anwar.repository.FinancialGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class FinancialGoalServiceImpl implements FinancialGoalService {
    @Autowired
    private FinancialGoalRepository financialGoalRepository;

    @Autowired
    private AccountRepository accountRepository;

    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Account getAccount() {
        return accountRepository.findById(getUsername()).get();
    }

    @Override
    public ResponseDto createFinancialGoal(FinancialGoalDto dto) {
        Account account = getAccount();
        FinancialGoal financialGoal = new FinancialGoal();

        financialGoal.setProductName(dto.getProductName());
        financialGoal.setTotalPrice(dto.getTotalPrice());
        financialGoal.setYourMoney(new BigDecimal(0));
        financialGoal.setIsAchieved(false);
        financialGoal.setCreatedAt(LocalDateTime.now());
        financialGoal.setUpdatedAt(LocalDateTime.now());
        financialGoal.setUsername(account.getUsername());

        financialGoalRepository.save(financialGoal);

        ResponseDto responseDto = new ResponseDto(
                HttpStatus.CREATED.value(),
                "New financial goal has been created successfully.",
                System.currentTimeMillis()
        );

        return responseDto;
    }
}
