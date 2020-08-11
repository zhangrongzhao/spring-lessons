package org.geekbang.thinking.in.spring.bean.lifecycle;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.SuperUser;
import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * BeanDefinition合并示例
 * */
public class BeanConstructorDependencyInjectionDemo {
    public static void main(String[] args) {
        //创建BeanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //基于XML资源的BeanDefinitionReader
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"/META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        int beanDefinitionBefore = beanFactory.getBeanDefinitionCount();
        beanDefinitionReader.loadBeanDefinitions(locations);
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
