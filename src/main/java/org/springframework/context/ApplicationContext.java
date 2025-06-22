package org.springframework.context;

import org.springframework.bean.factory.support.HierarchicalBeanFactory;
import org.springframework.bean.factory.support.ListableBeanFactory;
import org.springframework.context.event.ApplicationEventPublisher;

public interface ApplicationContext extends HierarchicalBeanFactory, ListableBeanFactory, ApplicationEventPublisher {
    
}
