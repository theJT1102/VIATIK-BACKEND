package com.viatik; // <--- Debe ser este paquete

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiViatikApplication { // <--- Debe ser este nombre

    public static void main(String[] args) {
        SpringApplication.run(ApiViatikApplication.class, args);
    }
}