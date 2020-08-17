package org.geekbang.thinking.in.spring.configuration.metadata;


import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.SuperUser;
import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

/**
 * Properties bean definition reader demo
 * {@Link PropertiesBeanDefinitionReader }
 *
 * */
public class PropertiesBeanDefinitionReaderDemo {
    public static void main(String[] args){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //BeanDefinitionRegistry registry = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        //Properties 资源加载默认通过ISO-8859-1编码（ASC）
        String configFileLocation= "classpath:/META-INF/user-bean-definitions.properties";
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(configFileLocation);
        //Resource resource = new ClassPathResource(configFileLocation);
        EncodedResource encodedResource = new EncodedResource(resource,"utf-8");
        beanDefinitionReader.loadBeanDefinitions(encodedResource);

        User user = beanFactory.getBean("user",User.class);
        System.out.println(user);

        SuperUser superUser = beanFactory.getBean("superUser",SuperUser.class);
        System.out.println(superUser);
    }
}
