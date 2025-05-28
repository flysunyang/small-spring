package org.springframework.beans.factory.config;

/**
 * 单例注册表
 * 
 * @author zhaoyaxu 
 */
public interface SingletonBeanRegistry {
    
    Object getSingleton(String beanName);
    
}
