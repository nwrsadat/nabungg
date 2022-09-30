package com.anwar.rest;

import com.anwar.dto.FinancialGoal.FinancialGoalDto;
import com.anwar.service.FinancialGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/financialgoal")
public class FinancialGoalRestController {
    @Autowired
    private FinancialGoalService financialGoalService;

    @PostMapping
    public ResponseEntity<Object> createFinancialGoal(@RequestBody FinancialGoalDto dto) {
        var response = financialGoalService.createFinancialGoal(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<Object> addMoneyToFinancialGoal() {
        return ResponseEntity.status(HttpStatus.OK).body("Add money");
    }
}
