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
@Table(name = "Outcome")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Outcome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Price")
    private BigDecimal price;

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