package org.springframework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.bean.BeansException;
import org.springframework.bean.Car;
import org.springframework.bean.Person;
import org.springframework.bean.User;
import org.springframework.bean.factory.BeanDefinition;
import org.springframework.bean.factory.support.CglibInstantiationStrategy;
import org.springframework.bean.factory.support.DefaultListableBeanFactory;
import org.springframework.bean.factory.support.xml.XmlBeanDefinitionReader;
import org.springframework.common.CommonApplicationContext;
import org.springframework.common.CommonBeanFactory;
import org.springframework.context.support.ClasspathXmlApplicationContext;
import org.springframework.event.UserEvent;

import java.util.Date;

public class IocTest {

    @Test
    void testEvent() {
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext("classpath:event.xml");
        UserEvent userEvent = new UserEvent(context);
        userEvent.setId("1");
        userEvent.setDate(new Date());
        context.publishEvent(userEvent);
        context.close();
    }

    @Test
    void testFactoryBean() {
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext("classpath:factory-bean.xml");
        Car car = context.getBean("car", Car.class);
        Assertions.assertNotNull(car);
        Assertions.assertEquals("BYD", car.getBrand());
    }

    @Test
    void testAware() {
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext("classpath:aware.xml");
        CommonApplicationContext commonApplicationContext = context.getBean("commonApplicationContext", CommonApplicationContext.class);
        Assertions.assertNotNull(commonApplicationContext.getApplicationContext());
        CommonBeanFactory commonBeanFactory = context.getBean("commonBeanFactory", CommonBeanFactory.class);
        Assertions.assertNotNull(commonBeanFactory.getBeanFactory());
    }

    @Test
    void testInitDestroy() {
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext("classpath:init-destroy.xml");
        Car car = context.getBean("car", Car.class);
        Assertions.assertNotNull(car);
        context.registerShutdownHook();
    }

    @Test
    void testBeanPostProcessor() {
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext("classpath:bean-post-processor.xml");
        Car car = context.getBean("car", Car.class);
        Assertions.assertNotNull(car);
        Assertions.assertEquals("XU", car.getBrand());
    }

    @Test
    void testBeanFactoryPostProcessor() {
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext("classpath:bean-post-processor.xml");
        User user = context.getBean("user", User.class);
        Assertions.assertNotNull(user);
        Assertions.assertEquals("bob", user.getName());
    }

    @Test
    void testScope() {
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext("classpath:scope.xml");
        User user = context.getBean("user", User.class);
        User user2 = context.getBean("user", User.class);
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user2);
        Assertions.assertSame(user, user2);

        Car car = context.getBean("car", Car.class);
        Car car2 = context.getBean("car", Car.class);
        Assertions.assertNotNull(car);
        Assertions.assertNotNull(car2);
        Assertions.assertNotSame(car2, car);
        Assertions.assertEquals(car2, car);
    }

    @Test
    void testApplyPropertiesReference() {
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext("classpath:properties-ref.xml");
        Person person = context.getBean("person", Person.class);
        Assertions.assertNotNull(person);
        Assertions.assertNotNull(person.getCar());
        Assertions.assertEquals("BYD", person.getCar().getBrand());
    }

    @Test
    void testSetProperty() {
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext("classpath:spring.xml");
        User user = context.getBean("user", User.class);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.getName(), "alice");
    }

    @Test
    void testClasspathXmlApplicationContext() {
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext("classpath:spring.xml");
        User user = context.getBean("user", User.class);
        Assertions.assertNotNull(user);
    }

    @Test
    void testXmlBeanDefinition() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinition("classpath:spring.xml");
        User user = beanFactory.getBean("user", User.class);
        Assertions.assertNotNull(user);
    }

    @Test
    void testInstantiationStrategy() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.setInstantiationStrategy(new CglibInstantiationStrategy());
        beanFactory.registerBeanDefinition("user", new BeanDefinition(User.class));
        User user = beanFactory.getBean("user", User.class);
        Assertions.assertNotNull(user);
        System.out.println(user.getClass().getName());
    }

    @Test
    void testBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        Assertions.assertThrows(BeansException.class, () -> {
            beanFactory.getBean("user", User.class);
        }, "No beanDefinition 'user' is define");
        beanFactory.registerBeanDefinition("user", new BeanDefinition(User.class));
        User user = beanFactory.getBean("user", User.class);
        Assertions.assertNotNull(user);
        System.out.println(user.getClass().getName());
    }
}
