package org.springframework.bean.factory.support.xml;

import cn.hutool.core.util.StrUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.bean.BeansException;
import org.springframework.bean.PropertyValue;
import org.springframework.bean.factory.BeanDefinition;
import org.springframework.bean.factory.support.AbstractBeanDefinitionReader;
import org.springframework.bean.factory.support.BeanDefinitionRegistry;
import org.springframework.core.Resource;

import java.io.InputStream;
import java.util.List;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    private BeanDefinitionRegistry beanDefinitionRegistry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return beanDefinitionRegistry;
    }

    @Override
    public void loadBeanDefinition(Resource resource) throws BeansException {
        try (InputStream inputStream = resource.getInputStream()) {
            doLoadBeanDefinition(inputStream);
        } catch (Exception e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    @SuppressWarnings("unchecked")
    private void doLoadBeanDefinition(InputStream inputStream) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        List<Element> beanList = root.elements("bean");
        for (Element bean : beanList) {
            String id = bean.attributeValue("id");
            String beanClass = bean.attributeValue("class");
            Class<?> clazz;
            try {
                clazz = Class.forName(beanClass);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            String beanName = StrUtil.isNotEmpty(id) ? id : StrUtil.lowerFirst(clazz.getSimpleName());
            BeanDefinition beanDefinition = new BeanDefinition(clazz);

            List<Element> propertyList = bean.elements("property");
            for (Element property : propertyList) {
                String name = property.attributeValue("name");
                String value = property.attributeValue("value");
                beanDefinition.getPropertyValues().add(new PropertyValue(name, value));
            }

            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }

    @Override
    public void loadBeanDefinitions(String[] locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinition(location);
        }
    }
}
