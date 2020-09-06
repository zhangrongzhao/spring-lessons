package org.geekbang.thinking.in.spring.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Hello World Configuration class
 * ****/

@Configuration
public class HelloWorldConfiguration {

    @Bean
    public String helloWorld(){
        return "Hello world";
    }
}
