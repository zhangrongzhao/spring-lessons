package org.geekbang.thinking.in.spring.configuration.metadata;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * User bean definition parser 解析器
 * {@Link BeanDefinitionParser }实现
 * */
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        setPropertyValue("id",element,builder);
        setPropertyValue("name",element,builder);
        setPropertyValue("city",element,builder);
    }

    private void setPropertyValue(String attributeName,Element element,BeanDefinitionBuilder builder){
        String attributeValue = element.getAttribute(attributeName);
        if (StringUtils.hasText(attributeValue)) {
            //BeanDefinition中定义id属性
            builder.addPropertyValue(attributeName,attributeValue);
        }
    }
}
