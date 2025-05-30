package org.springframework.test.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.ioc.bean.Car;
import org.springframework.test.ioc.bean.Person;

public class ApplicationContextTest {
    
    @Test
    void testApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Person person = applicationContext.getBean("person", Person.class);
        Assertions.assertNotNull(person);
        Assertions.assertEquals(person.getName(), "ivy");

        Car car = applicationContext.getBean("car", Car.class);
        Assertions.assertNotNull(car);
        Assertions.assertEquals(car.getBrand(), "BYD");
    }
}
