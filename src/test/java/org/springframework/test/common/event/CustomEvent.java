package org.springframework.test.common.event;

import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

public class CustomEvent extends ApplicationContextEvent {
    
    @Getter
    private final Long id;

    @Getter
    private final String message;
    
    public CustomEvent(ApplicationContext source, Long id, String message) {
        super(source);
        this.id = id;
        this.message = message;
    }
    
}
