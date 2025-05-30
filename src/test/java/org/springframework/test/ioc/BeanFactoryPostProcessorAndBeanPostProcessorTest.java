package org.springframework.test.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.test.ioc.bean.Car;
import org.springframework.test.ioc.bean.Person;
import org.springframework.test.ioc.common.CustomBeanFactoryPostProcessor;
import org.springframework.test.ioc.common.CustomerBeanPostProcessor;

public class BeanFactoryPostProcessorAndBeanPostProcessorTest {
    
    @Test
    void testBeanFactoryPostProcessor() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        
        // 加载 xml 文件
        XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);
        definitionReader.loadBeanDefinitions("classpath:spring.xml");
        CustomBeanFactoryPostProcessor customBeanFactoryPostProcessor = new CustomBeanFactoryPostProcessor();
        customBeanFactoryPostProcessor.postProcessorBeanFactory(beanFactory);

        // 获取 bean
        Person person = beanFactory.getBean("person", Person.class);
        Assertions.assertNotNull(person);
        Assertions.assertEquals(person.getName(), "ivy");
        Assertions.assertEquals(person.getCar().getBrand(), "BMW");
        
    }
    
    @Test
    void testBeanPostProcessor() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        CustomerBeanPostProcessor beanPostProcessor = new CustomerBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        Car car = beanFactory.getBean("car", Car.class);
        Assertions.assertNotNull(car);
        Assertions.assertEquals(car.getBrand(), "BYD");
    }
}
