package org.geekbang.thinking.in.spring.bean.definition.factory;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.FactoryBean;

/*
 * @link User
 *
 */
public class UserFactoryBean implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        return User.createInstance();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
