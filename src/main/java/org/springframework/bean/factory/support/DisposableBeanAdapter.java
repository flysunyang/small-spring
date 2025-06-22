package org.springframework.bean.factory.support;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.bean.factory.BeanDefinition;
import org.springframework.bean.factory.config.BeanPostProcessor;
import org.springframework.bean.factory.config.DisposableBean;

import java.lang.reflect.Method;

public class DisposableBeanAdapter implements DisposableBean {

    private final String beanName;

    private final Object bean;

    private final BeanDefinition beanDefinition;

    public DisposableBeanAdapter(String beanName, Object bean, BeanDefinition beanDefinition) {
        this.beanName = beanName;
        this.bean = bean;
        this.beanDefinition = beanDefinition;
    }

    @Override
    public void destroy() throws Exception {
        if (bean instanceof DisposableBean disposableBean) {
            disposableBean.destroy();
        }
        if (StrUtil.isNotEmpty(beanDefinition.getDestroyMethod())) {
            Method method = ClassUtil.getPublicMethod(bean.getClass(), beanDefinition.getDestroyMethod());
            method.invoke(bean);
        }
    }
}
