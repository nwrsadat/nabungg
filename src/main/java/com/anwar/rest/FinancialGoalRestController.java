package com.anwar.rest;

import com.anwar.service.FinancialGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/financialgoal")
public class FinancialGoalRestController {
    @Autowired
    private FinancialGoalService financialGoalService;

    @PostMapping
    public ResponseEntity<Object> createFinancialGoal() {
        return ResponseEntity.status(HttpStatus.CREATED).body("Create New Financial Goal");
    }
}
