package org.springframework.test.other;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.service.WorldService;
import org.springframework.test.service.WorldServiceImpl;

import java.lang.reflect.Proxy;

public class ProxyTest {
    
    @Test
    void testJdkProxy() {
        WorldServiceImpl worldServiceImpl = new WorldServiceImpl();
        WorldService worldService = (WorldService) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{WorldService.class}, (proxy, method, args) -> {
            System.out.println("Before method: " + method.getName());
            Object result = method.invoke(worldServiceImpl, args);
            System.out.println("After method: " + method.getName());
            return result;
        });
        worldService.explode();
        Assertions.assertTrue(worldService.getClass().getName().contains("Proxy"));
    }
    
    @Test
    void testCglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(WorldServiceImpl.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            System.out.println("Before method: " + method.getName());
            Object result = proxy.invokeSuper(obj, args);
            System.out.println("After method: " + method.getName());
            return result;
        });
        WorldServiceImpl proxy = (WorldServiceImpl) enhancer.create();
        proxy.explode();
        Assertions.assertTrue(proxy.getClass().getName().contains("$$"));
    }
}
