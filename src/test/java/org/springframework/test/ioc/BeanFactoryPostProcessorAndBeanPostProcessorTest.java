package org.springframework.test.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.test.bean.Car;
import org.springframework.test.bean.Person;
import org.springframework.test.event.CustomBeanFactoryPostProcessor;
import org.springframework.test.event.CustomBeanPostProcessor;

public class BeanFactoryPostProcessorAndBeanPostProcessorTest {
    
    @Test
    void testBeanFactoryPostProcessor() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        CustomBeanFactoryPostProcessor beanFactoryPostProcessor = new CustomBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        Car car = beanFactory.getBean("car", Car.class);
        Person person = beanFactory.getBean("person", Person.class);
        Assertions.assertNotNull(car);
        Assertions.assertNotNull(person);
        Assertions.assertEquals("bob", person.getName());
    }
    
    @Test
    void testBeanPostProcessor() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        CustomBeanPostProcessor customBeanPostProcessor = new CustomBeanPostProcessor();
        beanFactory.addBeanPostProcessor(customBeanPostProcessor);

        Car car = beanFactory.getBean("car", Car.class);
        Person person = beanFactory.getBean("person", Person.class);
        Assertions.assertNotNull(person);
        Assertions.assertEquals("BYD", car.getBrand());
    }
}
