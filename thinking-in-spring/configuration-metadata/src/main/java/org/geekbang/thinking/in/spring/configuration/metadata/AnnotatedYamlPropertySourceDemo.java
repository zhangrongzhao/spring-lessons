package org.geekbang.thinking.in.spring.configuration.metadata;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.geekbang.thinking.in.spring.ioc.overview.dependency.enums.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * 基于注解的YAML外部配置化示例
 * **/
@PropertySource(name = "yamlPropertySource",
                value = "classpath:/META-INF/user.yaml",
                factory = YamlPropertySourceFactory.class)
public class AnnotatedYamlPropertySourceDemo {

    /**user.name是java.properties的默认存在，当前系统用户：allcure，除非配置文件中配置user.name*/
    @Bean
    public User configUser(@Value("${user.id}") Long id, @Value("${user.name}")String name,@Value("${user.city}")City city){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setCity(city);
        return user;
    }

    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotatedYamlPropertySourceDemo.class);
        applicationContext.refresh();

        User user = applicationContext.getBean(User.class);
        System.out.println(user);
        applicationContext.close();
    }
}
