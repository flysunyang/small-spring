package org.springframework.aop;

import lombok.Getter;

public class TargetSource {
    
    @Getter
    public final Object target;

    public Class<?>[] getTargetClass() {
        return this.target.getClass().getInterfaces();
    }

    public TargetSource(Object target) {
        this.target = target;
    }
}
