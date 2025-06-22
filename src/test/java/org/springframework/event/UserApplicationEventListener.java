package org.springframework.event;

import org.springframework.context.event.ApplicationListener;

public class UserApplicationEventListener implements ApplicationListener<UserEvent> {

    @Override
    public void onApplicationEvent(UserEvent event) {
        System.out.println(getClass().getName());
        System.out.println("event: " + event.getId() + "," + event.getDate());
    }
}
