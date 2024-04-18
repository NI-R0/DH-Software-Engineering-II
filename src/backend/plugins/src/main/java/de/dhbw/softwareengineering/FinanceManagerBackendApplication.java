package de.dhbw.softwareengineering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.UUID;

@SpringBootApplication
public class FinanceManagerBackendApplication {
    public static void main(String[] args){

        SpringApplication.run(FinanceManagerBackendApplication.class, args);

    }
}
