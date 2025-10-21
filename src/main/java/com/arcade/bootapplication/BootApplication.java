package com.arcade.bootapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class BootApplication {

    public static void main(String[] args) {
        ApplicationContext context =
                SpringApplication.run(BootApplication.class, args);

    }

}
