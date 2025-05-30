package org.springframework.test.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClasspathXmlApplicationContext;

public class InitAndDestroyMethodTest {
    
    @Test
    void testInitAndDestroyMethod() {
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext("classpath:spring.xml");
        
        context.registerShutdownHook();
    }
}
