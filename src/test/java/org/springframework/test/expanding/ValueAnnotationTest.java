package org.springframework.test.expanding;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClasspathXmlApplicationContext;
import org.springframework.test.bean.Car;
import org.springframework.test.bean.Person;

public class ValueAnnotationTest {
    
    @Test
    void testValueAnnotation() {
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext("classpath:value-annotation.xml");

        Car car = context.getBean("car", Car.class);
        Assertions.assertEquals(car.getBrand(), "CHERRY");
    }
    
    @Test
    void testAutowiredAnnotation() {
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext("classpath:value-annotation.xml");

        Person person = context.getBean("person", Person.class);
        Assertions.assertNotNull(person.getCar());
    }
}
