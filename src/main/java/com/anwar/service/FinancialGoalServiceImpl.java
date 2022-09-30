package com.anwar.service;

import com.anwar.dto.FinancialGoal.AddMoneyDto;
import com.anwar.dto.FinancialGoal.FinancialGoalDto;
import com.anwar.dto.FinancialGoal.NewFinancialGoalDto;
import com.anwar.dto.Response.ResponseDto;
import com.anwar.dto.Spending.SpendingGridDto;
import com.anwar.entity.Account;
import com.anwar.entity.FinancialGoal;
import com.anwar.repository.AccountRepository;
import com.anwar.repository.FinancialGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class FinancialGoalServiceImpl implements FinancialGoalService {
    @Autowired
    private FinancialGoalRepository financialGoalRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Pageable getPagination(Integer page) {
        Pageable pagination = PageRequest.of(page - 1, 10, Sort.by("id"));

        return pagination;
    }

    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Account getAccount() {
        return accountRepository.findById(getUsername()).get();
    }

    @Override
    public ResponseDto createFinancialGoal(NewFinancialGoalDto dto) {
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

    @Override
    public ResponseDto addMoney(Long id, AddMoneyDto dto) {
        Account account = getAccount();
        ResponseDto responseDto = null;

        if (account.getBalance().compareTo(dto.getMoney()) < 0) {
            responseDto = new ResponseDto(
                    HttpStatus.BAD_REQUEST.value(),
                    "Your balance is not enough. You're broke. Get a job.",
                    System.currentTimeMillis()
            );

            return responseDto;
        }

        FinancialGoal financialGoal = financialGoalRepository.findById(id).get();

        financialGoal.setYourMoney(financialGoal.getYourMoney().add(dto.getMoney()));

        if (financialGoal.getTotalPrice().compareTo(financialGoal.getYourMoney()) < 0) {
            financialGoal.setIsAchieved(true);

            responseDto = new ResponseDto(
                    HttpStatus.OK.value(),
                    "Your money is enough for your goal.",
                    System.currentTimeMillis()
            );

            financialGoalRepository.save(financialGoal);

            return responseDto;
        }

        financialGoal.setUpdatedAt(LocalDateTime.now());
        account.setBalance(account.getBalance().subtract(dto.getMoney()));

        financialGoalRepository.save(financialGoal);
        accountRepository.save(account);

        responseDto = new ResponseDto(
                HttpStatus.OK.value(),
                "Succes adding " + dto.getMoney() +  " to your financial goal. You need " + financialGoal.getTotalPrice().subtract(financialGoal.getYourMoney()) + " left for your goal.",
                System.currentTimeMillis()
        );

        return responseDto;
    }

    @Override
    public Long countFinancialGoalById(Long id) {
        return financialGoalRepository.countGoalById(id);
    }

    @Override
    public Page<FinancialGoalDto> getAllGoals(Integer page) {
        var pagination = getPagination(page);
        var account = getAccount();
        var financialGoals = financialGoalRepository.findAllGoals(account.getUsername(), pagination);

        Page<FinancialGoalDto> dtos = new PageImpl<>(
                financialGoals.stream().map(
                                goal -> new FinancialGoalDto(
                                        goal.getProductName(),
                                        goal.getTotalPrice(),
                                        goal.getYourMoney(),
                                        goal.getIsAchieved()
                                ))
                        .collect(Collectors.toList()), financialGoals.getPageable(), financialGoals.getTotalElements());

        return dtos;
    }
}
