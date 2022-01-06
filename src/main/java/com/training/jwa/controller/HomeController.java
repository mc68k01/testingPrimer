package com.training.jwa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
public class HomeController {
    @Autowired
    Payment payment;

    @RequestMapping("/home")
    public String home(){
        return "--Hello and Warm Welcome to JWA PRIMER Session-- Revature";
    }

    @RequestMapping("/pay")
    public String paid() {
        return payment.pay(9000);
    }

    @RequestMapping("/pay/{amount}")
    public String pay(@PathVariable("amount") int amount) {
        return payment.pay(amount);
    }

    @RequestMapping("/pay/{amount}/name/{acctholder}")
    public String payAndAcctholder(@PathVariable("amount") int amount,@PathVariable("acctholder") String acctholder) {
        return payment.payAndAcctholder(amount,acctholder);
    }

//    @PostMapping("/addProduct"/{product}) {
//
//    }
//
//    @DeleteMapping("/deleteProduct"/{productID}) {
//
//    }
//
//    @PutMapping("/updateProduct"/{productID}) {
//
//    }
//
//    @RequestMapping("/getProduct"/{productID}) {
//
//    }

}
