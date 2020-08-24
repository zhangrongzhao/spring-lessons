package org.geekbang.thinking.in.spring.ioc.overview.dependency.domain;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.enums.City;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import java.util.Arrays;
import java.util.List;

public class User implements InitializingBean, DisposableBean, BeanNameAware {
    private Long id;
    private String name;
    private City city;
    private Resource configFileLocation;
    private City[] workCities;
    private List<City> lifeCities;
    private transient String beanName;
    private Company company;

    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }

    public List<City> getLifeCities() {
        return lifeCities;
    }
    public void setLifeCities(List<City> lifeCities) {
        this.lifeCities = lifeCities;
    }

    public City[] getWorkCities() {
        return workCities;
    }
    public void setWorkCities(City[] workCities) {
        this.workCities = workCities;
    }

    public Resource getConfigFileLocation() {
        return configFileLocation;
    }
    public void setConfigFileLocation(Resource configFileLocation) {
        this.configFileLocation = configFileLocation;
    }

    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }

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
        newUser.setCity(City.BEIJING);
        return newUser;
    }


//    @PostConstruct
//    public void init(){
//        System.out.println("用户对象初始化...");
//    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("user bean["+this.beanName+"]初始化...");
    }


    @Override
    public void destroy(){
        System.out.println("user bean["+this.beanName+"]销毁中...");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
    public String getBeanName() {
        return beanName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", configFileLocation=" + configFileLocation +
                ", workCities=" + Arrays.toString(workCities) +
                ", lifeCities=" + lifeCities +
                ", beanName='" + beanName + '\'' +
                ", company=" + company +
                '}';
    }
}
