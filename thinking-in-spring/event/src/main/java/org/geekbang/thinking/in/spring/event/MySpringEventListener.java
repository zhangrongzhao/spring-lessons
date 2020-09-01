package org.geekbang.thinking.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 自定义事件监听器
 * @see ApplicationListener
 * **/
public class MySpringEventListener implements ApplicationListener<MySpringEvent> {

    @Override
    public void onApplicationEvent(MySpringEvent event) {
        System.out.printf("[线程：%s]监听到事件：%s\n",Thread.currentThread().getName(),event);
    }
}
