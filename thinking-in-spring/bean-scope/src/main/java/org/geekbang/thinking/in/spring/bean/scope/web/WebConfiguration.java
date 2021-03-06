package org.geekbang.thinking.in.spring.bean.scope.web;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Web MVC 配置类
 * */
@Configuration
@EnableWebMvc
public class WebConfiguration {

    @Bean
    //@RequestScope
    //@SessionScope
    @ApplicationScope
    public User user(){
        User user=new User();
        user.setId(1L);
        user.setName("小马哥");
        return  user;
    }
}
