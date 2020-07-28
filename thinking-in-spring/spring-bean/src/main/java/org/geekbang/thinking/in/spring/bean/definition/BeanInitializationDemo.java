package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.definition.factory.DefaultUserFactory;
import org.geekbang.thinking.in.spring.bean.definition.factory.UserFactory;
import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 *
 */
public class BeanInitializationDemo {

    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.Config.class);
        applicationContext.refresh();

        System.out.println(applicationContext.getBean(UserFactory.class));
    }

    @Configuration
    public static class Config{
        @Bean(initMethod = "initUserFactory")
        public UserFactory userFactory(){
            return new DefaultUserFactory();
        }
    }
}
