package org.springframework.test.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClasspathXmlApplicationContext;
import org.springframework.test.ioc.bean.Car;

public class PrototypeBeanTest {

    @Test
    void testPrototypeBean() {
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext("classpath:prototype-bean.xml");
        Car car1 = context.getBean("car", Car.class);
        Car car2 = context.getBean("car", Car.class);
        System.out.println(System.identityHashCode(car1));
        System.out.println(System.identityHashCode(car2));
        Assertions.assertNotSame(car1, car2);
    }
}
