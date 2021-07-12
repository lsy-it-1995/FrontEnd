package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class studentConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            studentRepository repository
    ){
        return args -> {
            student  lee = new student(1L,
                    "shaoyul@uci.edu",
                    "lee",
                    LocalDate.of(1995, Month.SEPTEMBER,9));

            student  alex = new student(2L,
                    "alex@uci.edu",
                    "alex",
                    LocalDate.of(1955, Month.DECEMBER,23));

            repository.saveAll(
                    List.of(lee, alex)
            );
        };
    }
}
