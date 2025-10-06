package com.moneymesh.moneymesh.config;

import com.moneymesh.moneymesh.entity.Bank;
import com.moneymesh.moneymesh.repository.BankRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Configuration
public class BankConfig {
    @Bean
    CommandLineRunner commandLineRunner(BankRepository repository) {
        return args -> {
            final Bank sparkasse = new Bank("Sparkasse");
            final Bank dkb = new Bank("DKB");
            final Bank ing = new Bank("ING");

            repository.saveAll(List.of(sparkasse, dkb, ing));
        };
    }
}