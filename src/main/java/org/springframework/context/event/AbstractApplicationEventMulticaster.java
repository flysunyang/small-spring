package org.springframework.context.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster {
    
    protected final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new HashSet<>();

    @SuppressWarnings("unchecked")
    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

}
