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

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Read Log6j settings from a properties file, with optional overrides from the system properties.
 * See {@link Setting} for available settings.
 * <p/>
 * Created by jlevy.
 * Date: 9/25/13
 */
public class Settings {

    public static final String SETTINGS_FILE = "log6j.properties";
    public static final String PROPERTY_PREFIX = "log6j.";

    public static Settings get() {
        return Singleton.INSTANCE;
    }

    private static class Singleton {
        static final Settings INSTANCE = readSettings();
    }

    private static Settings readSettings() {
        Properties prop = new Properties();
        try (InputStream in = ClassLoader.getSystemResourceAsStream(SETTINGS_FILE)) {
            prop.load(in);
        } catch (RuntimeException | IOException ignored) {
        }

        return fromProperties(prop);
    }

    private static Settings fromProperties(Properties prop) {
        Settings settings = new Settings();

        settings.async = Boolean.parseBoolean(Setting.ASYNC_DEFAULT.readFrom(prop));
        settings.factoryClass = Setting.FACTORY_CLASS.readFrom(prop);
        settings.formatterFactoryClass = Setting.FORMATTER_FACTORY_CLASS.readFrom(prop);
        String markerString = Setting.MARKER_DEFAULT.readFrom(prop);
        settings.defaultMarker = markerString == null ? null : MarkerFactory.getMarker(markerString);
        settings.detailMax = Integer.valueOf(Setting.DETAIL_MAX.readFrom(prop));

        return settings;
    }

    private Settings() {
    }

    private boolean async;
    private String factoryClass;
    private String formatterFactoryClass;
    private Marker defaultMarker;
    private int detailMax;

    public boolean isAsync() {
        return async;
    }

    public String getFactoryClass() {
        return factoryClass;
    }

    public String getFormatterFactoryClass() {
        return formatterFactoryClass;
    }

    public Marker getDefaultMarker() {
        return defaultMarker;
    }

    public int getDetailMax() {
        return detailMax;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "async=" + async +
                ", factoryClass='" + factoryClass + '\'' +
                ", formatterFactoryClass='" + formatterFactoryClass + '\'' +
                ", defaultMarker='" + defaultMarker + '\'' +
                '}';
    }
}
