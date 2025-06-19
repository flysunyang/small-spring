package org.springframework.test.aop;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.service.WorldService;

public class AutoProxyTest {
    
    @Test
    void testAutoProxy() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:auto-proxy.xml");
        WorldService worldService = context.getBean("worldService", WorldService.class);
        worldService.explode();
    }
}
