package org.geekbang.thinking.in.spring.ioc.overview.dependency.configuration;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.SuperUser;
import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConfigComponent {

    @Bean(name={"superuser","super-user","super_user","admin","administrator"})
    public User getUser(){
        SuperUser user=new SuperUser();
        user.setId(2L);
        user.setName("小马哥2");
        user.setAddress("北京");
        return user;
    }
}
