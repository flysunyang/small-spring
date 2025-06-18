package org.springframework.test.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.bean.Car;

public class FactoryBeanTest {

    @Test
    void testFactoryBean() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:factory-bean.xml");
        Car car = context.getBean("car", Car.class);
        Car car2 = context.getBean("car", Car.class);
        Assertions.assertSame(car, car2);
    }
}
