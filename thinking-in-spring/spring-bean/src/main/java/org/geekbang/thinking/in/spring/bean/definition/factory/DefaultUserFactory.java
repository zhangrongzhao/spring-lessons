package org.geekbang.thinking.in.spring.bean.definition.factory;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

public class DefaultUserFactory implements UserFactory, InitializingBean {
    //1.基于 @PostConstruct 注解实现
    @PostConstruct
    public void init(){
       System.out.println("@PostConstruct userFactory 初始化中...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet：userFactory初始化中...");
    }

    public void initUserFactory(){
       System.out.println("自定义初始化方法：initUserFactory...");
    }
}
