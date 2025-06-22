package org.springframework.bean.factory;

import org.springframework.bean.Aware;
import org.springframework.bean.BeansException;

public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
