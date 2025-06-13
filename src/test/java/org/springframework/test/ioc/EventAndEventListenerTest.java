package org.springframework.test.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClasspathXmlApplicationContext;
import org.springframework.test.common.event.CustomEvent;

public class EventAndEventListenerTest {
    
    @Test
    void testEventListener() {
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext("classpath:spring-event.xml");
        context.publishEvnet(new CustomEvent(context, 1019129009086763L, "success"));
        context.registerShutdownHook();
    }

}
