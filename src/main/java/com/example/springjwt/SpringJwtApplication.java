package com.example.springjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJwtApplication.class, args);
    }

}
