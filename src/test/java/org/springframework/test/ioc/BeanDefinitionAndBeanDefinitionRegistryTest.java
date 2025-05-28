package org.springframework.test.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.CglibSubclassingInstantiationStrategy;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class BeanDefinitionAndBeanDefinitionRegistryTest {

    @Test
    void testBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.setInstantiationStrategy(new CglibSubclassingInstantiationStrategy());

        BeansException beansException = Assertions.assertThrows(BeansException.class, () -> {
            beanFactory.getBean("helloService");
        });
        Assertions.assertTrue(beansException.getMessage().contains("No bean named"));

        beanFactory.registerBeanDefinition("helloService", new BeanDefinition(HelloService.class));
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        Assertions.assertEquals(helloService.sayHello(), "hello");
    }

}
