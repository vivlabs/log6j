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

package com.spinn3r.log5j.factories;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggingEvent;
import com.spinn3r.log5j.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

import javax.annotation.Nullable;
import java.util.EnumMap;

/**
 * Internal logging implementation for Log5j that uses Logback (instead of log4j).
 * Unlike the other simple implementations, it also supports slf4j Markers.
 * <p/>
 * Created by jlevy.
 * Date: 9/24/13
 */
public class LogbackInternalLoggerFactory implements InternalLoggerFactory {
    private static final EnumMap<LogLevel, Level> levelMapping;

    static {
        levelMapping = new EnumMap<>(LogLevel.class);
        levelMapping.put(LogLevel.TRACE, Level.TRACE);
        levelMapping.put(LogLevel.DEBUG, Level.DEBUG);
        levelMapping.put(LogLevel.INFO, Level.INFO);
        levelMapping.put(LogLevel.WARN, Level.WARN);
        levelMapping.put(LogLevel.ERROR, Level.ERROR);
        levelMapping.put(LogLevel.FATAL, Level.ERROR);
    }

    public LogbackInternalLoggerFactory() {
    }

    public InternalLogger create(String logName) {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        final Logger logger = lc.getLogger(logName);

        return new InternalLogger() {
            public boolean isEnabled(LogLevel level) {
                return logger.isEnabledFor(toLogbackLevel(level));
            }

            public void log(LogEvent event) {
                Throwable t = event.throwable();
                LoggingEvent loggingEvent = new InternalLoggingEvent(
                        LogbackInternalLoggerFactory.class.getName(),
                        logger,
                        event.marker(),
                        toLogbackLevel(event.level()),
                        event.message(), // This formats the message.
                        t,
                        event.threadName());
                logger.callAppenders(loggingEvent);
            }
        };
    }

    public void shutdown() {
        // TODO: Do anything here?
    }

    private Level toLogbackLevel(LogLevel level) {
        return levelMapping.get(level);
    }

    static class InternalLoggingEvent extends LoggingEvent {
        InternalLoggingEvent(String fqcn, Logger logger, @Nullable Marker marker, Level level, String message, Throwable throwable, String threadName) {
            super(fqcn, logger, level, message, throwable, null); // Null argArray disables formatting.
            setThreadName(threadName);
            setMarker(marker != null ? marker : Settings.get().getDefaultMarker());
        }
    }
}
