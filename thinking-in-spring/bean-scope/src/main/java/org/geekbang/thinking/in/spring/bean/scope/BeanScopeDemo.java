package org.geekbang.thinking.in.spring.bean.scope;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;

/**
 *  Bean 作用域
 * */
public class BeanScopeDemo implements DisposableBean {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)//默认SCOPE="singleton"
    public static User singletonUser(){
        return createUser();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static User prototypeUser(){
        return createUser();
    }

    private static User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    //结论一：
    //singleton Bean:依赖查找，依赖注入，均为一个共享实例对象
    //prototype Bean:依赖查找，依赖注入，均为新生对象。

    //结论二：
    //如果依赖注入集合类型对象，singleton Bean 和prototype Bean 会存在一个。
    //prototype Bean 有别与其他地方的prototype bean.


    //结论三：
    //无论是singleton bean 还是 prototype bean都会执行初始化方法
    //singleton bean 会执行销毁方法回调
    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;
    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser2;
    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser3;


    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser2;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser3;

    @Autowired
    private Map<String,User> users;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.printf("%s bean 名称：%s 在初始化后回调\n\r",bean.getClass().getName(),beanName);
                    return bean;
                }
            });
        });

        applicationContext.refresh();

        //scopedBeansByLookup(applicationContext);
        scopedBeansByInjection(applicationContext);

        applicationContext.close();
    }

    private static void scopedBeansByInjection(AnnotationConfigApplicationContext applicationContext) {
        BeanScopeDemo demo = applicationContext.getBean(BeanScopeDemo.class);
        System.out.println("demo.singletonUser1="+demo.singletonUser1);
        System.out.println("demo.singletonUser2="+demo.singletonUser2);
        System.out.println("demo.singletonUser3="+demo.singletonUser3);
        System.out.println("demo.prototypeUser1="+demo.prototypeUser1);
        System.out.println("demo.prototypeUser2="+demo.prototypeUser2);
        System.out.println("demo.prototypeUser3="+demo.prototypeUser3);
        System.out.println("demo.users="+demo.users);
    }

    private static void scopedBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
         for(int i=0;i<5;i++){
             //singletonUser是共享实例
             User singletonUser = applicationContext.getBean("singletonUser",User.class);
             System.out.println("singletonUser="+singletonUser);
         }

         for(int i=0;i<5;i++){
             //prototypeUser每次都是新生实例
             User prototypeUser = applicationContext.getBean("prototypeUser",User.class);
             System.out.println("prototypeUser=" + prototypeUser);
         }
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("当前BeanScopeDemo Bean 正在销毁中...");

        this.prototypeUser1.destroy();
        this.prototypeUser2.destroy();
        this.prototypeUser3.destroy();

        for(Map.Entry<String,User> entry:this.users.entrySet()){
            String beanName = entry.getKey();
            BeanDefinition beanDefinition=beanFactory.getBeanDefinition(beanName);
            if(beanDefinition!=null&&beanDefinition.isPrototype()){
                User user=entry.getValue();
                user.destroy();
            }
        }

        System.out.println("当前BeanScopeDemo Bean 正在销毁完成");
    }
}
