package com.anwar.service;

import com.anwar.dto.Response.ResponseDto;
import com.anwar.dto.Spending.SpendMoneyDto;
import com.anwar.dto.Spending.SpendingGridDto;
import com.anwar.entity.Account;
import com.anwar.entity.Spending;
import com.anwar.repository.AccountRepository;
import com.anwar.repository.SpendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class SpendingServiceImpl implements SpendingService {

    @Autowired
    private SpendingRepository spendingRepository;

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
    public ResponseDto spendMoney(SpendMoneyDto dto) {
        String username = getUsername();
        Account account = getAccount();
        ResponseDto responseDto = null;

        if (account.getBalance().compareTo(dto.getPrice()) < 0) {
            responseDto = new ResponseDto(
                    HttpStatus.BAD_REQUEST.value(),
                    "Your balance is not enough. Your balance is not enough. You're broke. Get a job.",
                    System.currentTimeMillis()
            );

            return responseDto;
        }

        account.setBalance(account.getBalance().subtract(dto.getPrice()));

        accountRepository.save(account);

        Spending newSpending = new Spending();

        newSpending.setName(dto.getName());
        newSpending.setPrice(dto.getPrice());
        newSpending.setUsername(username);
        newSpending.setCreatedAt(LocalDateTime.now());
        newSpending.setUpdatedAt(LocalDateTime.now());

        spendingRepository.save(newSpending);

        responseDto = new ResponseDto(
                HttpStatus.CREATED.value(),
                "New spending has been created successfully",
                System.currentTimeMillis()
        );

        return responseDto;
    }

    @Override
    public ResponseDto deleteSpending(Long id) {
        spendingRepository.deleteById(id);

        ResponseDto responseDto = new ResponseDto(
                HttpStatus.OK.value(),
                "Spending with id " + id + " has been deleted successfully.",
                System.currentTimeMillis()
        );

        return responseDto;
    }

    @Override
    public Long countSpending(Long id) {
        var spending = spendingRepository.countById(id);

        if (spending == null) {
            return null;
        }

        return spending;
    }

    @Override
    public Page<SpendingGridDto> getAllSpendings(Integer page) {
        String username = getUsername();
        Pageable pagination = getPagination(page);
        Page<Spending> spendings = spendingRepository.findAllSpendings(username, pagination);
        Page<SpendingGridDto> dtos = new PageImpl<>(
                spendings.stream().map(
                        spending -> new SpendingGridDto(
                                spending.getName(),
                                spending.getPrice(),
                                spending.getUsername()
                        ))
                        .collect(Collectors.toList()), spendings.getPageable(), spendings.getTotalElements());

        return dtos;
    }

    @Override
    public Spending getSpendingbyId(Long id) {
        String username = getUsername();
        Spending spending = spendingRepository.findByIdAndUsername(id, username);

        return spending;
    }
}
