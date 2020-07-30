package org.geekbang.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
* 层次性依赖查找
* */
public class HierarchicalLookupDemo {
    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(HierarchicalLookupDemo.class);

        //1.获取HierarchicalBeanFactory<-ConfigurableBeanFactory<-ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        System.out.println("[parent beanFactory]:"+beanFactory.getParentBeanFactory());
        displayContainsBean(beanFactory,"user");

        //2.设置parent BeanFactory
        HierarchicalBeanFactory hierarchicalBeanFactory = createBeanFactory();
        beanFactory.setParentBeanFactory(hierarchicalBeanFactory);
        System.out.println("[parent beanFactory]:{"+beanFactory.getParentBeanFactory()+"}");
        displayContainsBean(hierarchicalBeanFactory,"user");

        applicationContext.refresh();
        //lookupByObjectProvider(applicationContext);
        applicationContext.close();
    }

    public static void displayContainsBean(HierarchicalBeanFactory beanFactory,String beanName){
        System.out.println("分层beanFactory:" + beanFactory);
        System.out.println("分层beanFactory是否包含Bean:"+beanName + ":"+containsBean(beanFactory,beanName));
    }

    public static boolean containsBean(HierarchicalBeanFactory beanFactory,String beanName){
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if(parentBeanFactory instanceof HierarchicalBeanFactory){
            HierarchicalBeanFactory hierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(beanFactory);
            return containsBean(hierarchicalBeanFactory,beanName);
        }
        return beanFactory.containsBean(beanName);
    }

    public static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory,String beanName){
        System.out.println("当前的beanFactory是否包含LocalBean:"+beanName + ":"+beanFactory.containsLocalBean(beanName));
        System.out.println("LocalBean:"+beanFactory.getBean(beanName));
    }
    public static ConfigurableListableBeanFactory createBeanFactory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String path="META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(path);
        return beanFactory;
    }
}
