package org.geekbang.thinking.in.spring.conversion;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.PropertyEditor;

/**
 *  Spring 自定义{@link PropertyEditor}
 * **/
public class SpringCustomizedPropertyEditorDemo {

    public static void main(String[] args){
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/property-editors-context.xml");

        //AbstractApplicationContext->"conversionService" ConversionService Bean
        //->ConfigurableBeanFactory#setConversionService(conversionService)
        //->AbstractAutowiredBeanFactory.instantiateBean
        //->AbstractBeanFactory#getConversionService->BeanWrapper
        //BeanDefinition->BeanWrapper->属性转换（数据来源） PropertyValues
        //setPropertyValues(PropertyValues)->typeConverter#convertIfNecessnary
        //TypeConverterDelegate#convertIfNecessary->PropertyEditor or ConversionService

        User user = applicationContext.getBean("user",User.class);
        System.out.println(user);

        applicationContext.close();
    }
}
