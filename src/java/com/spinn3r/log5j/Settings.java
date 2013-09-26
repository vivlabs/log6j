/*
 * Copyright 2013 Joshua Levy
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.spinn3r.log5j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Read log5j settings from properties file, with optional overrides from the system properties.
 * <p/>
 * Created by jlevy.
 * Date: 9/25/13
 */
public class Settings {
    public static final String SETTINGS_FILE = "log5j.properties";

    public static Settings get() {
        return Singleton.INSTANCE;
    }

    private static class Singleton {
        static final Settings INSTANCE = readSettings();
    }

    private static Settings readSettings() {
        Properties prop = new Properties();
        try (InputStream in = Settings.class.getResourceAsStream(SETTINGS_FILE)) {
            prop.load(in);
        } catch (RuntimeException | IOException ignored) {
        }

        return fromProperties(prop);
    }

    private static Settings fromProperties(Properties prop) {
        Settings settings = new Settings();

        settings.async = Boolean.parseBoolean(readSetting(prop,
                "log5j.async", "async", "false"));
        settings.factoryClass = readSetting(prop,
                "log5j.factory", "factory", "com.spinn3r.log5j.factories.LogbackInternalLoggerFactory");
        settings.formatterFactoryClass = readSetting(prop,
                "log5j.formatter.factory", "formatter.factory", "com.spinn3r.log5j.formatter.DefaultMessageFormatterFactory");

        return settings;
    }

    private static String readSetting(Properties prop, String sysPropName, String propName, String defaultValue) {
        String value = System.getProperty(sysPropName);
        if (value == null) {
            value = prop.getProperty(propName, defaultValue);
        }
        return value;
    }

    private Settings() {}

    private boolean async;
    private String factoryClass;
    private String formatterFactoryClass;

    public boolean isAsync() {
        return async;
    }

    public String getFactoryClass() {
        return factoryClass;
    }

    public String getFormatterFactoryClass() {
        return formatterFactoryClass;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "async=" + async +
                ", factoryClass='" + factoryClass + '\'' +
                ", formatterFactoryClass='" + formatterFactoryClass + '\'' +
                '}';
    }
}
