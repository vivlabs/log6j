/*
 * Copyright 2010 "Tailrank, Inc (Spinn3r)"
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

import javax.annotation.Nullable;

public abstract class AbstractLoggable implements Loggable {

    private final String _logName;

    private boolean _async;

    private final InternalLogger _logger;

    public AbstractLoggable(String logName,
                            boolean async,
                            InternalLogger logger) {
        _logName = logName;
        _async = async;
        _logger = logger;
    }

    protected void log(LogEvent logEvent) {
        if (_logger.isEnabled(logEvent.level())) {
            if (_async) {
                LogManager.logAsync(logEvent);
            } else {
                logEvent.logger().log(logEvent);
            }
        }
    }

    protected InternalLogger internalLogger() {
        return _logger;
    }

    public String getName() {
        return _logName;
    }

    public void trace(String formatMessage, Object... params) {
        trace(null, formatMessage, params);
    }

    public void trace(@Nullable Marker marker, String formatMessage, Object... params) {
        if (_logger.isEnabled(LogLevel.TRACE)) {
            log(LogEvent.create(_logger, marker, _logName, LogLevel.TRACE,
                    formatMessage, params));
        }
    }

    public void trace(String formatMessage, Throwable t, Object... params) {
        trace(null, formatMessage, t, params);
    }

    public void trace(@Nullable Marker marker, String formatMessage, Throwable t, Object... params) {
        if (_logger.isEnabled(LogLevel.TRACE)) {
            log(LogEvent.create(_logger, marker, _logName, LogLevel.TRACE,
                    formatMessage, params, t));
        }
    }

    public void trace(String formatMessage, Detail d, Object... params) {
        trace(null, formatMessage, d, params);
    }

    public void trace(@Nullable Marker marker, String formatMessage, Detail d, Object... params) {
        trace(marker, formatMessage, new WrappedDetail(d), params);
    }

    public void debug(String formatMessage, Object... params) {
        debug(null, formatMessage, params);
    }

    public void debug(@Nullable Marker marker, String formatMessage, Object... params) {
        if (_logger.isEnabled(LogLevel.DEBUG)) {
            log(LogEvent.create(_logger, marker, _logName, LogLevel.DEBUG,
                    formatMessage, params));
        }
    }

    public void debug(String formatMessage, Throwable t, Object... params) {
        debug(null, formatMessage, t, params);
    }

    public void debug(@Nullable Marker marker, String formatMessage, Throwable t, Object... params) {
        if (_logger.isEnabled(LogLevel.DEBUG)) {
            log(LogEvent.create(_logger, marker, _logName, LogLevel.DEBUG,
                    formatMessage, params, t));
        }
    }

    public void debug(String formatMessage, Detail d, Object... params) {
        debug(null, formatMessage, d, params);
    }

    public void debug(@Nullable Marker marker, String formatMessage, Detail d, Object... params) {
        debug(marker, formatMessage, new WrappedDetail(d), params);
    }

    public void info(String formatMessage, Object... params) {
        info(null, formatMessage, params);
    }

    public void info(@Nullable Marker marker, String formatMessage, Object... params) {
        if (_logger.isEnabled(LogLevel.INFO)) {
            log(LogEvent.create(_logger, marker, _logName, LogLevel.INFO,
                    formatMessage, params));
        }
    }

    public void info(String formatMessage, Throwable t, Object... params) {
        info(null, formatMessage, t, params);
    }

    public void info(@Nullable Marker marker, String formatMessage, Throwable t, Object... params) {
        if (_logger.isEnabled(LogLevel.INFO)) {
            log(LogEvent.create(_logger, marker, _logName, LogLevel.INFO,
                    formatMessage, params, t));
        }
    }

    public void info(String formatMessage, Detail d, Object... params) {
        info(null, formatMessage, d, params);
    }

    public void info(@Nullable Marker marker, String formatMessage, Detail d, Object... params) {
        info(marker, formatMessage, new WrappedDetail(d), params);
    }

    public void warn(String formatMessage, Object... params) {
        warn(null, formatMessage, params);
    }

    public void warn(@Nullable Marker marker, String formatMessage, Object... params) {
        if (_logger.isEnabled(LogLevel.WARN)) {
            if (params != null && params.length > 0
                    && params[0] instanceof Throwable) {
                warn(formatMessage, (Throwable) params[0], withoutFirst(params));
            } else {
                log(LogEvent.create(_logger, marker, _logName, LogLevel.WARN,
                        formatMessage, params));
            }
        }
    }

    public void warn(String formatMessage, Throwable t, Object... params) {
        warn(null, formatMessage, t, params);
    }

    public void warn(@Nullable Marker marker, String formatMessage, Throwable t, Object... params) {
        if (_logger.isEnabled(LogLevel.WARN)) {
            log(LogEvent.create(_logger, marker, _logName, LogLevel.WARN,
                    formatMessage, params, t));
        }
    }

    public void warn(String formatMessage, Detail d, Object... params) {
        warn(null, formatMessage, d, params);
    }

    public void warn(@Nullable Marker marker, String formatMessage, Detail d, Object... params) {
        warn(marker, formatMessage, new WrappedDetail(d), params);
    }

    public void error(String formatMessage, Object... params) {
        error(null, formatMessage, params);
    }

    public void error(@Nullable Marker marker, String formatMessage, Object... params) {
        if (_logger.isEnabled(LogLevel.ERROR)) {
            if (params != null && params.length > 0
                    && params[0] instanceof Throwable) {
                error(formatMessage, (Throwable) params[0], withoutFirst(params));
            } else {
                log(LogEvent.create(_logger, marker, _logName, LogLevel.ERROR,
                        formatMessage, params));
            }
        }
    }

    public void error(String formatMessage, Throwable t, Object... params) {
        error(null, formatMessage, t, params);
    }

    public void error(@Nullable Marker marker, String formatMessage, Throwable t, Object... params) {
        if (_logger.isEnabled(LogLevel.ERROR)) {
            log(LogEvent.create(_logger, marker, _logName, LogLevel.ERROR,
                    formatMessage, params, t));
        }
    }

    public void error(String formatMessage, Detail d, Object... params) {
        error(null, formatMessage, d, params);
    }

    public void error(@Nullable Marker marker, String formatMessage, Detail d, Object... params) {
        error(marker, formatMessage, new WrappedDetail(d), params);
    }

    public void fatal(String formatMessage, Object... params) {
        fatal(null, formatMessage, params);
    }

    public void fatal(@Nullable Marker marker, String formatMessage, Object... params) {
        if (_logger.isEnabled(LogLevel.FATAL)) {
            if (params != null && params.length > 0
                    && params[0] instanceof Throwable) {
                fatal(formatMessage, (Throwable) params[0], withoutFirst(params));
            } else {
                log(LogEvent.create(_logger, marker, _logName, LogLevel.FATAL,
                        formatMessage, params));
            }
        }
    }

    public void fatal(String formatMessage, Throwable t, Object... params) {
        fatal(null, formatMessage, t, params);
    }

    public void fatal(@Nullable Marker marker, String formatMessage, Throwable t, Object... params) {
        if (_logger.isEnabled(LogLevel.FATAL)) {
            log(LogEvent.create(_logger, marker, _logName, LogLevel.FATAL,
                    formatMessage, params, t));
        }
    }

    public void fatal(String formatMessage, Detail d, Object... params) {
        fatal(null, formatMessage, d, params);
    }

    public void fatal(@Nullable Marker marker, String formatMessage, Detail d, Object... params) {
        fatal(marker, formatMessage, new WrappedDetail(d), params);
    }

    private static Object[] withoutFirst(Object... params) {
        Object[] newParams = new Object[params.length - 1];
        if (newParams.length > 0) {
            System.arraycopy(params, 1, newParams, 0, newParams.length);
        }
        return newParams;
    }

}
