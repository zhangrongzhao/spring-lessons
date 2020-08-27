package org.geekbang.thinking.in.spring.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.core.GenericTypeResolver.*;

/**
 * {@link  GenericTypeResolver}示例
 * @see GenericTypeResolver
 * **/
public class GenericTypeResolverDemo {
    public static void main(String[] args) throws NoSuchMethodException {
//        //String Comparable<String>具体化
//        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class,Comparable.class,"getString");
//
//        //ArrayList<Object> 是泛型类型参数的具体化
//        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class,List.class,"getList");
//
//        //StringList 是List的泛型参数的具体化
//        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class,List.class,"getStringList");

        //具备ParameterizedType返回，否则null

        //Type Variable

        Map<TypeVariable, Type> typeVariableMap = GenericTypeResolver.getTypeVariableMap(StringList.class);
        System.out.println(typeVariableMap);
    }

    public static  ArrayList<Object> getList(){
        return null;
    }

    public static String getString(){
        return null;
    }

    public static StringList getStringList(){
        return null;
    }

    private static void displayReturnTypeGenericInfo(Class<?> containingClass,Class<?> genericIfs,String methodName, Class... arguments)throws NoSuchMethodException{
        Method method = containingClass.getMethod(methodName);
        Class returnType =  resolveReturnType(method,containingClass);

        //常规类作为方法返回值
        System.out.println();
        System.out.printf(" GenericTypeResolver.resolveReturnType(%s,%s) = %s ",methodName,containingClass.getSimpleName(), returnType);
        System.out.println();
        //常规类型不具备泛型参数类型
        Class returnTypeArgument = resolveReturnTypeArgument(method,genericIfs);
        System.out.printf(" GenericTypeResolver.resolveReturnTypeArgument(%s,%s) = %s ",methodName,containingClass.getSimpleName(), returnTypeArgument);

    }

    static class StringList extends ArrayList<String> {//泛型类型参数具体化

    }
}
