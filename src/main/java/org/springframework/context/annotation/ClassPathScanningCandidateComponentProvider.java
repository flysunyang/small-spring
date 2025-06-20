package org.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> beanClass : classes) {
            BeanDefinition beanDefinition = new BeanDefinition(beanClass);
            candidates.add(beanDefinition);
        }
        return candidates;
    }
    
}
