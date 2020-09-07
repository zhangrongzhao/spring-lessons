package org.geekbang.thinking.in.spring.environment;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 *  依赖注入 {@link Environment} 示例
 * @see Environment
 * **/
public class LookupEnvironmentDemo implements EnvironmentAware{
    private Environment environment;

    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();
        applicationContext.register(LookupEnvironmentDemo.class);
        applicationContext.refresh();
        LookupEnvironmentDemo demo = applicationContext.getBean(LookupEnvironmentDemo.class);

        //通过Environment Bean名称依赖查找。
        Environment environment = applicationContext.getBean(ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME,Environment.class);
        System.out.println(environment);
        System.out.println(demo.environment);
        System.out.println(environment==demo.environment);

        applicationContext.close();

    }

    @Override
    public void setEnvironment(Environment environment) {
         this.environment=environment;
    }


}
