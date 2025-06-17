package org.springframework.beans.factory.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.PropertyValues;

public class BeanDefinition {

    private static final String SCOPE_SINGLETON = "singleton";

    private static final String SCOPE_PROTOTYPE = "prototype";
    @Getter
    private Class<?> beanClass;

    @Getter
    @Setter
    private PropertyValues propertyValues;

    @Getter
    @Setter
    private String initMethodName;

    @Getter
    @Setter
    private String destroyMethodName;

    @Getter
    private boolean singleton = true;

    @Getter
    private boolean prototype = false;

    @Getter
    private String scope = SCOPE_SINGLETON;

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    public BeanDefinition(Class<?> beanClass) {
        this(beanClass, null);
    }

    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }
}
