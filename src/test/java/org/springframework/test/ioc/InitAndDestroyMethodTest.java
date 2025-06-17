package org.springframework.test.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitAndDestroyMethodTest {
    
    @Test
    void testInitAndDestroyMethod() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-and-destroy-method.xml");
        context.registerShutdownHook();
    }
}
