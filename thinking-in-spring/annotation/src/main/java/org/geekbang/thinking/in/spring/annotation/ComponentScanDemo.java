package org.geekbang.thinking.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


/**
 * 组件扫描示例
 * {@link Component} 扫描示例
 * @see Component
 * @see ComponentScan
 * */
//basePackages() @AliasFor Value()
//Value() @AliasFor basePackages()
//@ComponentScan(basePackages="org.geekbang.thinking.in.spring.annotation")//指定Class-path
//@ComponentScan(value="org.geekbang.thinking.in.spring.annotation")//指定Class-path
//@MyComponentScan(scanBasePackages="org.geekbang.thinking.in.spring.annotation")//指定Class-path
@MyComponentScan2(basePackages = "org.geekbang.thinking.in.spring.annotation")
public class ComponentScanDemo {
    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();

        //注册Configuration class
        applicationContext.register(ComponentScanDemo.class);
        applicationContext.refresh();

        //@MyComponent2 <- @MyComponent <- @Component
        //since spring 4.0 开始支持多层次@Component"派生"
        TestClass testClass = applicationContext.getBean(TestClass.class);
        System.out.println(testClass);

        applicationContext.close();
    }
}
