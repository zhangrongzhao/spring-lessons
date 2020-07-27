package org.geekbang.thinking.in.spring.ioc.overview.dependency.lookup;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.annotation.Super;
import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找示例
 * **/
public class DependencyLookupDemo {
    public static void main(String[] args){
        //配合xml
        //启动spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-lookup-context.xml");
//        lookupInRealTime(beanFactory);
//        lookupInLazy(beanFactory);
//        lookupByType(beanFactory);
//        lookupByCollectionType(beanFactory);
        lookupByAnnotation(beanFactory);
    }

    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listablebeanFactory=(ListableBeanFactory)beanFactory;
            Map<String,User> users = (Map)listablebeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println(users);
        }
    }

    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listablebeanFactory=(ListableBeanFactory)beanFactory;
            Map<String,User> users = listablebeanFactory.getBeansOfType(User.class);
            System.out.println(users);
        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user=beanFactory.getBean(User.class);
        System.out.println(user);
    }

    private static void lookupInLazy(BeanFactory beanFactory) {
       ObjectFactory<User> objectFactory=(ObjectFactory<User>) beanFactory.getBean("objectFactory");
       User user=objectFactory.getObject();
       System.out.println(user);
    }

    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user=(User) beanFactory.getBean("user");
        System.out.println(user);
    }
}
