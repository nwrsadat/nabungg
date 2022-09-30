package com.anwar.service;

import com.anwar.dto.Response.ResponseDto;
import com.anwar.dto.Spending.SpendMoneyDto;
import com.anwar.entity.Spending;
import com.anwar.repository.SpendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SpendingServiceImpl implements SpendingService {

    @Autowired
    private SpendingRepository spendingRepository;

    @Override
    public ResponseDto spendMoney(SpendMoneyDto dto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
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
    public Long getSpending(Long id) {
        var spending = spendingRepository.countById(id);

        if (spending == null) {
            return null;
        }

        return spending;
    }
}
