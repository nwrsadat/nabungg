package com.anwar.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "FinancialGoal")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
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

    @Column(name = "IsAchieved")
    private Boolean isAchieved;

    @CreatedDate
    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "UpdatedAt")
    private LocalDateTime updatedAt;

    @Column(name = "Username")
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Username", insertable = false, updatable = false)
    private Account account;
}