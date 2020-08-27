package org.geekbang.thinking.in.spring.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Generic api demo
 * **/
public class GenericAPIDemo {
    public static void main(String[] args){
        //原生类型 primitive type: int long double
        Class intClass = int.class;

        //数组类型 array type: int[] ,object[]
        Class objectArrayClass = Object[].class;

        //原始类型 raw types: java.lang.String
        Class rawClass = String.class;

        //参数化类型 Parameterized type
        ParameterizedType  parameterizedType = (ParameterizedType) ArrayList.class.getGenericSuperclass();
        System.out.println(parameterizedType.toString());

        //parameterizedType.getRawType()==>java.util.AbstractList
        Type rawType = parameterizedType.getRawType();
        System.out.println(rawType);

        //类型变量 type variable
        Type[] typeVariables = parameterizedType.getActualTypeArguments();
        Stream.of(typeVariables)
                .map(TypeVariable.class::cast)//Type==>TypeVariable
                .forEach(System.out::println);
    }
}
