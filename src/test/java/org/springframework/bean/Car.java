package org.springframework.bean;

import lombok.Data;
import org.springframework.bean.factory.config.DisposableBean;
import org.springframework.bean.factory.config.InitializingBean;

@Data
public class Car implements InitializingBean, DisposableBean {

    private String brand;

    public void setup() {
        System.out.println("init-method execute");
    }

    public void afterPropertiesSet() {
        System.out.println("destroy-method execute");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean execute");
    }

    @Override
    public void init() throws BeansException {
        System.out.println("InitializingBean execute");
    }
}
