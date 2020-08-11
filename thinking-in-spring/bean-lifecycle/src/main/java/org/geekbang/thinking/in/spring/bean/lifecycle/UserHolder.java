package org.geekbang.thinking.in.spring.bean.lifecycle;


import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;

/**
 * User holder 类
 * */
public class UserHolder {
    private User user;
    public UserHolder(User user){
        this.user = user;
    }
//
//    public UserHolder() {
//
//    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
