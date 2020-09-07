package org.geekbang.thinking.in.spring.environment;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/***
 *  属性占位符 {@link PropertySourcesPlaceholderConfigurer} 示例
 *
 * @see PropertySourcesPlaceholderConfigurer
 * */
public class PropertySourcesPlaceholderConfigurerDemo {
    public static void main(String[] args){
        //创建并启动应用上下文
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("META-INF/placeholder-resolver.xml");

        User user=applicationContext.getBean("user",User.class);
        System.out.println(user);
        //关闭spring 应用上下文
        applicationContext.close();

    }
}
