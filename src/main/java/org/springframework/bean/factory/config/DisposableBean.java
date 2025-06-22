package org.springframework.bean.factory.config;

public interface DisposableBean {

    void destroy() throws Exception;

}
