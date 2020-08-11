package org.geekbang.thinking.in.spring.bean.lifecycle;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.SuperUser;
import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;

 class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if(ObjectUtils.nullSafeEquals("superUser",beanName)&&SuperUser.class.equals(beanClass)){
            return new SuperUser();
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if(ObjectUtils.nullSafeEquals("user",beanName) && User.class.equals(bean.getClass())){
            //"user"对象不允许属性赋值（配置元数据->对象属性）
            User user = (User)bean;
            user.setId(2L);
            user.setName("mercy");
            return false;
        }
        return true;
    }

    //user 是 跳过 Bean属性设置（填入）
    //SuperUser 完整跳过 Bean属性设置（填入）
    //useHolder
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName)
            throws BeansException {
        //对userHolder进行拦截
        if(ObjectUtils.nullSafeEquals("userHolder",beanName)&&UserHolder.class.equals(bean.getClass())){
            //假设<property name="number" value="1"></property>配置，PropertyValues就包含元素PropertyValue(number=1)
            final  MutablePropertyValues propertyValues;
            if(pvs instanceof MutablePropertyValues){
                propertyValues=(MutablePropertyValues)pvs;
            }else{
                propertyValues = new MutablePropertyValues();
            }
            propertyValues.addPropertyValue("number","1");

            //如果存在description属性，则进行替换
            if(propertyValues.contains("description")){
                //PropertyValue是不可变的
                //PropertyValue propertyValue = (PropertyValue) propertyValues.getPropertyValue("description");
                propertyValues.removePropertyValue("description");
                propertyValues.addPropertyValue("description","The user holder:v2");
            }
            //原始属性：<property name="description" value="the user holder"></property>

            return propertyValues;
        }
        return null;
    }
}

/**
 * BeanDefinition合并示例
 * */
public class BeanConstructorDependencyInjectionDemo {
    public static void main(String[] args) {
        //beanConstructorDependencyInjection();
        //executeBeanFactory();
        //System.out.println("================================================================");
        executeApplicationContext();
    }


    public static void beanConstructorDependencyInjection(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        //方法一：添加MyInstantiationAwareBeanPostProcessor实现InstantiationAwareBeanPostProcessor
        //beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        //方法二：将MyInstantiateAwareBeanPostProcessor作为Bean注册

        String[] locations = {"/META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        applicationContext.setConfigLocations(locations);
        applicationContext.refresh();

        int beanDefinitionBefore = beanFactory.getBeanDefinitionCount();
        int beanDefinitionAfter = beanFactory.getBeanDefinitionCount();
        int beanDefinitionCount = beanDefinitionAfter - beanDefinitionBefore;
        System.out.println("加载的BeanDefinition数量：" + beanDefinitionCount);

//        User user = beanFactory.getBean("user", User.class);
//        System.out.println(user);
//
//        SuperUser superUser = (SuperUser) beanFactory.getBean("superUser", User.class);
//        System.out.println(superUser);

//        //构造器注入按照类型注入，resolveDependency
        UserHolder userholder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userholder);

        applicationContext.close();
    }


    public static void executeBeanFactory(){
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        //方法一：添加MyInstantiationAwareBeanPostProcessor实现InstantiationAwareBeanPostProcessor
        //beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        //方法二：将MyInstantiateAwareBeanPostProcessor作为Bean注册

        String[] locations = {"/META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        XmlBeanDefinitionReader beanDefinitionReader=new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions(locations);

        int beanDefinitionBefore = beanFactory.getBeanDefinitionCount();
        int beanDefinitionAfter = beanFactory.getBeanDefinitionCount();
        int beanDefinitionCount = beanDefinitionAfter - beanDefinitionBefore;
        System.out.println("加载的BeanDefinition数量：" + beanDefinitionCount);

//        User user = beanFactory.getBean("user", User.class);
//        System.out.println(user);
//
//        SuperUser superUser = (SuperUser) beanFactory.getBean("superUser", User.class);
//        System.out.println(superUser);

        //构造器注入按照类型注入，resolveDependency
        UserHolder userholder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userholder);
    }

    public  static void executeApplicationContext(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        String[] locations = {"/META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        applicationContext.setConfigLocations(locations);
        applicationContext.refresh();

        int beanDefinitionBefore = applicationContext.getBeanDefinitionCount();
        int beanDefinitionAfter = applicationContext.getBeanDefinitionCount();
        int beanDefinitionCount = beanDefinitionAfter - beanDefinitionBefore;
        System.out.println("加载的BeanDefinition数量：" + beanDefinitionCount);

//        User user = applicationContext.getBean("user", User.class);
//        System.out.println(user);
//
//        SuperUser superUser = (SuperUser) applicationContext.getBean("superUser", User.class);
//        System.out.println(superUser);

        //构造器注入按照类型注入，resolveDependency
        UserHolder userholder = applicationContext.getBean("userHolder", UserHolder.class);
        System.out.println(userholder);

        applicationContext.close();

    }
}