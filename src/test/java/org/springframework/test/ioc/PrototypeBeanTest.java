package org.springframework.test.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.bean.Car;
import org.springframework.test.bean.Person;

public class PrototypeBeanTest {

    @Test
    void testSingleton() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Person person = context.getBean("person", Person.class);
        Person person2 = context.getBean("person", Person.class);
        Car car = context.getBean("car", Car.class);
        Assertions.assertSame(person.getCar(), car);
        Assertions.assertSame(person, person2);
    }

    @Test
    void testPrototype() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:prototype-bean.xml");
        Car car = context.getBean("car", Car.class);
        Car car2 = context.getBean("car", Car.class);
        Assertions.assertNotSame(car, car2);
    }
}
