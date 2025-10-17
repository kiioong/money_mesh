package com.moneymesh.moneymesh.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "transaction")
public class TransactionController {
    @PostMapping
    public ResponseEntity<?> syncTransactions() {
        return  ResponseEntity.ok().build();

    }
}
