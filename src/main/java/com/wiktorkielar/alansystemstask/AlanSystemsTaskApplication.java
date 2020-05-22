package com.wiktorkielar.alansystemstask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AlanSystemsTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlanSystemsTaskApplication.class, args);
    }

}
