package org.springframework.test.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.service.UserService;

public class AwareInterfaceTest {
    
    @Test
    void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService userService = context.getBean("userService", UserService.class);
        Assertions.assertNotNull(userService.getBeanFactory());
        Assertions.assertNotNull(userService.getApplicationContext());
    }
}
