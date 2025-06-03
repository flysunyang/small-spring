package org.springframework.test.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClasspathXmlApplicationContext;
import org.springframework.test.ioc.service.HelloService;

public class AwareInterfaceTest {

    @Test
    void testAware() {
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext("classpath:spring.xml");
        HelloService helloService = context.getBean("helloService", HelloService.class);
        Assertions.assertNotNull(helloService);
        Assertions.assertNotNull(helloService.getBeanFactory());
        Assertions.assertNotNull(helloService.getApplicationContext());
    }
}
