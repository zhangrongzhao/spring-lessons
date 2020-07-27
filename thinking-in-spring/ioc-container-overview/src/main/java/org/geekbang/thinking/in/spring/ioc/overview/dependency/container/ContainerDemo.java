package org.geekbang.thinking.in.spring.ioc.overview.dependency.container;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.configuration.Config;
import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ContainerDemo {
    public static void main(String[] args){
        iocContainerByAnnotationCconfig();
    }

    public static void iocContainer1(){
        //创建BeanFactory容器
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();

        //
        XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(beanFactory);
        String path="META-INF/dependency-lookup-context.xml";
        int beanDefinitionCount =  reader.loadBeanDefinitions(path);
        System.out.println(beanDefinitionCount);
    }

    public static void iocContainerByAnnotationCconfig(){
        //注解方式
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Config.class);
        applicationContext.refresh();

        User user = applicationContext.getBean(User.class);
        System.out.println( user);
        applicationContext.close();
    }
}
