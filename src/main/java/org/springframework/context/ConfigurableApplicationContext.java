package org.springframework.context;

import org.springframework.beans.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext {
    
    void refresh() throws BeansException;

    /**
     * 向 JVM 注册一个钩子函数，在 JVM 关闭之前执行关闭容器等操作   
     */
    void registerShutdownHook();
    
    void close();
}
