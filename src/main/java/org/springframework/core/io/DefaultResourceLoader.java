package org.springframework.core.io;

import java.net.MalformedURLException;
import java.net.URL;

public class DefaultResourceLoader implements ResourceLoader {

    private static final String CLASSPATH_PREFIX = "classpath:";

    @Override
    public Resource getResource(String location) {
        if (location.startsWith(CLASSPATH_PREFIX)) {
            return new ClasspathResource(location.substring(CLASSPATH_PREFIX.length()));
        }
        URL url;
        try {
            url = new URL(location);
            return new UrlResource(url);
        } catch (MalformedURLException e) {
            return new FileSystemResource(location);
        }
    }

}
