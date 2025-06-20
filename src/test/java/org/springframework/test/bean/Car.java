package org.springframework.test.bean;

import lombok.Data;
import org.springframework.beans.factory.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class Car {
    
    @Value("${brand}")
    private String brand;
    
}
