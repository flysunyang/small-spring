package org.springframework.bean.factory.support;

import org.springframework.bean.factory.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    @Override
    public Object getBean(String beanName) {
        Object bean = getSingleton(beanName);
        if (bean != null) {
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition);

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBean(String beanName, Class<T> type) {
        return (T) getBean(beanName);
    }

}
