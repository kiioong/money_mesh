package com.moneymesh.moneymesh.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor(force = true)
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private BankAccount bankAccount;

    public Booking(String name, BankAccount bankAccount) {
        this.name = name;
        this.bankAccount = bankAccount;
    }
}
