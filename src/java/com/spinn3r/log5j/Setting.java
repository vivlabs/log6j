package com.spinn3r.log5j;

import java.util.Properties;

/**
 * Global logging settings, settable by property file or from system properties.
 * <p/>
 * Created by jlevy.
 * Date: 5/19/15
 */
public enum Setting {

    /**
     * Whether to use async logging.
     */
    ASYNC_DEFAULT("async.default", "false"),

    /**
     * Logger factory.
     */
    FACTORY_CLASS("factory", "com.spinn3r.log5j.factories.LogbackInternalLoggerFactory"),

    /**
     * Formatter factory class.
     */
    FORMATTER_FACTORY_CLASS("formatter.factory", "com.spinn3r.log5j.formatter.DefaultMessageFormatterFactory"),

    /**
     * Default {@link org.slf4j.Marker} name to use if no marker provided.
     */
    MARKER_DEFAULT("marker.default", null);

    private final String name;
    private final String defaultValue;

    Setting(String name, String defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String readFrom(Properties prop) {
        String value = System.getProperty(Settings.PROPERTY_PREFIX + name);
        if (value == null) {
            value = prop.getProperty(name, defaultValue);
        }
        return value;
    }
}
