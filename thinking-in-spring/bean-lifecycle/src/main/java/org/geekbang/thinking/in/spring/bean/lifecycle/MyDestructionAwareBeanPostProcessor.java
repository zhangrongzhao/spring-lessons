package org.geekbang.thinking.in.spring.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * 销毁 Bean的PostProcessor
 * {@link DestructionAwareBeanPostProcessor } 实现
 * **/
public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {
    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if(ObjectUtils.nullSafeEquals("userHolder",beanName)&&UserHolder.class.equals(bean.getClass())){
            UserHolder userHolder=(UserHolder)bean;
            //afterSingletonInstantiated()=the user holder:v8
            //user holder description ="the user holder v"
            userHolder.setDescription("the user holder:v9");
            System.out.println("postProcessBeforeDestruction()="+userHolder.getDescription());
        }
    }
}
