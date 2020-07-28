package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanInstantiationDemo {
    public static void main(String[] args){
         ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");

         User user1 = applicationContext.getBean("user-by-static-method",User.class);
         User user2 = applicationContext.getBean("user-by-instance-method",User.class);
         User user3 = applicationContext.getBean("user-by-factory-bean",User.class);

         System.out.println(user1==user2);
         System.out.println(user3);
    }
}
