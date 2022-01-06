package com.training.jwa.controller;

import org.springframework.stereotype.Service;

@Service
public class Payment {

    public String pay(int amount) {
        return "Successfully credited USD: " + amount;
    }
    public String payAndAcctholder(int amount, String acctholder) {
        return "Successfully credited USD: " + amount + " from " + acctholder;
    }
}
