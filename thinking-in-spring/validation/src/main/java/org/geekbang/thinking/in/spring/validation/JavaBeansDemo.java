package org.geekbang.thinking.in.spring.validation;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.stream.Stream;

/**
 * Java beans 示例
 * **/
public class JavaBeansDemo {
    public static void main(String[] args) throws IntrospectionException {
        //stopClass排除类（截止）
        BeanInfo beanInfo= Introspector.getBeanInfo(User.class,Object.class);

        //所有的Java类均继承 java.lang.Object
        //class 属性来自于Object#getClass() 方法
        //属性描述符PropertyDescriptor
        Stream.of(beanInfo.getPropertyDescriptors()).forEach((propertyDescriptor)->{
            //propertyDescriptor.getReadMethod();//get 方法
            //propertyDescriptor.getWriteMethod(); //set 方法
            System.out.println(propertyDescriptor);
        });

        Stream.of(beanInfo.getMethodDescriptors()).forEach(methodDescriptor->{
            System.out.println(methodDescriptor);
        });
    }
}
