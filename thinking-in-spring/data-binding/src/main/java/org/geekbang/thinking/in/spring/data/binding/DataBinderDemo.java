package org.geekbang.thinking.in.spring.data.binding;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.Company;
import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

/**
 *  {@link DataBinder} 示例
 * @see DataBinder
 * @see PropertyValues
 * @see PropertyValue
 * **/
public class DataBinderDemo {
    public static void main(String[] args){
        //1.创建空白对象
        User user = new User();
        //2.创建dataBinder
        DataBinder dataBinder = new DataBinder(user,"user");

        //创建PropertyValues
        Map<String,Object> source=new HashMap<>();
        source.put("id",1);
        source.put("name","小马哥");
        //PropertyValues存在User中不存在的属性值
        //dataBinder 特性一： 忽略未知属性
        //source.put("company",new Company());
        source.put("age",19);

        //PropertyValues中存在一个嵌套属性，比如company.name
        //DataBinder 特性二：支持嵌套属性
        source.put("company.name","geekbang");
        PropertyValues propertyValues = new MutablePropertyValues(source);

        //1.调整IgnoreUnknownFields true==>false,{抛出异常，age字段不存在User类中}
        //dataBinder.setIgnoreUnknownFields(false);

        //2.调整 AutoGrowNestedPaths 为false
        dataBinder.setAutoGrowNestedPaths(false);

        //3.调整IgnoreInvalidFields false==>true{默认调整不变化,需要调整AutoGrowNestedPaths 为false}
        dataBinder.setIgnoreInvalidFields(true);

        dataBinder.setRequiredFields("id","name","city");
        dataBinder.bind(propertyValues);

        //输出User内容
        System.out.println(user);

        //绑定结果(结果包含文案 code，不报错)
        BindingResult result = dataBinder.getBindingResult();
        System.out.println(result);
    }
}
