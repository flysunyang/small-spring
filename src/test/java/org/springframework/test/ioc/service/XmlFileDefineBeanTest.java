package org.springframework.test.ioc.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.test.ioc.bean.Car;
import org.springframework.test.ioc.bean.Person;

public class XmlFileDefineBeanTest {
    
    @Test
    void testXmlFile() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");
        
        Person person = (Person) beanFactory.getBean("person");
        Assertions.assertNotNull(person);
        Assertions.assertEquals(person.getName(), "tony");
        Assertions.assertEquals(person.getAge(), 18);
        Assertions.assertEquals(person.getCar().getBrand(), "BMW");

        Car car = (Car) beanFactory.getBean("car");
        Assertions.assertNotNull(car);
        Assertions.assertEquals(car.getBrand(), "BMW");
    }
}
