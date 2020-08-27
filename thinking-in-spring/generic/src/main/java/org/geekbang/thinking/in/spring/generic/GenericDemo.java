package org.geekbang.thinking.in.spring.generic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Generic Demo
 * @author
 * @see Collection
 * */
public class GenericDemo {
    public static void main(String[] args){
        Collection<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("world");

        //编译错误
        //strings.add(1);

        //泛型类型擦写
        Collection stringCollection = list;
        //编译正确
        stringCollection.add(1);

        System.out.println(list);
    }
}
