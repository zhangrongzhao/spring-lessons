package org.geekbang.thinking.in.spring.configuration.metadata;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * 基于注解的Spring IoC容器元数据配置 示例
 * {@Link AnnotatedBeanDefinitionReader}
 * **/
@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
@Import(User.class)
@PropertySource("classpath:/META-INF/user-bean-definition.properties")
@PropertySource("classpath:/META-INF/user-bean-definition.properties")
public class AnnotatedSpringIoCContainerMetadataConfigurationDemo {

    /**user.name是java.properties的默认存在，当前系统用户：allcure，除非配置文件中配置user.name*/
    @Bean
    public User configUser(@Value("${user.id}") Long id,@Value("${user.name}")String name){
       User user = new User();
       user.setId(id);
       user.setName(name);
       return user;
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotatedSpringIoCContainerMetadataConfigurationDemo.class);
        applicationContext.refresh();

        Map<String,User> users = applicationContext.getBeansOfType(User.class);
        for(Map.Entry<String,User> entry: users.entrySet()){
             System.out.println(entry.getValue());
        }
        applicationContext.close();
    }
}
