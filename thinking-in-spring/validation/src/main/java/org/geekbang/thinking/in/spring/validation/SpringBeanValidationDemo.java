package org.geekbang.thinking.in.spring.validation;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 *Spring bean validation 整合示例
 * @see Validator
 * @see LocalValidatorFactoryBean
 * **/
public class SpringBeanValidationDemo {
    public static void main(String[] args){
        //配置XML配置文件
        //配置Spring 应用上下文
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-validation-context.xml");

//        Validator validator = applicationContext.getBean(Validator.class);
//        System.out.println(validator instanceof LocalValidatorFactoryBean);

        UserProcessor processor=applicationContext.getBean(UserProcessor.class);
        processor.process(new User());

        applicationContext.close();
    }

    @Component
    @Validated
    static class UserProcessor{

           public void process( User user){
                 System.out.println(user);
           }
    }



    static class User{

        @NonNull
        private String name;
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
