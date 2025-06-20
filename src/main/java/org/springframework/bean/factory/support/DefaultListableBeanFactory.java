package org.springframework.bean.factory.support;

import org.springframework.bean.BeansException;
import org.springframework.bean.factory.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements ConfigurableListableBeanFactory, BeanDefinitionRegistry {
    
    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    
    @Override
    protected BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("No beanDefinition '" + beanName + "' is defined");
        }
        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public void preInstantiateSingletons() {
        for (String beanName : beanDefinitionMap.keySet()) {
            getBean(beanName);
        }
    }
}
