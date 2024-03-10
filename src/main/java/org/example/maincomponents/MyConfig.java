package org.example.maincomponents;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    @Bean
    public String directory() {
        return "C:\\temp";
    }
}
