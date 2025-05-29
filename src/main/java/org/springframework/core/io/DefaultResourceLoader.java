package org.springframework.core.io;

import java.net.URL;

public class DefaultResourceLoader implements ResourceLoader {

    private final static String CLASSPATH_PREFIX = "classpath:";

    @Override
    public Resource getResource(String location) {
        if (location.startsWith(CLASSPATH_PREFIX)) {
            return new ClasspathResource(location.substring(CLASSPATH_PREFIX.length()));
        } else {
            try {
                return new UrlResource(new URL(location));
            } catch (Exception e) {
                return new FileSystemResource(location);
            }
        }
    }

}
