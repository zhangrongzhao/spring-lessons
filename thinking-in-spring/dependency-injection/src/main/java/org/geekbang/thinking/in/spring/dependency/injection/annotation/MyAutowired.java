package org.geekbang.thinking.in.spring.dependency.injection.annotation;


import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

/**
 * 自定义注解 （元注解： @Autowired）
 *
 * */
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Autowired
public @interface MyAutowired {

    /**
     * Declares whether the annotated dependency is required.
     * <p>Defaults to {@code true}.
     */
    boolean required() default true;
}
