package com.my.testproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath*:application-context.xml"})
public class TestProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestProjectApplication.class, args);
    }

}
