package org.springframework.beans.factory.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BeanReference {
    
    private final String beanName;
    
}
