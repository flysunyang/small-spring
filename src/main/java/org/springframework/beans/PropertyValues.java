package org.springframework.beans;

import cn.hutool.core.collection.CollUtil;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {

    private final List<PropertyValue> propertyValueList;

    public PropertyValues() {
        this(null);
    }
    
    public PropertyValues(List<PropertyValue> propertyValueList) {
        this.propertyValueList = CollUtil.isNotEmpty(propertyValueList) ? propertyValueList : new ArrayList<>();
    }

    public void addPropertyValue(PropertyValue propertyValue) {
        propertyValueList.add(propertyValue);
    }

    public PropertyValue[] getPropertyValues() {
        return propertyValueList.toArray(new PropertyValue[0]);
    }
}
