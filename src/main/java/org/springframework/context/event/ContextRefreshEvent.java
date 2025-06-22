package org.springframework.context.event;

import org.springframework.context.ApplicationContext;

public class ContextRefreshEvent extends ApplicationContextEvent{

    public ContextRefreshEvent(ApplicationContext source) {
        super(source);
    }

}
