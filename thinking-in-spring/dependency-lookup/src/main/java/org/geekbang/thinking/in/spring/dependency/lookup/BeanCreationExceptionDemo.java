package org.geekbang.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

public class BeanCreationExceptionDemo {
    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        BeanDefinitionBuilder builder=BeanDefinitionBuilder.genericBeanDefinition(Pojo.class);
        applicationContext.registerBeanDefinition("errorBean",builder.getBeanDefinition());

        applicationContext.refresh();
        applicationContext.close();
    }

    public static class Pojo implements InitializingBean{
        @PostConstruct /*CommonAnnotationBeanPostProcessor*/
        public void postConstruct() throws Exception {
            throw new Exception("postConstruct: ...For purpose... ");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
              throw new Exception("afterPropertiesSet: ...For purpose... ");
        }
    }
}
