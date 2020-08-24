package org.geekbang.thinking.in.spring.ioc.overview.dependency.domain;

/**
 *  Company 公司类
 * */
public class Company {
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                '}';
    }
}
