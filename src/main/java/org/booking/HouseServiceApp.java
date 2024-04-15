package org.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HouseServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(HouseServiceApp.class, args);
        System.out.println("Hello world");
    }
}