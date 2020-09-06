package org.geekbang.thinking.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring 注解属性覆盖 示例
 * ***/

@MyComponentScan2(packages = "org.geekbang.thinking.in.spring.annotation")
public class AttributeOverridesDemo {

    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();

        //注册Configuration class
        applicationContext.register(AttributeOverridesDemo.class);
        applicationContext.refresh();

        //@MyComponent2 <- @MyComponent <- @Component
        //since spring 4.0 开始支持多层次@Component"派生"
        TestClass testClass = applicationContext.getBean(TestClass.class);
        System.out.println(testClass);

        applicationContext.close();
    }
}
