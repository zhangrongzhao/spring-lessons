package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.definition.factory.DefaultUserFactory;
import org.geekbang.thinking.in.spring.bean.definition.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
*Bean 单例注册示例
*/
public class BeanSingletonRegistryDemo {

    public static void main(String[] args){
        /*1.BeanDefinitionRegistry  Bean定义登记簿*/
        /*2.SingletonRegistry 单例登记簿*/
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        UserFactory userFactory = new DefaultUserFactory();
        beanFactory.registerSingleton("userFactory",userFactory);
        applicationContext.refresh();

        System.out.println(applicationContext.getBean("userFactory"));
        System.out.println(userFactory);

        System.out.println("userFactory(IoC container)==userFactory(new)："+(userFactory==applicationContext.getBean("userFactory")));
    }
}
