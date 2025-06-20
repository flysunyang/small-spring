package org.springframework.context.support;

import org.springframework.bean.factory.support.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;

public abstract class AbstractApplicationContext implements ConfigurableApplicationContext {

    @Override
    public void refresh() {
        // 创建 BeanFactory，并加载 BeanDefinition
        refreshBeanFactory();
        
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 提前实例化单例 bean
        beanFactory.preInstantiateSingletons();
    }

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    protected abstract void refreshBeanFactory();

    @Override
    public Object getBean(String beanName) {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> type) {
        return getBeanFactory().getBean(beanName, type);
    }

}
