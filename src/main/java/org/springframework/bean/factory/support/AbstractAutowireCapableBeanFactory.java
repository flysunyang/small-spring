package org.springframework.bean.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.bean.BeansException;
import org.springframework.bean.PropertyValue;
import org.springframework.bean.factory.BeanDefinition;
import org.springframework.bean.factory.BeanFactoryAware;
import org.springframework.bean.factory.BeanReference;
import org.springframework.bean.factory.config.BeanPostProcessor;
import org.springframework.bean.factory.config.DisposableBean;
import org.springframework.bean.factory.config.InitializingBean;

import java.lang.reflect.Method;
import java.util.List;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();


    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        return doCreateBean(beanName, beanDefinition);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        Object bean;
        try {
            bean = createBeanInstance(beanName, beanDefinition);
            applyProperties(bean, beanName, beanDefinition);
            bean = initializeBean(bean, beanName, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        if (bean instanceof BeanFactoryAware beanFactoryAware) {
            beanFactoryAware.setBeanFactory(this);
        }

        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

        if (beanDefinition.isSingleton()) {
            addSingleton(beanName, bean);
        }
        return bean;
    }

    private void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (beanDefinition.isSingleton()) {
            if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethod())) {
                registerDisposableBean(beanName, new DisposableBeanAdapter(beanName, bean, beanDefinition));
            }
        }

    }

    protected Object initializeBean(Object bean, String beanName, BeanDefinition beanDefinition) {
        try {
            bean = postProcessBeforeInitialization(bean, beanName);
            bean = initBean(bean, beanName, beanDefinition);
            bean = postProcessAfterInitialization(bean, beanName);
            return bean;
        } catch (BeansException e) {
            throw new BeansException("initialize bean failed", e);
        }
    }

    protected Object initBean(Object bean, String beanName, BeanDefinition beanDefinition) {
        try {
            if (bean instanceof InitializingBean initializingBean) {
                initializingBean.init();
            }
            String initMethod = beanDefinition.getInitMethod();
            if (StrUtil.isNotEmpty(initMethod)) {
                Method method = bean.getClass().getDeclaredMethod(initMethod);
                method.invoke(bean);
            }
            return bean;
        } catch (Exception e) {
            throw new BeansException("Execute init bean['" + beanName + "'] failed", e);
        }
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Object res = bean;
        List<BeanPostProcessor> beanPostProcessorList = getBeanPostProcessors();
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            Object cur = beanPostProcessor.postProcessAfterInitialization(bean, beanName);
            if (cur == null) {
                return res;
            }
            res = cur;
        }
        return res;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Object res = bean;
        List<BeanPostProcessor> beanPostProcessorList = getBeanPostProcessors();
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            Object cur = beanPostProcessor.postProcessBeforeInitialization(bean, beanName);
            if (cur == null) {
                return res;
            }
            res = cur;
        }
        return res;
    }

    protected void applyProperties(Object bean, String beanName, BeanDefinition beanDefinition) {
        try {
            for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValueList()) {
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference beanReference) {
                    value = getBean(beanReference.getName());
                }
                BeanUtil.setFieldValue(bean, propertyValue.getName(), value);
            }
        } catch (Exception e) {
            throw new BeansException("beanName '" + beanName + "' apply properties failed", e);
        }
    }

    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition) {
        return getInstantiationStrategy().instantiate(beanName, beanDefinition);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }


}
