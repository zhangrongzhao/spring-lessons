package org.geekbang.thinking.in.spring.dependency.injection.annotation;


import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectUser {
}
