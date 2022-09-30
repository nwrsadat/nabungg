package com.anwar.dto.Spending;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class SpendingGridDto {
    private String name;
    private BigDecimal price;
    private String username;
}
