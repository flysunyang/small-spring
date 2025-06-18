package org.springframework.test.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.event.CustomEvent;

public class EventAndEventListenerTest {
    
    @Test
    void testEventListener() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:event.xml");
        context.publishEvent(new CustomEvent(context));
        
        context.close();
    }
}
