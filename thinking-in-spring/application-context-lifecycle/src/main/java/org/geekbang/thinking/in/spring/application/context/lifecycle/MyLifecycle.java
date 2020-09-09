package org.geekbang.thinking.in.spring.application.context.lifecycle;

import org.springframework.context.Lifecycle;

/**
 * 自定义生命周期{@link Lifecycle}
 *
 * **/
public class MyLifecycle implements Lifecycle {

    private boolean running=false;

    @Override
    public void start() {
        this.running=true;
        System.out.println("----MyLifecycle----启动----");
    }

    @Override
    public void stop() {
        this.running=false;
        System.out.println("----MyLifecycle----结束----");
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
