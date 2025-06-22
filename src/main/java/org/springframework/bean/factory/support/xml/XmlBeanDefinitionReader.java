package org.springframework.bean.factory.support.xml;

import cn.hutool.core.util.StrUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.bean.BeansException;
import org.springframework.bean.PropertyValue;
import org.springframework.bean.factory.BeanDefinition;
import org.springframework.bean.factory.BeanReference;
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
            String scope = bean.attributeValue("scope");
            String initMethod = bean.attributeValue("init-method");
            String destroyMethod = bean.attributeValue("destroy-method");
            Class<?> clazz;
            try {
                clazz = Class.forName(beanClass);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            String beanName = StrUtil.isNotEmpty(id) ? id : StrUtil.lowerFirst(clazz.getSimpleName());
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            if (StrUtil.isNotEmpty(scope)) {
                beanDefinition.setScope(scope);
            }
            if (StrUtil.isNotEmpty(initMethod)) {
                beanDefinition.setInitMethod(initMethod);
            }
            if (StrUtil.isNotEmpty(destroyMethod)) {
                beanDefinition.setDestroyMethod(destroyMethod);
            }

            List<Element> propertyList = bean.elements("property");
            for (Element property : propertyList) {
                String name = property.attributeValue("name");
                String value = property.attributeValue("value");
                String ref = property.attributeValue("ref");
                Object valObject = value;
                if (StrUtil.isNotEmpty(ref)) {
                    valObject = new BeanReference(ref);
                }
                beanDefinition.getPropertyValues().add(new PropertyValue(name, valObject));
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
