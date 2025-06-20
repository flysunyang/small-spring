package org.springframework.bean.factory;

import lombok.Getter;

public class BeanDefinition {

    @Getter
    private Class<?> beanClass;

    @Getter
    private PropertyValues propertyValues;

    public BeanDefinition(Class<?> beanClass) {
        this(beanClass, null);
    }

    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }
}
