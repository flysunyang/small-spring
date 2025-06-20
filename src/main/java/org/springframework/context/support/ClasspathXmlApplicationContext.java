package org.springframework.context.support;

public class ClasspathXmlApplicationContext extends AbstractXmlApplicationContext {

    private final String[] configLocations;

    public ClasspathXmlApplicationContext(String configLocation) {
        this(new String[]{configLocation});
    }

    public ClasspathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
