package org.geekbang.thinking.in.spring.bean.lifecycle;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.Bean;

public class AnnotatedBeanDefinitionParsingDemo {

    @Bean
    public User user() {
        return User.createInstance();
    }

    public static void main(String[] args) {
        //创建BeanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);
        //注册当前类：AnnotatedBeanDefinitionParsingDemo
        int beanDefinitionBefore = beanFactory.getBeanDefinitionCount();
        //注册当前普通的类作为非 @Component class
        reader.register(AnnotatedBeanDefinitionParsingDemo.class);
        int beanDefinitionAfter = beanFactory.getBeanDefinitionCount();

        int beanDefinitionCount = beanDefinitionAfter - beanDefinitionBefore;
        System.out.println(beanDefinitionCount);
        //普通的class 作为 Component注册到spring IoC容器，通常Bean名称为 annotatedBeanDefinitionParsingDemo
        //Bean 名称生成来自于BeanNameGenerator,注解的实现AnnotatedBeanNameGenerator
        AnnotatedBeanDefinitionParsingDemo demo = beanFactory.getBean("annotatedBeanDefinitionParsingDemo", AnnotatedBeanDefinitionParsingDemo.class);
        System.out.println(demo);
    }
}
