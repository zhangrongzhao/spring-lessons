package org.geekbang.thinking.in.spring.ioc.overview.dependency.repository;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * 用户信息仓库
 * ***/
public class UserRepository {

    private Collection<User> users;
    public Collection<User> getUsers() {
        return users;
    }
    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    private BeanFactory beanFactory;
    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    private ObjectFactory<ApplicationContext> objectFactory;
    public ObjectFactory<ApplicationContext> getObjectFactory() {
        return objectFactory;
    }
    public void setObjectFactory(ObjectFactory<ApplicationContext> objectFactory) {
        this.objectFactory = objectFactory;
    }

}
