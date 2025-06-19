package org.springframework.test.aop;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClasspathXmlApplicationContext;
import org.springframework.test.service.UserService;

public class AutoProxyTest {
    
    @Test
    void testAutoProxy() {
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext("classpath:auto-proxy.xml");
        UserService userService = context.getBean("userService", UserService.class);
        String res = userService.register("alice");
        System.out.println(res);
    }
}
