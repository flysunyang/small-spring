package org.springframework.test.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.service.UserService;
import org.springframework.test.service.UserServiceImpl;

import java.lang.reflect.Proxy;

public class ProxyTest {
    
    @Test
    void testJdkProxy() {
        UserService userService = (UserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{UserService.class}, (proxy, method, args) -> "你被代理了");
        String userInfo = userService.getUserInfo();
        Assertions.assertNotNull(userInfo);
        Assertions.assertTrue(userService.getClass().getName().contains("Proxy"));
    }
    
    @Test
    void testCglibProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> proxy.invokeSuper(obj, args));
        UserService proxy = (UserService) enhancer.create();
        String userInfo = proxy.getUserInfo();
        Assertions.assertNotNull(userInfo);
        Assertions.assertTrue(proxy.getClass().getName().contains("$$"));
    }
}
