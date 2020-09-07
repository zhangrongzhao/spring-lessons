package org.geekbang.thinking.in.spring.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *  {@link Value} Value注解示例
 * @see Value
 * */
public class ValueAnnotationDemo {

    @Value("${user.name}")
    private String userName;

    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();
        applicationContext.register(ValueAnnotationDemo.class);
        applicationContext.refresh();
        ValueAnnotationDemo demo=applicationContext.getBean(ValueAnnotationDemo.class);
        System.out.println(demo.userName);

        applicationContext.close();
    }
}
