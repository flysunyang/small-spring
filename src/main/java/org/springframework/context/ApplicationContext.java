package org.springframework.context;

import org.springframework.bean.factory.support.HierarchicalBeanFactory;
import org.springframework.bean.factory.support.ListableBeanFactory;

public interface ApplicationContext extends HierarchicalBeanFactory, ListableBeanFactory {
    
}
