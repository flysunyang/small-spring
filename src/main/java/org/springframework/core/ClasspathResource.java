package org.springframework.core;

import java.io.IOException;
import java.io.InputStream;

public class ClasspathResource implements Resource {
    
    private final String location;

    public ClasspathResource(String location) {
        this.location = location;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return this.getClass().getClassLoader().getResourceAsStream(location);
    }
}
