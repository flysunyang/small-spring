package org.springframework.core;

import java.net.URL;

public class DefaultResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) {
        if (location.startsWith("classpath:")) {
            return new ClasspathResource(location.substring("classpath:".length()));
        }
        try {
            URL url = new URL(location);
            return new UrlResource(url);
        } catch (Exception e) {
            return new FileSystemResource(location);
        }
    }

}
