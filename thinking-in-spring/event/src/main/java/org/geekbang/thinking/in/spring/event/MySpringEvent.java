package org.geekbang.thinking.in.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义
 * **/
public class MySpringEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param message the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MySpringEvent(Object message) {
        super(message);
    }

    public String getSource(){
        return (String)super.getSource();
    }

    public String getMessage(){
        return getSource();
    }
}
