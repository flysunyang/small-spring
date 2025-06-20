package org.springframework.test.expanding;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClasspathXmlApplicationContext;
import org.springframework.test.bean.Car;

public class PackageScanTest {
    
    @Test
    void testScanPackage() {
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext("classpath:package-scan.xml");
        Car car = context.getBean("car", Car.class);
        Car car2 = context.getBean("car", Car.class);
        Assertions.assertNotNull(car);
        Assertions.assertSame(car, car2);
    }
    
}
