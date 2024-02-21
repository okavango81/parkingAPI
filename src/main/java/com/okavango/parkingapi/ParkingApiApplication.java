package com.okavango.parkingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.NumberFormat;

@SpringBootApplication
public class ParkingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkingApiApplication.class, args);

//        double value = 2890.00;
//        NumberFormat coin = NumberFormat.getCurrencyInstance();
//        System.out.println();
//        System.out.println(coin.format(value));
    }

}
