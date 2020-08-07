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
public class MergedBeanDefinitionDemo {
    public static void main(String[] args) {
        //创建BeanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //基于XML资源的BeanDefinitionReader
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String path="/META-INF/dependency-lookup-context.xml";
        Resource resource = new ClassPathResource(path);
        EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");

        int beanDefinitionBefore=beanFactory.getBeanDefinitionCount();
        reader.loadBeanDefinitions(encodedResource);
        int beanDefinitionAfter=beanFactory.getBeanDefinitionCount();
        int beanDefinitionCount=beanDefinitionAfter-beanDefinitionBefore;
        System.out.println("加载的BeanDefinition数量："+beanDefinitionCount);

        User user=beanFactory.getBean("user",User.class);
        System.out.println(user);

        SuperUser superUser= (SuperUser) beanFactory.getBean("superUser",User.class);
        System.out.println(superUser);
    }
}
