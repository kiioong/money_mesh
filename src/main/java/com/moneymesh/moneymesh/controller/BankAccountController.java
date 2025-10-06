package com.moneymesh.moneymesh.controller;

import com.moneymesh.moneymesh.entity.BankAccountDto;
import com.moneymesh.moneymesh.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankAccountController {
    @Autowired
    BankAccountService bankAccountService;

    @PostMapping
    public ResponseEntity<?> createNewBankAccount(BankAccountDto bankAccountDto) {
        bankAccountService.createNewBankAccount(bankAccountDto);

        return ResponseEntity.ok("Bank account created successfully");
    }
}
