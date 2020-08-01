package org.geekbang.thinking.in.spring.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.Resource;

public class AnnotationDependencyFieldInjectionDemo {

    @Autowired  /* Autowired 会忽略掉静态字段*/
    private UserHolder userHolder;

    @Resource
    private UserHolder userHolder2;


    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyFieldInjectionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath="classpath:/META-INF/dependency-lookup-context.xml";
        //加载xml资源，解析并生成BeanDefinition
        reader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        //依赖查找AnnotationDependencyFieldInjectionDemo bean
        AnnotationDependencyFieldInjectionDemo demo=applicationContext.getBean(AnnotationDependencyFieldInjectionDemo.class);

        //@Autowired 方式字段关联
        UserHolder userHolder = demo.userHolder;
        System.out.println(userHolder);



        //@Resource 方式字段关联
        UserHolder userHolder2 = demo.userHolder2;
        System.out.println(userHolder2);

        System.out.println(userHolder==userHolder2);


        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user){
        UserHolder userHolder = new UserHolder(user);
        return userHolder;
    }
}
