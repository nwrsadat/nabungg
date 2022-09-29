package com.anwar.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "FinancialGoal")
@NoArgsConstructor
@AllArgsConstructor
public class FinancialGoals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "ProductName")
    private String productName;

    @Column(name = "TotalPrice")
    private BigDecimal totalPrice;

    @Column(name = "YourMoney")
    private BigDecimal yourMoney;
}