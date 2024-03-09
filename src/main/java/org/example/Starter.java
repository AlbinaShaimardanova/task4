package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication(scanBasePackages = "org.example")
public class Starter {
    public static void main(String[] args) throws IOException {
        ApplicationContext ctx = SpringApplication.run(Starter.class);
        ctx.getBean(Maker.class).make();
    }
}
