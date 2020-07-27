package org.geekbang.thinking.in.spring.ioc.overview.dependency.configuration;


import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public User getUser(){
        User user=new User();
        user.setId(1L);
        user.setName("龙哥");
        return user;
    }
}
