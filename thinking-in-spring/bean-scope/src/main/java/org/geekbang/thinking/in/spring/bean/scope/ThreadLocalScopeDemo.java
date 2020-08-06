package org.geekbang.thinking.in.spring.bean.scope;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * 线程作用域范围的Demo： thread local scope demo 示例
 *
 * */
public class ThreadLocalScopeDemo {
    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME)
    public User user() {
        return User.createInstance();
    }

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ThreadLocalScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
        });

        applicationContext.refresh();

        customScopedBeansByInjection(applicationContext);

        applicationContext.close();
    }

    private static void customScopedBeansByInjection(AnnotationConfigApplicationContext applicationContext) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Thread thread=new Thread(()->{
                System.out.printf("Thread ["+Thread.currentThread().getId()+"] demo.user[" + applicationContext.getBean(User.class).getBeanName() + "]:{hashCode：" + applicationContext.getBean(User.class).hashCode()+"}");
                System.out.println();
            });
            thread.start();
            thread.join();
        }
    }
}
