package org.springframework.bean.factory;

import org.springframework.bean.PropertyValue;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {

    private List<PropertyValue> propertyValueList;

    public PropertyValues() {
        propertyValueList = new ArrayList<>();
    }

    public void add(PropertyValue propertyValue) {
        propertyValueList.add(propertyValue);
    }

    public PropertyValue[] getPropertyValueList() {
        return propertyValueList.toArray(new PropertyValue[0]);
    }
}
