package org.geekbang.thinking.in.spring.dependency.injection;

import org.geekbang.thinking.in.spring.dependency.injection.annotation.InjectUser;
import org.geekbang.thinking.in.spring.dependency.injection.annotation.MyAutowired;
import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.*;

import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

/**
 * 注解驱动的依赖注入处理过程
 * @author
 * @see org.springframework.beans.factory.annotation.Autowired;
 * @since
*/
public class AnnotationDependencyInjectionResolutionDemo {

//    //@Lazy
//    @Autowired                  //DependencyDescriptor->
//    private User user;          // 必须的（required=true）
//                                // 实时注入(eager=true)
//                                // 通过类型（User.class）
//                                // 依赖查找（处理）
//                                // 字段名称（"user"）
//                                // 是否是主要的（primary=true）

//    @Autowired                  //集合类型的依赖注入
//    private Map<String,User> users;
//
//
//    @Autowired
//    private Optional<User> optionalUser;

//      @MyAutowired
//      private Optional<User> optionalUser;

//    @Inject
//    private User userInjected;

    @InjectUser
    private User myInjectUser;

//    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor(){
//        AutowiredAnnotationBeanPostProcessor beanPostProcessor=new AutowiredAnnotationBeanPostProcessor();
//        //1.替换原有的注解处理：使用@InjectUser 注解
//        //2.@Autowired + @InjectUser
////        Set<Class<? extends Annotation>> autowiredAnnotationTypes = new HashSet<>();
////        autowiredAnnotationTypes.add(Autowired.class);
////        autowiredAnnotationTypes.add(Value.class);
////        //annotations.add(Inject.class);
////        autowiredAnnotationTypes.add(InjectUser.class);
//
//        Set<Class<? extends Annotation>> autowiredAnnotationTypes=new LinkedHashSet<Class<? extends Annotation>>(Arrays.asList(Autowired.class,Inject.class,InjectUser.class));
//        beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
//        //beanPostProcessor.setAutowiredAnnotationType(InjectUser.class);
//        return beanPostProcessor;
//    }


    @Order(Ordered.LOWEST_PRECEDENCE-3)
    @Bean
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor(){
        AutowiredAnnotationBeanPostProcessor beanPostProcessor=new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(InjectUser.class);
        return beanPostProcessor;
    }

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

//        期待输出：superUser
//        System.out.println("demo.userInjected = "+demo.userInjected);


        //期待输出：superUser
        System.out.println("demo.myInjectUser = "+demo.myInjectUser);

        applicationContext.close();
    }
}
