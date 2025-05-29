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
    void testPopulateBeanWithBean() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        PropertyValues propertyValuesForCar = new PropertyValues();
        propertyValuesForCar.addPropertyValue(new PropertyValue("brand", "BMW"));
        BeanDefinition carBeanDefinition = new BeanDefinition(Car.class, propertyValuesForCar);
        beanFactory.registerBeanDefinition("car", carBeanDefinition);

        PropertyValues propertyValuesForPerson = new PropertyValues();
        propertyValuesForPerson.addPropertyValue(new PropertyValue("name", "tony"));
        propertyValuesForPerson.addPropertyValue(new PropertyValue("age", 18));
        propertyValuesForPerson.addPropertyValue(new PropertyValue("car", new BeanReference("car")));
        BeanDefinition personBeanDefinition = new BeanDefinition(Person.class, propertyValuesForPerson);
        beanFactory.registerBeanDefinition("person", personBeanDefinition);
        
        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
        Assertions.assertNotNull(person);
        Assertions.assertEquals(person.getName(), "tony");
        Assertions.assertEquals(person.getAge(), 18);
        Car car = person.getCar();
        Assertions.assertNotNull(car);
        Assertions.assertEquals(car.getBrand(), "BMW");
    }
}
