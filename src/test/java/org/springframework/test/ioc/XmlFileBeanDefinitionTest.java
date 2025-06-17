package org.springframework.test.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.test.ioc.bean.Car;
import org.springframework.test.ioc.bean.Person;

public class XmlFileBeanDefinitionTest {
    
    @Test
    void testXMLFile() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        Car car = beanFactory.getBean("car", Car.class);
        Person person = beanFactory.getBean("person", Person.class);
        Assertions.assertNotNull(car);
        Assertions.assertNotNull(person);
        Assertions.assertEquals(person.getCar(), car);
    }
}
