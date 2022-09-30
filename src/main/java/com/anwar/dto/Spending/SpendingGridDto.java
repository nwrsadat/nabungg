package com.anwar.dto.Spending;

import lombok.*;

import java.math.BigDecimal;

@Data
public class SpendingGridDto {
    private final String name;
    private final BigDecimal price;
    private final String username;
}
