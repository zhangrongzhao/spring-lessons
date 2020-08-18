package org.geekbang.thinking.in.spring.configuration.metadata;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 扩展XML 示例
 * */
public class ExtensibleXmlAuthoringDemo {
    public static void main(String[] args){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/user-context.xml");
        User user = beanFactory.getBean(User.class);
        System.out.println(user);
    }
}
