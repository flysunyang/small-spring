package org.springframework.test.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.test.bean.Car;

public class CustomBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomBeanPostProcessor#postProcessorBeforeInitialization, beanName:" + beanName);
        if ("car".equals(beanName)) {
            ((Car) bean).setBrand("BYD");
        }
        return bean;
    }

    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomBeanPostProcessor#postProcessorAfterInitialization, beanName:" + beanName);
        return bean;
    }
}
