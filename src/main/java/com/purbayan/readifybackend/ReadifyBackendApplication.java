package com.purbayan.readifybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The ReadifyBackendApplication class is the entry point of the Readify backend
 * application.
 * This class is responsible for starting the Spring Boot application and running it.
 */
@SpringBootApplication
public class ReadifyBackendApplication {

    /**
     * The main method is the entry point of the application. It starts the Spring
     * Boot application.
     *
     * @param args The command-line arguments to be passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(ReadifyBackendApplication.class, args);
    }

}