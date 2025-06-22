package org.springframework.bean;

import org.springframework.bean.factory.FactoryBean;

public class CarFactoryBean implements FactoryBean<Car> {

    @Override
    public Car getObject() {
        Car car = new Car();
        car.setBrand("BYD");
        return car;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
