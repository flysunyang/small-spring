package org.springframework.bean.factory.support;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    
    private final Map<String, Object> singletonObjects = new HashMap<>();
    
    @Override
    public void addSingleton(String beanName, Object singleton) {
        singletonObjects.put(beanName, singleton);
    }

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

}
