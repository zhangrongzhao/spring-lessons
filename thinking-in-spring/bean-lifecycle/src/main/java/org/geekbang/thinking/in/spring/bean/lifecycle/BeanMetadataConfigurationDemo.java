package org.geekbang.thinking.in.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * Bean 元信息配置
 * */
public class BeanMetadataConfigurationDemo {
    public static void main(String[] args){
        //创建BeanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);
        String path="META-INF/user.properties";
        Resource resource = new ClassPathResource(path);
        EncodedResource encodedResource = new EncodedResource(resource,"utf-8");
        int beanDefinitionNumbers =  reader.loadBeanDefinitions(encodedResource);
        System.out.println(beanFactory.getBean("user"));
    }
}
