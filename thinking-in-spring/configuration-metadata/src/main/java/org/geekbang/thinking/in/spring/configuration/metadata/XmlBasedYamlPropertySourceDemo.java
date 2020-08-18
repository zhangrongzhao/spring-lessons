package org.geekbang.thinking.in.spring.configuration.metadata;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * 基于XML的 YAML 外部配置化示例
 * **/
public class XmlBasedYamlPropertySourceDemo {
    public static void main(String[] args){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/yaml-property-source-context.xml");
        //获取 YAML 对象
        Map<String,Object>  user = beanFactory.getBean("yamlMap",Map.class);
        System.out.println(user);
    }
}
