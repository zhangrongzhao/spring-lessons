package org.geekbang.thinking.in.spring.bean.definition.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    public DefaultUserFactory(){
        System.out.println("0.first step:DefaultUserFactory 无参构造函数执行完成--实例化完成");
    }

    //1.基于 @PostConstruct 注解实现
    @PostConstruct
    public void postConstruct(){
        System.out.println("1.second step:@PostConstruct : userFactory 实例化后置初始化");
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("2.third step:afterPropertiesSet（InitializingBean）：userFactory初始化中...");
    }
    public void customInitUserFactory(){
        System.out.println("3.fourth step:自定义初始化方法：customInitUserFactory...");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("4.preDestroy step:准备销毁...");
    }
    @Override
    public void destroy() throws Exception {
        System.out.println("5.destroy step:销毁中...");
    }
    public void customDestroy(){
        System.out.println("6.custom destroy step:自定义销毁...");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("7.GC...垃圾回收中...");
    }
}
