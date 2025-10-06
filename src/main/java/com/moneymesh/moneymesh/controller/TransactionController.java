package com.moneymesh.moneymesh.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @PostMapping
    public ResponseEntity<?> syncTransactions() {


    }
}
