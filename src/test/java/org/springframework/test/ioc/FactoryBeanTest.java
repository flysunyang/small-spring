package org.springframework.test.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClasspathXmlApplicationContext;
import org.springframework.test.ioc.bean.Car;
import org.springframework.test.ioc.dao.UserDao;

public class FactoryBeanTest {
    
    @Test
    void testFactoryBean() {
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext("classpath:factory-bean.xml");
        Car car = context.getBean("car", Car.class);
        Assertions.assertEquals(car.getBrand(), "porsche");
    }
    
    @Test
    void testUserDaoFactoryBean() {
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext("classpath:factory-bean.xml");
        UserDao userDao = context.getBean("userDao", UserDao.class);
        Assertions.assertNotNull(userDao);
        System.out.println("userDao:" + userDao);
        String res = userDao.queryUserName("123");
        Assertions.assertNotNull(res);
        System.out.println(res);
    }
}
