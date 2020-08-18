package org.geekbang.thinking.in.spring.configuration.metadata;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 外部化配置示例
 * **/
@PropertySource("classpath:/META-INF/user-bean-definition.properties")
public class PropertySourceDemo {
    /**user.name是java.properties的默认存在，当前系统用户：allcure，除非配置文件中配置user.name*/
    @Bean
    public User configUser(@Value("${user.id}") Long id, @Value("${user.name}")String name){
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //扩展Environment中的PropertySources
        //必须在refresh方法之前操作
        Map<String,Object> propertiesSource = new HashMap<>();
        propertiesSource.put("user.name","小马哥");
        org.springframework.core.env.PropertySource propertySource = new MapPropertySource("first-property-source",propertiesSource);
        applicationContext.getEnvironment().getPropertySources().addFirst(propertySource);

        applicationContext.register(PropertySourceDemo.class);
        applicationContext.refresh();

        Map<String,User> users = applicationContext.getBeansOfType(User.class);
        for(Map.Entry<String,User> entry: users.entrySet()){
            System.out.println(entry.getValue());
        }

        System.out.println(applicationContext.getEnvironment().getPropertySources());
        applicationContext.close();
    }
}
