package org.geekbang.thinking.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *  Enable模块驱动示例
 * **/
@EnableHelloWorld
public class EnableModuleDemo {
    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();

        //注册Configuration class
        applicationContext.register(EnableModuleDemo.class);
        applicationContext.refresh();

        //@MyComponent2 <- @MyComponent <- @Component
        //since spring 4.0 开始支持多层次@Component"派生"
        String helloWorld = applicationContext.getBean("helloWorld",String.class);
        System.out.println(helloWorld);

        applicationContext.close();
    }
}
