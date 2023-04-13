package com.liuyubo.qs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class QueuingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueuingSystemApplication.class, args);
    }

}
