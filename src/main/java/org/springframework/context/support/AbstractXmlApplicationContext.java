package org.springframework.context.support;

import org.springframework.bean.factory.support.DefaultListableBeanFactory;
import org.springframework.bean.factory.support.xml.XmlBeanDefinitionReader;

public abstract class AbstractXmlApplicationContext extends AbstractRefreshApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = getConfigLocations();
        xmlBeanDefinitionReader.loadBeanDefinitions(locations);
    }

    protected abstract String[] getConfigLocations();
}
