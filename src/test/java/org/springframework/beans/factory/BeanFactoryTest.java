package org.springframework.beans.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BeanFactoryTest {

    @Test
    void testGetBean() {
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.registerBean("helloService", new HelloService());

        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        Assertions.assertNotNull(helloService);
        Assertions.assertEquals(helloService.sayHello(), "hello");
    }

    static class HelloService {
        public String sayHello() {
            System.out.println("hello");
            return "hello";
        }
    }
}
