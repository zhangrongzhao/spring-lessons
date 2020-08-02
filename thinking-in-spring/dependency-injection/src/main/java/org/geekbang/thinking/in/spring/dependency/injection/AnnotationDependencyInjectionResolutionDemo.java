package org.geekbang.thinking.in.spring.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Lazy;

import java.util.Map;
import java.util.Optional;

/**
 * 注解驱动的依赖注入处理过程
 * @author
 * @see org.springframework.beans.factory.annotation.Autowired;
 * @since
*/
public class AnnotationDependencyInjectionResolutionDemo {

    @Lazy
    @Autowired                  //DependencyDescriptor->
    private User user;          // 必须的（required=true）
                                // 实时注入(eager=true)
                                // 通过类型（User.class）
                                // 依赖查找（处理）
                                // 字段名称（"user"）
                                // 是否是主要的（primary=true）

//    @Autowired                  //集合类型的依赖注入
//    private Map<String,User> users;
//
//
//    @Autowired
//    private Optional<User> optionalUser;

    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath="classpath:/META-INF/dependency-lookup-context.xml";
        //加载xml资源，解析并生成BeanDefinition
        reader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);

//        //期待输出：superUser
//        System.out.println("demo.user = "+demo.user);
//
//        //期待输出：user,superUser
//        System.out.println("demo.users = "+demo.users);

//
//        //期待输出：superUser
//        System.out.println("demo.optionalUser = "+demo.optionalUser);

        applicationContext.close();
    }
}
