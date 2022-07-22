package com.tpe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SpringBootIntroIjApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootIntroIjApplication.class, args);
    }

}