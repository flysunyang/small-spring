package org.springframework.context.event;

import org.springframework.context.ApplicationContext;

public class ApplicationContextEvent extends ApplicationEvent {

    public ApplicationContextEvent(ApplicationContext source) {
        super(source);
    }

}
