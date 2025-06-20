package org.springframework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.bean.BeansException;
import org.springframework.bean.User;
import org.springframework.bean.factory.BeanDefinition;
import org.springframework.bean.factory.support.CglibInstantiationStrategy;
import org.springframework.bean.factory.support.DefaultListableBeanFactory;
import org.springframework.bean.factory.support.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClasspathXmlApplicationContext;

public class IocTest {

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
