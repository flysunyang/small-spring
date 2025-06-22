package org.springframework.common;

import lombok.Getter;
import org.springframework.bean.BeansException;
import org.springframework.bean.factory.BeanFactory;
import org.springframework.bean.factory.BeanFactoryAware;

public class CommonBeanFactory implements BeanFactoryAware {

    @Getter
    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
