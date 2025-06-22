package org.springframework.bean.factory;

import lombok.Getter;
import lombok.Setter;

public class BeanDefinition {

    @Getter
    private Class<?> beanClass;

    @Getter
    private PropertyValues propertyValues;

    private String scope = "singleton";

    @Getter
    private boolean isSingleton = true;

    @Getter
    private boolean isPrototype = false;

    @Getter
    @Setter
    private String initMethod;

    @Getter
    @Setter
    private String destroyMethod;

    public void setScope(String scope) {
        this.scope = scope;
        this.isSingleton = "singleton".equals(scope);
        this.isPrototype = "prototype".equals(scope);
    }

    public BeanDefinition(Class<?> beanClass) {
        this(beanClass, null);
    }

    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }
}
