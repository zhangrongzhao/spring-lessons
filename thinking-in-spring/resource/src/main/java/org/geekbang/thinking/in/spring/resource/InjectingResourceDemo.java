package org.geekbang.thinking.in.spring.resource;

import org.geekbang.thinking.in.spring.resource.util.ResourceUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * 注入{@link Resource}资源对象示例
 * @see Resource
 * @see Value
 * @see AnnotationConfigApplicationContext
 * */
public class InjectingResourceDemo {

    @Value("classpath:/META-INF/default.properties")
    private Resource defaultPropertiesResource;

    @Value("classpath:/META-INF/*.properties")
    private Resource[] propertiesResources;

    @Value("${user.dir}")
    private String currentProjectBasePath;

    @PostConstruct
    public void init(){
       System.out.println(ResourceUtils.getContent(defaultPropertiesResource));
       System.out.println("=================================================");
       Stream.of(propertiesResources).map(ResourceUtils::getContent).forEach(System.out::println);
       System.out.println("=================================================");
       System.out.println(currentProjectBasePath);
    }

    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectingResourceDemo.class);
        applicationContext.refresh();
        applicationContext.close();
    }
}
