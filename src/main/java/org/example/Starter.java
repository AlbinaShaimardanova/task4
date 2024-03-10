package org.example;

import org.example.maincomponents.Maker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication(scanBasePackages = "org.example")
public class Starter {
    public static void main(String[] args) throws IOException {
        ApplicationContext ctx = SpringApplication.run(Starter.class);
        ctx.getBean(Maker.class).make();
    }
}
