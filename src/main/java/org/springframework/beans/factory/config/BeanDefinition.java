package org.springframework.beans.factory.config;

import org.springframework.beans.PropertyValues;

public class BeanDefinition {

    private Class<?> beanClass;

    private PropertyValues propertyValues;
    
    private String initMethodName;
    
    private String destroyMethodName;
    
    private String scope;
    
    private boolean singleton;
    
    private boolean prototype;

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = "singleton".equals(scope);
        this.prototype = "prototype".equals(scope);
    }

    public boolean isSingleton() {
        return singleton;
    }

    public boolean isPrototype() {
        return prototype;
    }

    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public BeanDefinition(Class<?> beanClass) {
        this(beanClass, null);
    }
}
