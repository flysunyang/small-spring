package org.springframework;

import cn.hutool.core.io.IoUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.DefaultResourceLoader;
import org.springframework.core.Resource;

import java.io.IOException;

public class DefaultResourceLoaderTest {

    @Test
    void testDefaultResource() throws IOException {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:hello.txt");
        String context = IoUtil.readUtf8(resource.getInputStream());
        Assertions.assertNotNull(context);
        Assertions.assertEquals(context, "hello world");

        resource = resourceLoader.getResource("C:\\dev\\code\\learn\\small-spring\\src\\test\\resources\\hello.txt");
        context = IoUtil.readUtf8(resource.getInputStream());
        Assertions.assertNotNull(context);
        Assertions.assertEquals(context, "hello world");

        resource = resourceLoader.getResource("https://www.baidu.com");
        context = IoUtil.readUtf8(resource.getInputStream());
        Assertions.assertNotNull(context);
    }
}
