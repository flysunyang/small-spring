package org.springframework.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

import java.util.Date;


public class UserEvent extends ApplicationContextEvent {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private Date date;

    public UserEvent(ApplicationContext source) {
        super(source);
    }

}
