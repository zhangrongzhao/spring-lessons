package org.geekbang.thinking.in.spring.bean.definition.factory;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;

/**
 * user 工厂类
 * **/
public interface UserFactory {
    default User createUser(){
        return User.createInstance();
    }

}
