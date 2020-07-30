package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.definition.factory.DefaultUserFactory;
import org.geekbang.thinking.in.spring.bean.definition.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
*Bean 单例注册示例
*/
public class BeanSingletonRegistryDemo {

    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();
        applicationContext.refresh();

        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
        UserFactory userFactory = new DefaultUserFactory();
        if(beanFactory instanceof SingletonBeanRegistry){
            SingletonBeanRegistry registry = (SingletonBeanRegistry)beanFactory;
            registry.registerSingleton("userFactory",userFactory);
        }
        System.out.println(applicationContext.getBean("userFactory"));
        System.out.println(userFactory);

        System.out.println("userFactory(IoC container)==userFactory(new)："+(userFactory==applicationContext.getBean("userFactory")));
    }
}
