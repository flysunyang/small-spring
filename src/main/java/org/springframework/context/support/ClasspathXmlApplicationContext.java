package org.springframework.context.support;

public class ClasspathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configLocations;

    public ClasspathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    public ClasspathXmlApplicationContext(String configLocation) {
        this(new String[]{configLocation});
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }

}
