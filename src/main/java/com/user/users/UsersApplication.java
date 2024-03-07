package com.user.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UsersApplication {
    public static void main(String[] args) {
        System.err.println(" Run ");
        SpringApplication.run(UsersApplication.class, args);
        System.err.println(" Stop ");
    }
    //prueba de commit
}
