package org.geekbang.thinking.in.spring.i18n;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Spring boot 场景使用自定义{@link MessageSource} bean
 * @see MessageSource
 * @see MessageSourceAutoConfiguration
 * @see ReloadableResourceBundleMessageSource
 * */
@EnableAutoConfiguration
public class CustomizedMessageSourceBeanDemo { //@Configuration class

    /**在spring boot场景中，Primary Configuration Sources(classes) 高于@AutoConfiguration */
    @Bean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)
    public MessageSource messageSource(){
        return new ReloadableResourceBundleMessageSource();
    }

    public static void main(String[] args){
        ConfigurableApplicationContext applicationContext =  new SpringApplicationBuilder(CustomizedMessageSourceBeanDemo.class)
                //Primary Configuration class
                .web(WebApplicationType.NONE)
                .run(args);

        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        if(beanFactory.containsBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)){
            //查找MessageSource 的BeanDefinition
            System.out.println(beanFactory.getBeanDefinition(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME));

            //查找MessageSource的bean
            MessageSource messageSource = applicationContext.getBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME,MessageSource.class);
            System.out.println(messageSource);
        }
        //关闭应用程序上下文
        applicationContext.close();
    }
}
