package org.geekbang.thinking.in.spring.dependency.injection;

import org.geekbang.thinking.in.spring.dependency.injection.annotation.UserGroup;
import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * {@Link Qualifier} 注解的依赖注入示例
 * @author
 * @see org.springframework.beans.factory.annotation.Qualifier
 * @since
*/
public class QualifierAnnotationDependencyInjectionDemo {

    /**
     * 整体上下文中有四个Bean：
     * superBean，
     * user，
     * user1-->@Qualifer
     * user2-->@Qualifer
     * **/

    @Autowired
    private Collection<User> allUsers;//期待2个Bean全部注入:user superUser

    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers;//user1,user2,user3,user4

    @Autowired
    @UserGroup
    private Collection<User> groupUsers;// user3,user4
    /***************************************************************************************/
    @Autowired
    private User user; //superUser->primary=true

    @Autowired
    @Qualifier("user")  //指定Bean的名称或者ID
    private User namedUser;

    @Bean
    @Qualifier  //进行逻辑分组
    public User user1(){
        return  createUser(7L);
    }

    @Bean
    @Qualifier //进行逻辑分组
    public User user2(){
        return  createUser(8L);
    }

    @Bean
    @UserGroup
    public User user3(){
        return  createUser(9L);
    }

    @Bean
    @UserGroup
    public User user4(){
        return  createUser(10L);
    }

    public static User createUser(Long id){
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath="classpath:/META-INF/dependency-lookup-context.xml";
        //加载xml资源，解析并生成BeanDefinition
        reader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

        //期待输出：superUser
        System.out.println("demo.user = "+demo.user);
        //期待输出：user
        System.out.println("demo.namedUser="+demo.namedUser);
        //期待输出：user,user1,user2
        System.out.println("demo.allUsers="+demo.allUsers);
        //期待输出：user1,user2
        System.out.println("demo.qualifierUsers="+demo.qualifierUsers);
        //期待输出：user3,user4
        System.out.println("demo.groupUsers="+demo.groupUsers);

        applicationContext.close();
    }
}
