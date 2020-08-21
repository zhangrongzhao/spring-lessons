package org.geekbang.thinking.in.spring.validation;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Locale;

/**
 * 错误文案示例
 *
 * @see Errors
 * */
public class ErrorMessageDemo {
    public static void main(String[] args) {
        //0.创建User对象
        User user = new User();
        user.setName("小马哥");
        //1.选择Errors--BeanPropertyBindingResult
        Errors errors = new BeanPropertyBindingResult(user,"user");
        //2.调用 reject 或者 rejectValue 方法
        //reject 生成 ObjectError
        //rejectValue 生成 FieldError
        errors.reject("user.properties.not.null");
        //user.name => user.getName()
        errors.rejectValue("name","name.required");

        //3.获取Errors中 ObjectError 和 FieldError
        List<ObjectError> globalErrors = errors.getGlobalErrors();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        List<ObjectError> allErrors = errors.getAllErrors();

        //4.通过ObjectError和FieldError中的code和arg来关联MessageSource实现
        MessageSource messageSource = createMessageSource();

        for(ObjectError error:allErrors){
            String message = messageSource.getMessage(error.getCode(),error.getArguments(),Locale.getDefault());
            System.out.println(message);
        }
    }

    private static MessageSource createMessageSource() {
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("user.properties.not.null", Locale.getDefault(),"user所有属性不能为空");
        messageSource.addMessage("name.required", Locale.getDefault(),"the name of the user must not be null");
        return messageSource;
    }
}
