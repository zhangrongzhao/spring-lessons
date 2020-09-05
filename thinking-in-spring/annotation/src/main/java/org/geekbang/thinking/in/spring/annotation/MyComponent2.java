package org.geekbang.thinking.in.spring.annotation;


import java.lang.annotation.*;

/**
 *  自定义 Component注解
 *
 * **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponent
public @interface MyComponent2 {
}
