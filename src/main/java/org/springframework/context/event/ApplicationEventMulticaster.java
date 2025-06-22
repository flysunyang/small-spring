package org.springframework.context.event;

public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<ApplicationEvent> listener);

    void removeApplicationListener(ApplicationListener<ApplicationEvent> listener);

    void multicastEvent(ApplicationEvent event);

}
