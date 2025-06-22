package org.springframework.config;

import org.springframework.bean.BeansException;
import org.springframework.bean.PropertyValue;
import org.springframework.bean.factory.BeanDefinition;
import org.springframework.bean.factory.config.BeanFactoryPostProcessor;
import org.springframework.bean.factory.support.ConfigurableListableBeanFactory;

public class UserBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("user");
        beanDefinition.getPropertyValues().add(new PropertyValue("name", "bob"));
    }
}
