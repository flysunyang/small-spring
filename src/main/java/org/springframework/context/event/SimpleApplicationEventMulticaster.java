package org.springframework.context.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener<ApplicationEvent> applicationListener : applicationListeners) {
            if (supportEvent(applicationListener, event)) {
                applicationListener.onApplicationEvent(event);
            }

        }
    }

    private boolean supportEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        Type type = applicationListener.getClass().getGenericInterfaces()[0];
        Type actualTypeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }
        return eventClassName.isAssignableFrom(event.getClass());
    }

}
