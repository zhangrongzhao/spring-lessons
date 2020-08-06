package org.geekbang.thinking.in.spring.bean.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;
import reactor.util.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * 线程级别的Scope
 *
 * */
public class ThreadLocalScope implements Scope {

    public static final String SCOPE_NAME = "thread-local-scope";

    private NamedThreadLocal<Map<String,Object>> threadLocal = new NamedThreadLocal<Map<String, Object>>("thread-local"){
        protected Map<String,Object> initialValue() {
            return new HashMap<String,Object>();
        }
    };

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String,Object> context = getContext();
        Object result = context.get(name);
        if(result==null){
            result=objectFactory.getObject();
            context.put(name,result);
        }
        return result;
    }

    @NonNull
    private Map<String,Object> getContext(){
        return threadLocal.get();
    }

    @Override
    public Object remove(String name) {
        Map<String,Object> context=getContext();
        return  context.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        //TODO:
    }

    @Override
    public Object resolveContextualObject(String key) {
        Map<String,Object> context=getContext();
        return context.get(key);
    }

    @Override
    public String getConversationId() {
        Thread currentThread = Thread.currentThread();
        return String.valueOf(currentThread.getId());
    }
}
