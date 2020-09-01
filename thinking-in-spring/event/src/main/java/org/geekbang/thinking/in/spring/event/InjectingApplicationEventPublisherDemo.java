package org.geekbang.thinking.in.spring.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import javax.annotation.PostConstruct;

/**
 * 依赖注入事件发布器示例
 * @see ApplicationEvent
 * **/
public class InjectingApplicationEventPublisherDemo implements ApplicationEventPublisherAware, ApplicationContextAware, BeanPostProcessor {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void init(){
        //#3
        applicationEventPublisher.publishEvent(new MySpringEvent("The event from @Autowired ApplicationEventPublisher"));

        //#4
        applicationEventPublisher.publishEvent(new MySpringEvent("The event from @Autowired ApplicationContext"));
    }

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.addApplicationListener(new MySpringEventListener());
        context.register(InjectingApplicationEventPublisherDemo.class);

        context.refresh();
        //context.publishEvent(new MySpringEvent("hello,world"));

        context.close();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {//#1
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {//#2
        this.applicationContext = applicationContext;
    }
}
