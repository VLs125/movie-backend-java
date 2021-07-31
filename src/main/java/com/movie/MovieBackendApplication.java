package com.movie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieBackendApplication {
    static final Logger log = LoggerFactory.getLogger(MovieBackendApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MovieBackendApplication.class, args);
    }

}
