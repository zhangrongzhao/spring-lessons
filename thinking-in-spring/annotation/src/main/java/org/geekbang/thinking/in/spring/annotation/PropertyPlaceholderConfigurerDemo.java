package org.geekbang.thinking.in.spring.annotation;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/***
 * {@link PropertyPlaceholderConfigurer} 示例
 * @see PropertyPlaceholderConfigurer
 *
 * **/
public class PropertyPlaceholderConfigurerDemo {

    public static void main(String[] args){
        //创建并启动应用上下文
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("META-INF/placeholder-resolver.xml");

        User user=applicationContext.getBean("user",User.class);
        System.out.println(user);
        //关闭spring 应用上下文
        applicationContext.close();
    }
}
