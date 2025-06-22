package org.springframework.event;

import org.springframework.context.event.ApplicationListener;
import org.springframework.context.event.ContextRefreshEvent;

public class ContextRefreshEventListener implements ApplicationListener<ContextRefreshEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshEvent event) {
        System.out.println(this.getClass().getName());
    }
}
