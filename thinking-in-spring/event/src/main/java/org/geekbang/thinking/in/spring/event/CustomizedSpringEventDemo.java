package org.geekbang.thinking.in.spring.event;

import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 自定义事件 示例
 * @see ApplicationEvent
 * **/
public class CustomizedSpringEventDemo implements ApplicationEventPublisherAware, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private ApplicationEventPublisher applicationEventPublisher;

    public static void main(String[] args){
        GenericApplicationContext context = new GenericApplicationContext();
        context.addApplicationListener(new MySpringEventListener());

        context.refresh();
        context.publishEvent(new MySpringEvent("hello,world"));

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
