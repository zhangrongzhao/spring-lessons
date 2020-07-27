package org.geekbang.thinking.in.spring.ioc.overview.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.geekbang.thinking.in.spring.ioc.overview.dependency.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 依赖注入DEMO
 * **/
public class DependencyInjectionDemo {
    public static void main(String[] args){
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        UserRepository userRepository=beanFactory.getBean(UserRepository.class);
//        System.out.println(userRepository.getUsers());

//        System.out.println(userRepository.getBeanFactory());
//        System.out.println(userRepository.getBeanFactory()==beanFactory);
//        System.out.println(beanFactory.getBean(BeanFactory.class));

        ObjectFactory objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject());
        System.out.println(beanFactory);
        System.out.println(objectFactory.getObject()==beanFactory);
    }
}
