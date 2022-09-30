package com.anwar.service;

import com.anwar.dto.Response.ResponseDto;
import com.anwar.dto.Spending.SpendMoneyDto;
import com.anwar.dto.Spending.SpendingGridDto;
import com.anwar.entity.Spending;
import com.anwar.helper.Helper;
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

    public Pageable getPagination(Integer page) {
        Pageable pagination = PageRequest.of(page - 1, 10, Sort.by("id"));

        return pagination;
    }

    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public ResponseDto spendMoney(SpendMoneyDto dto) {
        String username = getUsername();
        Spending newSpending = new Spending();

        newSpending.setName(dto.getName());
        newSpending.setPrice(dto.getPrice());
        newSpending.setUsername(username);
        newSpending.setCreatedAt(LocalDateTime.now());
        newSpending.setUpdatedAt(LocalDateTime.now());

        spendingRepository.save(newSpending);

        ResponseDto responseDto = new ResponseDto(
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
                                Helper.formatCurrency(spending.getPrice()),
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
