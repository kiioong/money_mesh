package com.moneymesh.moneymesh.service;

import com.moneymesh.moneymesh.entity.BankAccount;
import com.moneymesh.moneymesh.entity.BankAccountDto;
import com.moneymesh.moneymesh.entity.User;
import com.moneymesh.moneymesh.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private UserService userService;

    public void createNewBankAccount(BankAccountDto bankAccountDto) {
        User currentUser = userService.getCurrentLoggedInUser();

        final BankAccount bankAccount = new BankAccount(bankAccountDto.name, currentUser, bankAccountDto.bank);

        bankAccountRepository.save(bankAccount);
    }
}
