package org.geekbang.thinking.in.spring.validation;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.*;

import java.util.Locale;

/**
 * {@link Validator} 示例
 * **/
public class ValidatorDemo {

    static class UserValidator implements Validator{

        @Override
        public boolean supports(Class<?> aClass) {
            return User.class.isAssignableFrom(aClass);
        }

        @Override
        public void validate(Object o, Errors errors) {
            User user = (User)o;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors,"id","id.required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","name.required");
        }
    }

    public static void main(String[] args){
        //1.
         Validator validator = new UserValidator();

         //2.
         User user=new User();
         System.out.println("User 对象是否被UserValidator 支持校验："+validator.supports(user.getClass()));

         //3.
         Errors errors = new BeanPropertyBindingResult(user,"user");
         validator.validate(user,errors);

        //4.通过ObjectError和FieldError中的code和arg来关联MessageSource实现
        MessageSource messageSource = createMessageSource();

        //5.输出所有错误文案
        for(ObjectError error:errors.getAllErrors()){
            String message = messageSource.getMessage(error.getCode(),error.getArguments(),Locale.getDefault());
            System.out.println(message);
        }
    }

    private static MessageSource createMessageSource() {
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("user.properties.not.null", Locale.getDefault(),"user所有属性不能为空");
        messageSource.addMessage("name.required", Locale.getDefault(),"the name of the user must not be null");
        messageSource.addMessage("id.required", Locale.getDefault(),"the id of the user must not be null");
        return messageSource;
    }
}
