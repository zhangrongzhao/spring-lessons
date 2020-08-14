package org.geekbang.thinking.in.spring.configuration.metadata;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ObjectUtils;

/**
 * Bean 配置元数据示例
 * */
public class BeanConfigurationMetadataDemo {
    public static void main(String[] args){
        //BeanDefinition 的定义（声明）
        //BeanDefinition beanDefinition = new GenericBeanDefinition();
        //声明BeanDefinition
        //附加属性
        //beanDefinition.setAttribute("name","小马哥");

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name","mercyblitz");
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        //添加属性，不影响Bean实例化（Instantiate），属性赋值(populate)，初始化(Initialize)
        beanDefinition.setAttribute("name","小马哥");
        //当前BeanDefinition来自何方
        beanDefinition.setSource(BeanConfigurationMetadataDemo.class);

        //注册beanDefinition
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("user",beanDefinition);
        beanFactory.addBeanPostProcessor(new BeanPostProcessor(){
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if(ObjectUtils.nullSafeEquals("user",beanName)&&User.class.equals(bean.getClass())){
                    //属性存储上下文
                    BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                    if(BeanConfigurationMetadataDemo.class.equals(beanDefinition.getSource())){//通过source来判断
                        String name=(String)beanDefinition.getAttribute("name");
                        User user=(User)bean;
                        user.setName(name);
                    }
                }
                return bean;
            }
        });

        User user= beanFactory.getBean("user",User.class);
        System.out.println(user);
    }
}
