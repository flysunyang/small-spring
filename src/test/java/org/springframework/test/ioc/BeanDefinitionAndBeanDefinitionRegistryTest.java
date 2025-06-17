package org.springframework.test.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.test.ioc.service.HelloService;

public class BeanDefinitionAndBeanDefinitionRegistryTest {

    @Test
    void testBeanDefinitionAndBeanDefinitionRegistry() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        Assertions.assertThrows(BeansException.class, () -> {
            beanFactory.getBean("helloService", HelloService.class);
        }, "beanName:[helloService] initialization failed");
        beanFactory.registry("helloService", new BeanDefinition(HelloService.class));
        HelloService helloService = beanFactory.getBean("helloService", HelloService.class);
        Assertions.assertNotNull(helloService);
        Assertions.assertEquals(helloService.sayHello(), "hello");
    }
}
