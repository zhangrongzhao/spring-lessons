package org.geekbang.thinking.in.spring.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.*;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link Environment} 属性源变更示例
 * @see Environment
 *
 * **/
public class EnvironmentPropertySourceChangeDemo {

    @Value("${user.name}")
    private String userName;

    //PropertySource("first-property-source"){user.name=小马哥}
    //PropertySource(Java System Properties){user.name=administrator}

    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();
        applicationContext.register(EnvironmentPropertySourceChangeDemo.class);

        //在Spring ApplicationContext启动前，调整Environment中的PropertySource
        ConfigurableEnvironment environment=applicationContext.getEnvironment();
        //获取MutablePropertySources对象
        MutablePropertySources propertySources=environment.getPropertySources();
        //动态插入 PropertySource 到PropertySources
        Map<String,Object> source=new HashMap<>();
        source.put("user.name","小马哥");
        MapPropertySource mapPropertySource=new MapPropertySource("first-property-source",source);
        propertySources.addFirst(mapPropertySource);

        applicationContext.refresh();

        for(PropertySource propertySource:propertySources){
            System.out.printf("PropertySource(name=%s) 'user.name' 属性： %s \n",propertySource.getName(),propertySource.getProperty("user.name"));
        }

        EnvironmentPropertySourceChangeDemo demo=applicationContext.getBean(EnvironmentPropertySourceChangeDemo.class);
        System.out.println(demo.userName);

        applicationContext.close();
    }
}
