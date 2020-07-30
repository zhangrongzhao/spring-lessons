package org.geekbang.thinking.in.spring.dependency.lookup;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
* 类型安全的依赖查找demo
* */
public class TypeSafeDependencyLookupDemo {
    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TypeSafeDependencyLookupDemo.class);
        applicationContext.refresh();

        /*演示BeanFactory.getBean()的安全性： 不安全 */
        displayBeanFactoryGetBean(applicationContext);
        /*演示ObjectFactory.getObject()的安全性：不安全*/
        displayObjectFactoryGetObject(applicationContext);
        /*演示ObjectProvider.getIfAvaliable()的安全性 ： 安全*/
        displayObjectProviderIfAvaliable(applicationContext);

        /*演示ListableBeanFactory.getBeansOfType的安全性*/
        displayListableBeanFactoryGetBeansOfType(applicationContext);

        displayObjectProviderStreamOps(applicationContext);

        applicationContext.close();
    }

    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> objectFactory=applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderStreamOps",()->{objectFactory.stream().forEach(System.err::print); });
    }

    private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory beanFactory) {
        printBeansException("displayListableBeanFactoryGetBeansOfType",()->{beanFactory.getBeansOfType(User.class);});
    }

    /*验证ObjectProvider的安全性*/
    private static void displayObjectProviderIfAvaliable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> objectFactory = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetObject",()->{objectFactory.getIfAvailable();});
    }

    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext){
        //ObjectProvider is ObjectFactory
        ObjectFactory<User> objectFactory = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetObject",()->{objectFactory.getObject();});
    }

    public static void displayBeanFactoryGetBean(BeanFactory beanFactory){
        printBeansException("displayBeanFactoryGetBean",()->{beanFactory.getBean(User.class);});
    }

    private static void printBeansException(String source,Runnable task){
        System.err.println("===============================================================================");
        System.err.println("Source from:"+source);
        try{
            task.run();
        }catch(BeansException ex){
            ex.printStackTrace();
        }
    }
}
