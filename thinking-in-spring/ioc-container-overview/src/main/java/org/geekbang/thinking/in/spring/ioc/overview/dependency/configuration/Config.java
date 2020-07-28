package org.geekbang.thinking.in.spring.ioc.overview.dependency.configuration;


import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean(name={"user","xiaomage-user"})
    public User getUser(){
        User user=new User();
        user.setId(2L);
        user.setName("小马哥2");
        return user;
    }
}
