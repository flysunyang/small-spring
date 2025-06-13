package org.springframework.context;

public interface ApplicationEventPublisher {
    
    void publishEvnet(ApplicationEvent event);
    
}
