package org.springframework.test.ioc.common;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.test.ioc.dao.UserDao;

import java.lang.reflect.Proxy;
import java.util.Arrays;

public class UserDaoFactoryBean implements FactoryBean<UserDao> {

    @Override
    public UserDao getObject() throws Exception {
        return (UserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{UserDao.class}, (proxy, method, args) -> {
            if (method.getDeclaringClass() == Object.class) {
                return method.invoke(this, args);
            }
            System.out.println(this);
            return "UserDao proxy execute: [method:" + method.getName() + ", proxy: " + proxy + ", args:" + Arrays.toString(args) + "]";
        });
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

}
