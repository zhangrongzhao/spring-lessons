package org.geekbang.thinking.in.spring.application.context.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.LiveBeansView;

import static org.springframework.context.support.LiveBeansView.MBEAN_DOMAIN_PROPERTY_NAME;

/**
 * {@link LiveBeansView} 示例
 * @see LiveBeansView
 * */
public class LiveBeansViewDemo {
    public static void main(String[] args){
        //添加LiveBeanViews的ObjectName的Domain
        System.setProperty(MBEAN_DOMAIN_PROPERTY_NAME,"org.geekbang.thinking.in.spring");
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();

        applicationContext.register(LiveBeansViewDemo.class);

        applicationContext.refresh();
        applicationContext.close();
    }
}
