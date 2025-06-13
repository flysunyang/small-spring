package org.springframework.context.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public interface ApplicationEventMulticaster {
    
    void addApplicationListener(ApplicationListener<ApplicationEvent> listener);
    
    void removeApplicationListener(ApplicationListener<ApplicationEvent> listener);
    
    void multicastEvent(ApplicationEvent event);
    
}
