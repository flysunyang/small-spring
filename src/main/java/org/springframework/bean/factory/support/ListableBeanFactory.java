package org.springframework.bean.factory.support;

import org.springframework.bean.factory.BeanFactory;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {

    <T> Map<String, T> getBeansOfType(Class<T> type);

}

