package org.geekbang.thinking.in.spring.bean.lifecycle;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * BeanDefinition合并示例
 * */
public class BeanConstructorDependencyInjectionDemo {
    public static void main(String[] args) {
        //beanConstructorDependencyInjection();
        //executeBeanFactory();
        //System.out.println("================================================================");
        executeApplicationContext();
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


    public static void executeBeanFactory(){
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        //方法一：添加MyInstantiationAwareBeanPostProcessor实现InstantiationAwareBeanPostProcessor
        //beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        //方法二：将MyInstantiateAwareBeanPostProcessor作为Bean注册

        String[] locations = {"/META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        XmlBeanDefinitionReader beanDefinitionReader=new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions(locations);

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

    public  static void executeApplicationContext(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        String[] locations = {"/META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        applicationContext.setConfigLocations(locations);
        applicationContext.refresh();

        int beanDefinitionBefore = applicationContext.getBeanDefinitionCount();
        int beanDefinitionAfter = applicationContext.getBeanDefinitionCount();
        int beanDefinitionCount = beanDefinitionAfter - beanDefinitionBefore;
        System.out.println("加载的BeanDefinition数量：" + beanDefinitionCount);

//        User user = applicationContext.getBean("user", User.class);
//        System.out.println(user);
//
//        SuperUser superUser = (SuperUser) applicationContext.getBean("superUser", User.class);
//        System.out.println(superUser);

        //构造器注入按照类型注入，resolveDependency
        UserHolder userholder = applicationContext.getBean("userHolder", UserHolder.class);
        System.out.println(userholder);

        applicationContext.close();

    }
}
