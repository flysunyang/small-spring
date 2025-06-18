package org.springframework.test.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

public class CustomEvent extends ApplicationContextEvent {
    
    public CustomEvent(ApplicationContext source) {
        super(source);
    }
    
}
