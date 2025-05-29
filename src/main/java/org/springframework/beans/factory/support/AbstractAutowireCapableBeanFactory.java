package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiateStrategy instantiateStrategy = new SimpleInstantiateStrategy();

    public InstantiateStrategy getInstantiateStrategy() {
        return instantiateStrategy;
    }

    public void setInstantiateStrategy(InstantiateStrategy instantiateStrategy) {
        this.instantiateStrategy = instantiateStrategy;
    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        return doCreateBean(beanName, beanDefinition);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        Object object;
        try {
            object = createBeanInstance(beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, object);
        return object;
    }

    private Object createBeanInstance(BeanDefinition beanDefinition) {
        return getInstantiateStrategy().instantiate(beanDefinition);
    }
}
