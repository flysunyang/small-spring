package org.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Method;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

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
        Object bean;
        try {
            bean = createBeanInstance(beanDefinition);
            applyPropertyValues(bean, beanName, beanDefinition);
            bean = initializeBean(bean, beanName, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);
        if (beanDefinition.isSingleton()) {
            addSingleton(beanName, bean);    
        }
        return bean;
    }

    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (beanDefinition.isSingleton() && (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName()))) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }

    protected Object initializeBean(Object bean, String beanName, BeanDefinition beanDefinition) {
        if (bean instanceof BeanFactoryAware beanFactoryAware) {
            beanFactoryAware.setBeanFactory(this);
        }
        Object wrappedBean = applyBeanPostProcessorBeforeInitialization(bean, beanName);
        try {
            invokeInitMethod(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
        }
        wrappedBean = applyBeanPostProcessorAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    protected void invokeInitMethod(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        if (bean instanceof InitializingBean initializingBean) {
            initializingBean.afterPropertiesSet();
        }
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName)) {
            Method initMethod = ClassUtil.getPublicMethod(beanDefinition.getBeanClass(), initMethodName);
            if (initMethod == null) {
                throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(bean);
        }
    }

    @Override
    public Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(existingBean, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessAfterInitialization(existingBean, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    protected void applyPropertyValues(Object bean, String beanName, BeanDefinition beanDefinition) {
        try {
            for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValue()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference beanReference) {
                    value = getBean(beanReference.getName());
                }
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (BeansException e) {
            throw new BeansException("Error setting property values for bean:" + beanName, e);
        }
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition) {
        return getInstantiateStrategy().instantiate(beanDefinition);
    }
}
