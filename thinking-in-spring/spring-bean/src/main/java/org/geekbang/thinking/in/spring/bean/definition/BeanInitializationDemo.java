package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.definition.factory.DefaultUserFactory;
import org.geekbang.thinking.in.spring.bean.definition.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/*
 *
 */
public class BeanInitializationDemo {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.Config.class);
        System.out.println("--------Spring Ioc Container start----begin-----");
        applicationContext.refresh();
        System.out.println("--------Spring Ioc Container start-----end------");

        System.out.println(applicationContext.getBean(UserFactory.class));

        System.out.println("--------Spring Ioc Container destroy----begin-----");
        applicationContext.close();
        System.out.println("--------Spring Ioc Container destroy-----end----");

        System.out.println("--------garbage collection -----start-----------");
        System.gc();
        Thread.sleep(100);
        System.out.println("--------garbage collection -----end-------------");
    }

    @Configuration
    public static class Config{
        @Bean(initMethod = "customInitUserFactory",destroyMethod = "customDestroy")
        @Lazy(value=false)
        public UserFactory userFactory(){
            return new DefaultUserFactory();
        }
    }
}
