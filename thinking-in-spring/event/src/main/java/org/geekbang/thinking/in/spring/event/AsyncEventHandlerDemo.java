package org.geekbang.thinking.in.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步事件监听示例
 * @see
 * */
public class AsyncEventHandlerDemo {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.addApplicationListener(new MySpringEventListener());
        context.register(AsyncEventHandlerDemo.class);

        context.refresh();//初始化ApplicationEventMulticaster
        //依赖查找ApplicationEventMulticaster
        ApplicationEventMulticaster applicationEventMulticaster =
                context.getBean(AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME,ApplicationEventMulticaster.class);

        //判断当前的applicationEventMulticaster 是否为 SimpleApplicationEventMulticaster
        if(applicationEventMulticaster instanceof SimpleApplicationEventMulticaster){
            SimpleApplicationEventMulticaster simpleApplicationEventMulticaster=(SimpleApplicationEventMulticaster)applicationEventMulticaster;
            ExecutorService taskExecutor = Executors.newSingleThreadExecutor(new CustomizableThreadFactory("my-spring-event-thread-pool"));
            //同步->异步
            simpleApplicationEventMulticaster.setTaskExecutor(taskExecutor);
            simpleApplicationEventMulticaster.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
                @Override
                public void onApplicationEvent(ContextClosedEvent event) {
                    if(!taskExecutor.isShutdown()){
                        taskExecutor.shutdown();
                    }
                }
            });
            simpleApplicationEventMulticaster.setErrorHandler(e -> {
                System.err.println("当Spring事件异常时，原因：" + e.getMessage());
            });
        }

        context.addApplicationListener(new ApplicationListener<MySpringEvent>() {
            @Override
            public void onApplicationEvent(MySpringEvent event) {
                 throw new RuntimeException("故意抛出异常");
            }
        });

        context.publishEvent(new MySpringEvent("Hello, world"));
        context.close();
    }
}
