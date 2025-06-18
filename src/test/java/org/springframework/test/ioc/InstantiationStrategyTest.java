package org.springframework.test.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.CglibSubclassingInstantiationStrategy;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.test.service.HelloService;

public class InstantiationStrategyTest {

    @Test
    void testInstantiationStrategy() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registry("helloService", new BeanDefinition(HelloService.class));

        HelloService helloService = beanFactory.getBean("helloService", HelloService.class);
        Assertions.assertNotNull(helloService);
        Assertions.assertEquals(helloService.sayHello(), "hello");
    }

    @Test
    void testInstantiationStrategyForCglib() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registry("helloService", new BeanDefinition(HelloService.class));
        beanFactory.setInstantiationStrategy(new CglibSubclassingInstantiationStrategy());

        HelloService helloService = beanFactory.getBean("helloService", HelloService.class);
        Assertions.assertNotNull(helloService);
        Assertions.assertEquals(helloService.sayHello(), "hello");
    }
}
