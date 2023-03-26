package com.freebee.hive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class FreebeeHiveCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreebeeHiveCoreApplication.class, args);
    }

}
