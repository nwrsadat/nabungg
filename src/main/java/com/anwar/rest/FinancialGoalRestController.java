package com.anwar.rest;

import com.anwar.dto.FinancialGoal.AddMoneyDto;
import com.anwar.dto.FinancialGoal.FinancialGoalDto;
import com.anwar.dto.FinancialGoal.NewFinancialGoalDto;
import com.anwar.exceptionhandler.NotFoundException;
import com.anwar.service.FinancialGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/financialgoal")
public class FinancialGoalRestController {
    @Autowired
    private FinancialGoalService financialGoalService;

    @GetMapping
    public ResponseEntity<Object> getAllGoal(@RequestParam(defaultValue = "1") Integer page) {
        var response = financialGoalService.getAllGoals(page);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<Object> createFinancialGoal(@RequestBody NewFinancialGoalDto dto) {
        var response = financialGoalService.createFinancialGoal(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> addMoneyToFinancialGoal(@PathVariable Long id, @RequestBody AddMoneyDto dto) {
        var totalFinancialGoal = financialGoalService.countFinancialGoalById(id);

        if (totalFinancialGoal == 0) {
            throw new NotFoundException("Financial goal with id " + id + " is not found.");
        }

        var response = financialGoalService.addMoney(id, dto);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
