package org.springframework.context.event;

import org.springframework.bean.BeansException;
import org.springframework.bean.factory.BeanFactory;
import org.springframework.bean.factory.BeanFactoryAware;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    protected BeanFactory beanFactory;

    protected final List<ApplicationListener<ApplicationEvent>> applicationListenerList = new ArrayList<>();

    @Override
    public void addApplicationListener(ApplicationListener<ApplicationEvent> listener) {
        applicationListenerList.add(listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<ApplicationEvent> listener) {
        applicationListenerList.remove(listener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
