package com.moneymesh.moneymesh.entity;

public class BankAccountDto {
    public String name;
    public Bank bank;

    public BankAccountDto(Bank bank, String name) {
        this.bank = bank;
        this.name = name;
    }
}
