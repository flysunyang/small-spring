package org.springframework.context.event;

public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);

}
