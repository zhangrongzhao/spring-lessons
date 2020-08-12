package org.geekbang.thinking.in.spring.bean.lifecycle;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化demo
 * **/
public class BeanInstantiateLifecycleDemo {
    public static void main(String[] args){
        beanConstructorDependencyInjection();
    }

    public static void beanConstructorDependencyInjection(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        //方法一：添加MyInstantiationAwareBeanPostProcessor实现InstantiationAwareBeanPostProcessor
        //beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        //方法二：将MyInstantiateAwareBeanPostProcessor作为Bean注册

        String[] locations = {"/META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        applicationContext.setConfigLocations(locations);
        applicationContext.refresh();

        int beanDefinitionBefore = beanFactory.getBeanDefinitionCount();
        int beanDefinitionAfter = beanFactory.getBeanDefinitionCount();
        int beanDefinitionCount = beanDefinitionAfter - beanDefinitionBefore;
        System.out.println("加载的BeanDefinition数量：" + beanDefinitionCount);

//        User user = beanFactory.getBean("user", User.class);
//        System.out.println(user);
//
//        SuperUser superUser = (SuperUser) beanFactory.getBean("superUser", User.class);
//        System.out.println(superUser);

//        //构造器注入按照类型注入，resolveDependency
        UserHolder userholder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userholder);

        applicationContext.close();
    }


}
