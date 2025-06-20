package org.springframework.core;

public interface ResourceLoader {

    Resource getResource(String location);
    
}
