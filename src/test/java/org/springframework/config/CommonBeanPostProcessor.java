package org.springframework.config;

import org.springframework.bean.BeansException;
import org.springframework.bean.Car;
import org.springframework.bean.factory.config.BeanPostProcessor;

public class CommonBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CommonBeanPostProcessor.postProcessBeforeInitialization, beanName: " + beanName);
        if (bean instanceof Car car) {
            car.setBrand("XU");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CommonBeanPostProcessor.postProcessAfterInitialization, beanName: " + beanName);
        return bean;
    }
}
