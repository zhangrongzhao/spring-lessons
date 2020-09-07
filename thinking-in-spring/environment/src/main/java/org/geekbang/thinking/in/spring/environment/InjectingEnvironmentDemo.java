package org.geekbang.thinking.in.spring.environment;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 *  依赖注入 {@link Environment} 示例
 * @see Environment
 * **/
public class InjectingEnvironmentDemo implements EnvironmentAware, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private Environment environment;

    @Autowired
    private Environment environment2;

    @Autowired
    private ApplicationContext applicationContext2;

    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();
        applicationContext.register(InjectingEnvironmentDemo.class);
        applicationContext.refresh();
        InjectingEnvironmentDemo demo=applicationContext.getBean(InjectingEnvironmentDemo.class);
        System.out.println(demo.environment);
        System.out.println(demo.environment2);
        System.out.println(demo.environment==demo.environment2);
        System.out.println(demo.applicationContext.getEnvironment()==demo.environment);
        System.out.println(demo.applicationContext2.getEnvironment()==demo.environment);
        System.out.println(demo.applicationContext.getEnvironment()==demo.environment2);
        System.out.println(demo.applicationContext2.getEnvironment()==demo.environment2);
        System.out.println(demo.applicationContext.getEnvironment()==demo.applicationContext2.getEnvironment());
        System.out.println(demo.applicationContext==demo.applicationContext2);
        System.out.println(demo.applicationContext==applicationContext);
        System.out.println(demo.applicationContext2==applicationContext);
        applicationContext.close();

    }

    @Override
    public void setEnvironment(Environment environment) {
         this.environment=environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
