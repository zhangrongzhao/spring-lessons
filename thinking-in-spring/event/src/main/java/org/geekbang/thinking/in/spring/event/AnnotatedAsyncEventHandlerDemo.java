package org.geekbang.thinking.in.spring.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步事件监听示例
 * @see
 * */
@EnableAsync
public class AnnotatedAsyncEventHandlerDemo {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotatedAsyncEventHandlerDemo.class);

        context.refresh();//初始化ApplicationEventMulticaster

        context.publishEvent(new MySpringEvent("Hello, world"));
        context.close();
    }


    @EventListener
    @Async
    public void onEvent(MySpringEvent event){
        System.out.printf("[线程：%s]onEvent方法监听到事件：%s\n",Thread.currentThread().getName(),event);
    }

    @Bean
    public Executor taskExecutor(){
        ExecutorService taskExecutor = Executors.newSingleThreadExecutor(new CustomizableThreadFactory("my-spring-event-thread-pool-a"));
        return taskExecutor;
    }
}
