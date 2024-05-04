package de.dhbw.softwareengineering;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class FinanceManagerBackendApplication {
    public static void main(String[] args){
        SpringApplication.run(FinanceManagerBackendApplication.class, args);
    }
}