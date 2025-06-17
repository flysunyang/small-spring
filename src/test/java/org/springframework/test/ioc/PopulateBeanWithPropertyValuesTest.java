package org.springframework.test.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanReference;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.test.ioc.bean.Car;
import org.springframework.test.ioc.bean.Person;

public class PopulateBeanWithPropertyValuesTest {

    @Test
    void testPopulateBeanWithPropertyValuesTest() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("brand", "BMW"));
        BeanDefinition carBeanDefinition = new BeanDefinition(Car.class, propertyValues);
        beanFactory.registry("car", carBeanDefinition);

        PropertyValues personPropertyValues = new PropertyValues();
        personPropertyValues.addPropertyValue(new PropertyValue("name", "Alice"));
        personPropertyValues.addPropertyValue(new PropertyValue("car", new BeanReference("car")));
        BeanDefinition personBeanDefinition = new BeanDefinition(Person.class, personPropertyValues);
        beanFactory.registry("person", personBeanDefinition);

        Person person = beanFactory.getBean("person", Person.class);
        Car car = beanFactory.getBean("car", Car.class);

        Assertions.assertNotNull(person);
        Assertions.assertNotNull(car);
        Assertions.assertEquals(car, person.getCar());
    }
}
