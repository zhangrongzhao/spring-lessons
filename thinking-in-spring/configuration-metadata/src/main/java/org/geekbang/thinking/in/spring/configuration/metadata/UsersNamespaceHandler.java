package org.geekbang.thinking.in.spring.configuration.metadata;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * user.xsd
 * {@link org.springframework.beans.factory.xml.NamespaceHandler}
 * ***/
public class UsersNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }
}
