package org.springframework.aop;

import lombok.Getter;

/**
 * 被代理的目标对象
 */
public class TargetSource {

    @Getter
    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass() {
        return this.target.getClass().getInterfaces();
    }
}
