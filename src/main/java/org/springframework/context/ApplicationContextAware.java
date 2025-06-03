package org.springframework.context;

import org.springframework.beans.Aware;
import org.springframework.beans.BeansException;

public interface ApplicationContextAware extends Aware {
    
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
