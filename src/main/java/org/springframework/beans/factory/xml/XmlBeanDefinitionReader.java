package org.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionRegistry;
import org.springframework.beans.factory.config.BeanReference;
import org.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    private static final String REF_ATTR = "ref";
    private static final String ID_ATTR = "id";
    private static final String CLASS_ATTR = "class";
    private static final String INIT_METHOD = "init-method";
    private static final String DESTROY_METHOD = "destroy-method";
    private static final String NAME_ATTR = "name";
    private static final String VALUE_ATTR = "value";
    private static final String SCOPE_ATTR = "scope";

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        super(beanDefinitionRegistry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
        super(resourceLoader, beanDefinitionRegistry);
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        Resource resource = getResourceLoader().getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinition(inputStream);
            }
        } catch (Exception e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(String[] resources) throws BeansException {
        for (String resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    private void doLoadBeanDefinition(InputStream inputStream) {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (!(childNodes.item(i) instanceof Element bean)) {
                continue;
            }
            String id = bean.getAttribute(ID_ATTR);
            String name = bean.getAttribute(NAME_ATTR);
            String className = bean.getAttribute(CLASS_ATTR);
            String destroyMethodName = bean.getAttribute(DESTROY_METHOD);
            String initMethodName = bean.getAttribute(INIT_METHOD);
            String scope = bean.getAttribute(SCOPE_ATTR);
            Class<?> clazz;
            try {
                clazz = Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new BeansException("Cannot find class[" + className + "]");
            }
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setInitMethodName(initMethodName);
            beanDefinition.setDestroyMethodName(destroyMethodName);
            if (StrUtil.isNotEmpty(scope)) {
                beanDefinition.setScope(scope);
            }
            NodeList propertyNodeList = bean.getChildNodes();
            for (int j = 0; j < propertyNodeList.getLength(); j++) {
                if (!(propertyNodeList.item(j) instanceof Element property)) {
                    continue;
                }
                String nameAttribute = property.getAttribute(NAME_ATTR);
                String valueAttribute = property.getAttribute(VALUE_ATTR);
                String refAttribute = property.getAttribute(REF_ATTR);
                if (StrUtil.isEmpty(nameAttribute)) {
                    throw new BeansException("The name attribute cannot be null or empty");
                }
                Object val = valueAttribute;
                if (StrUtil.isNotEmpty(refAttribute)) {
                    val = new BeanReference(refAttribute);
                }
                PropertyValue propertyValue = new PropertyValue(nameAttribute, val);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getBeanDefinitionRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            getBeanDefinitionRegistry().registry(beanName, beanDefinition);
        }
    }
}
