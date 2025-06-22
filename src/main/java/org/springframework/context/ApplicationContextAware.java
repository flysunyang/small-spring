package org.springframework.context;

import org.springframework.bean.Aware;
import org.springframework.bean.BeansException;

public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
