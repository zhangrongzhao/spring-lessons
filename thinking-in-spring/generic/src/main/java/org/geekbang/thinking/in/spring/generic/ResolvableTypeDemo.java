package org.geekbang.thinking.in.spring.generic;

import org.springframework.core.ResolvableType;

import java.util.ArrayList;

/**
 * {@link ResolvableType}
 * **/
public class ResolvableTypeDemo {
    public static void main(String[] args){
        //工厂创建
        //StringList <- ArrayList <- AbstractList <- List <- collection <-Iterable
        ResolvableType resolvableType = ResolvableType.forClass(StringList.class);

        resolvableType.getSuperType(); //ArrayList
        resolvableType.getSuperType().getSuperType();//AbstractList
        resolvableType.getSuperType().getSuperType().getSuperType();//List

        System.out.println(resolvableType.asCollection().resolve());//raw type :collection
        System.out.println(resolvableType.asCollection().resolveGeneric(0));//获取泛型类型参数

        //System.out.println(resolvableType);
    }


    static class StringList extends ArrayList<String> {

    }
}
