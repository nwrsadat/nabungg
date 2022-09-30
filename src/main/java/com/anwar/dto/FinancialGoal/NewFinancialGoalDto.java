package com.anwar.dto.FinancialGoal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class NewFinancialGoalDto {
    private String productName;
    private BigDecimal totalPrice;
}
