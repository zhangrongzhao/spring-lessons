package org.geekbang.thinking.in.spring.annotation;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/***
 * 自定义{@link Component}
 *
 * **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponentScan
public @interface MyComponentScan2 {

    //“多态”，子注解提供新的属性方法引用“父”（元）注解中的属性方法
    @AliasFor(annotation = MyComponentScan.class,attribute = "scanBasePackages")//隐性别名
    String[] basePackages() default {};

    //传递隐性别名
    //@MyComponentScan2.basePackages
    // -> @MyComponentScan.scanBasePackages
    // -> @AliasFor @ComponentScan.basePackages
    // -> @AliasFor @ComponentScan.value 显性别名


}
