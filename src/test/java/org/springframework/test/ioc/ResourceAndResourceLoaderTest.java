package org.springframework.test.ioc;

import cn.hutool.core.io.IoUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.io.InputStream;

public class ResourceAndResourceLoaderTest {
    
    @Test
    void testResourceLoader() throws IOException {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();

        Resource resource = resourceLoader.getResource("classpath:hello.txt");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        Assertions.assertNotNull(content);
        Assertions.assertEquals(content, "hello world");

        resource = resourceLoader.getResource("C:\\dev\\code\\learn\\small-spring\\src\\test\\resources\\hello.txt");
        Assertions.assertTrue(resource instanceof FileSystemResource);
        content = IoUtil.readUtf8(resource.getInputStream());
        Assertions.assertNotNull(content);
        Assertions.assertEquals(content, "hello world");
        
        resource = resourceLoader.getResource("https://www.baidu.com");
        Assertions.assertTrue(resource instanceof UrlResource);
        content = IoUtil.readUtf8(resource.getInputStream());
        Assertions.assertNotNull(content);
    }
}
