package org.springframework.beans.factory.config;

import org.springframework.beans.factory.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行 BeanPostProcessor 的 postProcessorBeforeInitialization()
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName);


    /**
     * 执行 BeanPostProcessor 的 postProcessorAfterInitialization()
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName);

}
