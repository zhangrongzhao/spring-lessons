package org.geekbang.thinking.in.spring.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

//import javax.annotation.PostConstruct;

/**
 * 依赖来源示例
 * */
public class DependencySourceDemo {

    // 注入在InitializingBean.afterPropertiesSet()中执行。早于setter注入，也早于@PostConstruct
    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApplicationContext applicationContext;

    //@PostConstruct
    public void initByInjection(){
        System.out.println("beanFactory=" + beanFactory);
        System.out.println("resourceLoader=" + resourceLoader);
        System.out.println("applicationEventPublisher=" + applicationEventPublisher);
        System.out.println("applicationContext=" + applicationContext);
    }

    //@PostConstruct
    public void initByLookup(){
        getBean(BeanFactory.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationEventPublisher.class);
        getBean(ApplicationContext.class);
    }

    public <T> T getBean(Class<T> beanType){
        try{
          return  beanFactory.getBean(beanType);
        }catch(NoSuchBeanDefinitionException ex){
            System.err.println(ex);
        }
        return null;
    }

    public static void main(String[] args){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(DependencySourceDemo.class);
        annotationConfigApplicationContext.refresh();

        /**prepareFactory()中将，BeanFactory.class,ResourceLoader.class,ApplicationEventPublisher.class,ApplicationContext.class
         * 四种类型注册到resolvableDepdendencies中，非spring IoC容器中。
         * 所以使用BeanFactory.getBean()接口获取四个接口类型之一时：NoSuchBeanDefinitionException异常。
         *
         * **/

        //BeanFactory beanFactory = annotationConfigApplicationContext.getBean(BeanFactory.class);
        //ResourceLoader resourceLoader = annotationConfigApplicationContext.getBean(ResourceLoader.class);
        //ApplicationEventPublisher eventPublisher = annotationConfigApplicationContext.getBean(ApplicationEventPublisher.class);
        //ApplicationContext applicationContext1 = annotationConfigApplicationContext.getBean(ApplicationContext.class);

        annotationConfigApplicationContext.close();
    }
}
