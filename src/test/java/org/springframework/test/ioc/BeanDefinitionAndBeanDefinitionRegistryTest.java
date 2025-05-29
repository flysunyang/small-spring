package org.springframework.test.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.test.ioc.service.HelloService;

public class BeanDefinitionAndBeanDefinitionRegistryTest {
    
    @Test
    void testBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("helloService", new BeanDefinition(HelloService.class));
        
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        Assertions.assertNotNull(helloService);
        Assertions.assertEquals(helloService.sayHello(), "hello");
    }
}
