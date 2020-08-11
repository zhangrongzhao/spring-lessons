package org.geekbang.thinking.in.spring.bean.lifecycle;


import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;

/**
 * User holder 类
 * */
public class UserHolder {
    private User user;
    private Integer number;
    private String description;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public UserHolder(User user){
        this.user = user;
    }

    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", description='" + description + '\'' +
                '}';
    }
}
