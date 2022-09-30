package com.anwar.dto.Spending;

import lombok.*;

import java.math.BigDecimal;

@Data
public class SpendMoneyDto {
    private final String name;
    private final BigDecimal price;
}
