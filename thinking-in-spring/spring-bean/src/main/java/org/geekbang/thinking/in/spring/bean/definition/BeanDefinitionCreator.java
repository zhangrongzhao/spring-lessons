package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * Bean定义创建Demo
 * **/
public class BeanDefinitionCreator {

    public static void main(String[] args){
        // 1.by BeanDefinitionBuilder
       BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
//       builder.addPropertyValue("id",1);
//       builder.addPropertyValue("name","小马哥");
        builder.addPropertyValue("id",1)
               .addPropertyValue("name","小马哥");

       //获取bean实例
       BeanDefinition beanDefinition = builder.getBeanDefinition();
       //这里的BeanDefinition并非最终状态，可以自定义修改

        //可以通过AbstractBeanDefinition派生
        GenericBeanDefinition genericBeanDefinition=new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);

        //
        MutablePropertyValues propertyValues = new MutablePropertyValues();
//        propertyValues.addPropertyValue("id",1);
//        propertyValues.addPropertyValue("name","小马哥");

        genericBeanDefinition.setPropertyValues(propertyValues);

    }

}
