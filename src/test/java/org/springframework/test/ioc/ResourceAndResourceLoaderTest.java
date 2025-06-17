package org.springframework.test.ioc;

import cn.hutool.core.io.IoUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;

import java.io.IOException;

public class ResourceAndResourceLoaderTest {
    
    @Test
    void testResourceAndResourceLoader() throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:hello.txt");
        String res = IoUtil.readUtf8(resource.getInputStream());
        System.out.println(res);
        Assertions.assertNotNull(res);

        resource = resourceLoader.getResource("https://www.baidu.com");
        Assertions.assertTrue(resource instanceof UrlResource);
        res = IoUtil.readUtf8(resource.getInputStream());
        Assertions.assertNotNull(res);

        resource = resourceLoader.getResource("src/test/resources/hello.txt");
        Assertions.assertTrue(resource instanceof FileSystemResource);
        res = IoUtil.readUtf8(resource.getInputStream());
        Assertions.assertNotNull(res);
    }
}
