package com.xyinc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AddressServicesApplication {
	public static void main(String[] args) {
        SpringApplication.run(AddressServicesApplication.class, args);
        //TODO incluir logger
        System.out.println("Server up");
    }
}