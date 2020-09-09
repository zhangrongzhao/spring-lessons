package org.geekbang.thinking.in.spring.application.context.lifecycle;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.Lifecycle;
import org.springframework.context.support.GenericApplicationContext;

/**
 * {@link Lifecycle} 示例
 * @see Lifecycle
 * **/
public class MyLifecycleDemo {
    public static void main(String[] args){
        GenericApplicationContext applicationContext=new GenericApplicationContext();
        //applicationContext.registerBean(MyLifecycle.class);
        applicationContext.registerBeanDefinition("myLifecycle", BeanDefinitionBuilder.rootBeanDefinition(MyLifecycle.class).getBeanDefinition());
        applicationContext.refresh();

        applicationContext.start();
        applicationContext.stop();

        applicationContext.close();
    }
}
