package org.geekbang.thinking.in.spring.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Set;

/**
 * {@Link ObjectProvider} 的延迟依赖注入示例
 * @author
 * @see org.springframework.context.annotation.Lazy
 * @since
*/
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;//实时注入

    @Autowired
    private ObjectProvider<User> userObjectProvider;//延迟依赖注入

    @Autowired
    private ObjectFactory<Set<User>> userObjectFactory;//延迟依赖注入

    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath="classpath:/META-INF/dependency-lookup-context.xml";
        //加载xml资源，解析并生成BeanDefinition
        reader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);

//        //期待输出：superUser
//        System.out.println("demo.user = "+demo.user);
//
//        //期待输出：
//        System.out.println("demo.userObjectProvider = "+demo.userObjectProvider);
//        demo.userObjectProvider.forEach(System.out::println);
//
//        //期待输出：
//        System.out.println("demo.userFromObjectProvider = "+demo.userObjectProvider.getObject());

        //期待输出：
        System.out.println("demo.userObjectFactory = "+demo.userObjectFactory);
        //期待输出：
        System.out.println("demo.userFromObjectFactory = "+demo.userObjectFactory.getObject());


        applicationContext.close();
    }
}
