package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    
    private Map<String, Object> singletonObjects = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeanMap = new HashMap<>();
    
    @Override
    public void addSingleton(String beanName, Object singleton) {
        singletonObjects.put(beanName, singleton);
    }

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    public void registerDisposableBean(String beanName, DisposableBean disposableBean) {
        disposableBeanMap.put(beanName, disposableBean);
    }

    public void destroySingletons() {
        for (String beanName : disposableBeanMap.keySet()) {
            DisposableBean disposableBean = disposableBeanMap.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
    
}
