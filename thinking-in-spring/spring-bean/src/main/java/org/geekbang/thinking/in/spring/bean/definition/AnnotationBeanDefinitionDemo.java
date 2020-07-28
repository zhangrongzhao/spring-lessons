package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.configuration.Config;
import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.SuperUser;
import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * 注解Bean定义示例
 * **/

@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();
        //@Bean
        //1.configuration class
        //applicationContext.register(AnnotationBeanDefinitionDemo.class);

        //2.Component
        //applicationContext.register(ConfigComponent.class);

        registerBeanDefinition(applicationContext,"mercy-user");
        registerBeanDefinition(applicationContext);

        //3.import
        applicationContext.refresh();
        //Map<String,Config> configs = applicationContext.getBeansOfType(Config.class);

        //System.out.println(applicationContext.getBeansOfType(Config.class));
        System.out.println(applicationContext.getBeansOfType(User.class));
        //User user=applicationContext.getBean(SuperUser.class);
        //User user=applicationContext.getBean("admin",User.class);
        //System.out.println(user);
        applicationContext.close();
    }

    /**命名方式***/
    public static void registerBeanDefinition(BeanDefinitionRegistry registry,String beanName){
        BeanDefinitionBuilder builder = genericBeanDefinition(User.class);
        builder.addPropertyValue("id",3)
               .addPropertyValue("name","小马哥3");

        if(StringUtils.hasText(beanName)){
            registry.registerBeanDefinition(beanName,builder.getBeanDefinition());
        }else{
            BeanDefinitionReaderUtils.registerWithGeneratedName(builder.getBeanDefinition(),registry);
        }
    }

    public static void registerBeanDefinition(BeanDefinitionRegistry registry){
        registerBeanDefinition(registry,null);
    }

    @Component
    public static class Config {

        @Bean(name={"user","admin"})
        public User getUser(){
            User user=new User();
            user.setId(2L);
            user.setName("小马哥2");
            return user;
        }
    }
}
