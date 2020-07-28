package org.geekbang.thinking.in.spring.ioc.overview.dependency.domain;

public class User {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public static User createInstance(){
        User newUser=new User();
        newUser.setId(4L);
        newUser.setName("小马哥");
        return newUser;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
