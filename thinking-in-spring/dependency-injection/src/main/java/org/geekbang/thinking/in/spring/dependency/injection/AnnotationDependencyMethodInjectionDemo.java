package org.geekbang.thinking.in.spring.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

//import javax.annotation.Resource;
/*
* 基于java注解的方法依赖注入
* */
public class AnnotationDependencyMethodInjectionDemo {
    private UserHolder userHolder1;
    private UserHolder userHolder2;

    @Autowired
    public void init1(UserHolder userHolder1){
        this.userHolder1=userHolder1;
    }

    //@Resource
    public void init2(UserHolder userHolder2){
        this.userHolder2=userHolder2;
    }

    @Bean
    public UserHolder userHolder(User user){
        UserHolder userHolder = new UserHolder(user);
        return userHolder;
    }

    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyMethodInjectionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath="classpath:/META-INF/dependency-lookup-context.xml";
        //加载xml资源，解析并生成BeanDefinition
        reader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        //依赖查找AnnotationDependencyFieldInjectionDemo bean
        AnnotationDependencyMethodInjectionDemo demo=applicationContext.getBean(AnnotationDependencyMethodInjectionDemo.class);

        //@Autowired 方式字段关联
        UserHolder userHolder = demo.userHolder1;
        System.out.println(userHolder);



        //@Resource 方式字段关联
        UserHolder userHolder2 = demo.userHolder2;
        System.out.println(userHolder2);

        System.out.println(userHolder==userHolder2);


        applicationContext.close();
    }


}
