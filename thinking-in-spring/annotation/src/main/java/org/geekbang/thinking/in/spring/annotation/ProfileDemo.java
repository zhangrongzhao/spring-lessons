package org.geekbang.thinking.in.spring.annotation;

import org.springframework.context.annotation.*;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

/**
 *{@link  Profile} 示例
 * @see Profile
 * @see Environment#getActiveProfiles()
 * **/
@Configuration
public class ProfileDemo {
    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();

        //注册Configuration class
        applicationContext.register(ProfileDemo.class);

        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        environment.setDefaultProfiles("odd");
        //environment.setActiveProfiles("even");

        //spring.profiles.active = even
        //-Dspring.profiles.active=even

        applicationContext.refresh();

        Integer number = applicationContext.getBean("number",Integer.class);
        System.out.println(number);

        applicationContext.close();
    }

    @Bean(name="number")
    @Profile("odd")//奇数
    public Integer odd(){
        return 1;
    }

    @Bean(name="number")
    //@Profile("even")//偶数
    @Conditional(EvenProfileCondition.class)
    public Integer even(){
        return 2;
    }
}
