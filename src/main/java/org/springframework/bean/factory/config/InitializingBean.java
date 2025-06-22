package org.springframework.bean.factory.config;

import org.springframework.bean.BeansException;

public interface InitializingBean {

    void init() throws BeansException;
}
