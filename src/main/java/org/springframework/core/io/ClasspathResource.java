package org.springframework.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClasspathResource implements Resource {
    
    private final String classpath;

    public ClasspathResource(String classpath) {
        this.classpath = classpath;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(classpath);
        if (inputStream == null) {
            throw new FileNotFoundException(this.classpath + "cannot be opened because it does not exist");
        }
        return inputStream;
    }
}
