package com.anwar.rest;

import com.anwar.dto.Spending.SpendMoneyDto;
import com.anwar.exceptionhandler.NotFoundException;
import com.anwar.service.SpendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/spending")
public class SpendingRestController {
    @Autowired
    private SpendingService spendingService;

    @PostMapping
    public ResponseEntity<Object> spendMoney(@RequestBody SpendMoneyDto dto) {
        var response = spendingService.spendMoney(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSpending(@PathVariable Long id) {
        var spending = spendingService.getSpending(id);

        if (spending == 0) {
            throw new NotFoundException("Spending with id " + id + " is not found.");
        }

        var response = spendingService.deleteSpending(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
