package org.springframework.bean.factory.support;

import org.springframework.bean.factory.BeanDefinition;
import org.springframework.bean.factory.FactoryBean;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final Map<String, Object> factoryBeanObjectCache = new HashMap<>();

    @Override
    public Object getBean(String beanName) {
        Object sharedInstance = getSingleton(beanName);
        if (sharedInstance != null) {
            return getObjectForBeanInstance(sharedInstance, beanName);
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        Object bean = createBean(beanName, beanDefinition);
        getObjectForBeanInstance(bean, beanName);
        return bean;
    }

    private Object getObjectForBeanInstance(Object bean, String beanName) {
        if (bean instanceof FactoryBean<?> factoryBean) {
            if (factoryBean.isSingleton()) {
                Object cache = factoryBeanObjectCache.get(beanName);
                if (cache != null) {
                    return cache;
                }
                Object cacheValue = factoryBean.getObject();
                factoryBeanObjectCache.put(beanName, cacheValue);
                return cacheValue;
            } else {
                return factoryBean.getObject();
            }
        }
        return bean;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition);

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBean(String beanName, Class<T> type) {
        return (T) getBean(beanName);
    }

}
