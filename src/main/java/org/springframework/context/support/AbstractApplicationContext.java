package org.springframework.context.support;

import org.springframework.bean.BeansException;
import org.springframework.bean.factory.config.BeanFactoryPostProcessor;
import org.springframework.bean.factory.config.BeanPostProcessor;
import org.springframework.bean.factory.support.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.*;

import java.util.Map;

public abstract class AbstractApplicationContext implements ConfigurableApplicationContext {

    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() {
        // 创建 BeanFactory，并加载 BeanDefinition
        refreshBeanFactory();
        // 获取 BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        registerApplicationContextProcessor(beanFactory);

        // 执行 BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessor(beanFactory);
        invokeBeanPostProcessor(beanFactory);

        initApplicationEventMulticaster();
        registerListeners();

        // 提前实例化单例 bean
        beanFactory.preInstantiateSingletons();

        finishRefresh();
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshEvent(this));
    }

    private void registerListeners() {
        for (ApplicationListener applicationListener : getBeansOfType(ApplicationListener.class).values()) {
            applicationEventMulticaster.addApplicationListener(applicationListener);
        }
    }

    private void initApplicationEventMulticaster() {
        applicationEventMulticaster = new SimpleApplicationEventMulticaster();
        getBeanFactory().addSingleton("applicationEventMulticaster", applicationEventMulticaster);
    }

    protected void registerApplicationContextProcessor(ConfigurableListableBeanFactory beanFactory) {
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
    }

    protected void invokeBeanPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        for (BeanPostProcessor beanPostProcessor : beanFactory.getBeansOfType(BeanPostProcessor.class).values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    protected void invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessorBeanFactory(beanFactory);
        }
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

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::doClose));
    }

    @Override
    public void close() {
        doClose();
    }

    protected void doClose() {
        publishEvent(new ContextClosedEvent(this));
        destroyBeans();
    }

    protected void destroyBeans() {
        getBeanFactory().destroySingletons();
    }

    @Override
    public <T> T getBean(Class<T> type) throws BeansException {
        return getBeanFactory().getBean(type);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

}
