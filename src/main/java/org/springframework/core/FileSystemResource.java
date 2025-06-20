package org.springframework.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileSystemResource implements Resource {

    private final String location;

    public FileSystemResource(String location) {
        this.location = location;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        Path path = new File(location).toPath();
        return Files.newInputStream(path);
    }
    
}
