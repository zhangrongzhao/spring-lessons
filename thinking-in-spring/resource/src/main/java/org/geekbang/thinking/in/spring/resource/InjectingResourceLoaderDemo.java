package org.geekbang.thinking.in.spring.resource;

import org.geekbang.thinking.in.spring.resource.util.ResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 注入{@link ResourceLoader}资源对象示例
 * @see Resource
 * @see ResourceLoader
 * @see Value
 * @see AnnotationConfigApplicationContext
 * */
public class InjectingResourceLoaderDemo implements ResourceLoaderAware {
    private ResourceLoader resourceLoader;//方法一

    @Autowired
    private ResourceLoader autowiredResourceLoader;//方法二

    @Autowired
    private AbstractApplicationContext applicationContext;//方法三

    @PostConstruct
    public void init(){
       System.out.println("resourceLoader == autowiredResourceLoader:" + (resourceLoader == autowiredResourceLoader));
       System.out.println("resourceLoader == applicationContext:" + (resourceLoader == applicationContext));
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectingResourceLoaderDemo.class);
        applicationContext.refresh();
        applicationContext.close();
    }


}
