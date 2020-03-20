package com.emsoft.riskwaring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class RiskwaringApplication {

    public static void main(String[] args) {
        SpringApplication.run(RiskwaringApplication.class, args);
    }

}
