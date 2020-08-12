package org.geekbang.thinking.in.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * Bean生命周期示例
 * */
public class BeanLifecycleDemo {
    public static void main(String[] args){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //添加MyInstantiationAwareBeanPostProcessor实现InstantiationAwareBeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        //添加MyDestructionAwareBeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        //添加CommonAnnotationBeanPostProcessor来处理@PostConstruct
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());


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


        //执行Bean销毁(容器内销毁，并不代表java进程内销毁，即不代表被垃圾回收啦。)
        beanFactory.destroyBean("userHolder",userholder);

        //Bean销毁并不意味着垃圾回收
        System.out.println(userholder);
    }
}
