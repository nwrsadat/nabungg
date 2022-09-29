package com.anwar.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "Account")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Account {
    @Id
    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Balance")
    private BigDecimal balance;

    @Column(name = "Address")
    private String address;

    @Column(name = "Email")
    private String email;
}
