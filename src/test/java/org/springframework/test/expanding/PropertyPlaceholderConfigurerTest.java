package org.springframework.test.expanding;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClasspathXmlApplicationContext;
import org.springframework.test.bean.Car;

public class PropertyPlaceholderConfigurerTest {
    
    @Test
    void test() {
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext("classpath:property-placeholder-configurer.xml");
        Car car = context.getBean("car", Car.class);
        Assertions.assertEquals(car.getBrand(), "CHERRY");
    }
    
}
