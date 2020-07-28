package org.geekbang.thinking.in.spring.bean.definition.lookup;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean别名示例
 *
 *
 * **/
public class BeanAliasDemo {
    public static void main(String[] args){
        BeanFactory beanFactory=new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition-context.xml");
        User user_Xiaomage = beanFactory.getBean("xiaomage-user",User.class);
        User user=beanFactory.getBean("user",User.class);
        System.out.println(user_Xiaomage);
        System.out.println(user);
        System.out.println(user_Xiaomage==user);
    }

}
