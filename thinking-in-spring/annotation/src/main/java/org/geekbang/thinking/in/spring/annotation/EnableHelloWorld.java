package org.geekbang.thinking.in.spring.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 激活“HelloWorld”模块
 *
 * ***/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//第二步：通过Import导入具体实现
//@Import(HelloWorldConfiguration.class)//方法一：通过Configuration Class实现
//@Import(HelloWorldImportSelector.class)//方法二： 通过ImportSelector接口实现
@Import(HelloWorldImportBeanDefinitionRegistrar.class)//方法三：通过ImportBeanDefinitionRegistrar
public @interface EnableHelloWorld {//第一步：@EnableXXXX命名
}
