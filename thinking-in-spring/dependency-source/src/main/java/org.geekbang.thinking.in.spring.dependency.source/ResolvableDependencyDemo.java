package org.geekbang.thinking.in.spring.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//import javax.annotation.PostConstruct;

/**
 * ResolvableDependency 作为依赖注入来源
 * **/
public class ResolvableDependencyDemo {

    @Autowired
    private String helloWorld;

    //@PostConstruct
    public void initByInjection(){
        System.out.println(this+"helloWorld="+helloWorld);
    }

    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ResolvableDependencyDemo.class);

        applicationContext.addBeanFactoryPostProcessor(configurableListableBeanFactory->{
            configurableListableBeanFactory.registerResolvableDependency(String.class,"hello world!");
        });
//        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
//        beanFactory.registerResolvableDependency(String.class,"Hello world.");

        applicationContext.refresh();
        applicationContext.close();
    }
}
