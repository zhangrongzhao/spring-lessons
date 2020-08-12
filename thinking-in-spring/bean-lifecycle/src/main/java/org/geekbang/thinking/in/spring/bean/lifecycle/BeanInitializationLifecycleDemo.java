package org.geekbang.thinking.in.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
*   Bean 初始化 Demo
* */
public class BeanInitializationLifecycleDemo {
    public static void main(String[] args){
        executeBeanFactory();
    }

    public static void executeBeanFactory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //添加MyInstantiationAwareBeanPostProcessor实现InstantiationAwareBeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        //添加CommonAnnotationBeanPostProcessor来处理@PostConstruct
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        //添加MyDestructionAwareBeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());

        String[] locations = {"/META-INF/dependency-lookup-context.xml", "META-INF/bean-lifecycle.xml"};
        XmlBeanDefinitionReader beanDefinitionReader=new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions(locations);

        //SmartInitializingSingleton 通常在
        //preInstantiateSingletons将已注册的BeanDefinition初始化成spring bean。
        //No ConfigurableListableBeanFactory set?????????
        //beanFactory.preInstantiateSingletons();

        int beanDefinitionBefore = beanFactory.getBeanDefinitionCount();
        int beanDefinitionAfter = beanFactory.getBeanDefinitionCount();
        int beanDefinitionCount = beanDefinitionAfter - beanDefinitionBefore;
        System.out.println("加载的BeanDefinition数量：" + beanDefinitionCount);

//        User user = beanFactory.getBean("user", User.class);
//        System.out.println(user);
//
//        SuperUser superUser = (SuperUser) beanFactory.getBean("superUser", User.class);
//        System.out.println(superUser);

        //构造器注入按照类型注入，resolveDependency
        UserHolder userholder = beanFactory.getBean("userHolder", UserHolder.class);

        System.out.println(userholder);
    }

}