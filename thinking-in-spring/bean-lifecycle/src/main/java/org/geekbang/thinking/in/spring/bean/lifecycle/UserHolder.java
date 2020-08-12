package org.geekbang.thinking.in.spring.bean.lifecycle;


import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * User holder 类
 * */
public class UserHolder implements InitializingBean, BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware {
    private User user;
    private Integer number;
    private String description;

    private String beanName;
    private ClassLoader classLoader;
    private BeanFactory beanFactory;
    private Environment environment;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", beanName='" + beanName + '\'' +
                '}';
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 依赖注解驱动
     * 当前场景：BeanFactory
     * **/
    @PostConstruct
    public void initPostConstruct(){
        //postProcessBeforeInitialization:v3->v4
        this.setDescription("The user description:v4");
        System.out.println("initPostConstruct()=" + this.getDescription());
    }

    @Override
    public void afterPropertiesSet(){
         //@PostConstruct:v4->v5
         this.setDescription("The user description:v5");
         System.out.println("afterPropertiesSet()="+this.getDescription());
    }

    /**自定义初始化方法*/
    public void customInitMethod(){
        //InitializingBean.afterPropertiesSet():v5->v6
         this.setDescription("The user description:v6");
         System.out.println("customInitMethod()="+this.getDescription());
    }
}
