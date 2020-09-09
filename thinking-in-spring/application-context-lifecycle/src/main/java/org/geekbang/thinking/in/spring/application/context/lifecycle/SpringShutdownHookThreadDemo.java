package org.geekbang.thinking.in.spring.application.context.lifecycle;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

/**
 * spring shutdown Hook  示例
 * */
public class SpringShutdownHookThreadDemo {
    public static void main(String[] args) throws IOException {
        GenericApplicationContext applicationContext=new GenericApplicationContext();
        applicationContext.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
            @Override
            public void onApplicationEvent(ContextClosedEvent event) {
                System.out.printf("[线程 %s]收到ContextClosedEvent事件\n",Thread.currentThread().getName());
            }
        });
        applicationContext.refresh();

        applicationContext.registerShutdownHook();
        //按任意键继续并且关闭Spring应用上下文
        System.out.println("===按任意键继续并且关闭Spring应用上下文===");
        System.in.read();

        //同步关闭
        applicationContext.close();
    }
}
