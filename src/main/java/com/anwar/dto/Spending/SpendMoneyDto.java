package com.anwar.dto.Spending;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class SpendMoneyDto {
    private String name;
    private BigDecimal price;

    @Override
    public String toString() {
        return "SpendMoneyDto{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
