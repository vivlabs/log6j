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

public class LogSink implements Loggable {

    private static final String LOG_NAME = "logsink";

    private final Loggable _loggables[];

    public String getName() {
        return LOG_NAME;
    }

    public LogSink(Loggable... loggables) {
        _loggables = new Loggable[loggables.length];
        for (int i = 0; i < loggables.length; ++i) {
            _loggables[i] = loggables[i];
        }
    }

    public void trace(String formatMessage, Object... params) {
        for (Loggable loggable : _loggables) {
            loggable.trace(formatMessage, params);
        }
    }

    public void debug(String formatMessage, Object... params) {
        for (Loggable loggable : _loggables) {
            loggable.debug(formatMessage, params);
        }
    }

    public void info(String formatMessage, Object... params) {
        for (Loggable loggable : _loggables) {
            loggable.info(formatMessage, params);
        }
    }

    public void warn(String formatMessage, Object... params) {
        for (Loggable loggable : _loggables) {
            loggable.warn(formatMessage, params);
        }
    }

    public void error(String formatMessage, Object... params) {
        for (Loggable loggable : _loggables) {
            loggable.error(formatMessage, params);
        }
    }

    public void fatal(String formatMessage, Object... params) {
        for (Loggable loggable : _loggables) {
            loggable.fatal(formatMessage, params);
        }
    }

    public void trace(String formatMessage, Throwable t, Object... params) {
        for (Loggable loggable : _loggables) {
            loggable.trace(formatMessage, t, params);
        }
    }

    public void info(String formatMessage, Throwable t, Object... params) {
        for (Loggable loggable : _loggables) {
            loggable.info(formatMessage, t, params);
        }
    }

    public void debug(String formatMessage, Throwable t, Object... params) {
        for (Loggable loggable : _loggables) {
            loggable.debug(formatMessage, t, params);
        }
    }

    public void warn(String formatMessage, Throwable t, Object... params) {
        for (Loggable loggable : _loggables) {
            loggable.warn(formatMessage, t, params);
        }
    }

    public void error(String formatMessage, Throwable t, Object... params) {
        for (Loggable loggable : _loggables) {
            loggable.error(formatMessage, t, params);
        }
    }

    public void fatal(String formatMessage, Throwable t, Object... params) {
        for (Loggable loggable : _loggables) {
            loggable.fatal(formatMessage, t, params);
        }
    }

    public void trace(String formatMessage, Detail d, Object... params) {
        trace(formatMessage, new WrappedDetail(d), params);
    }

    public void info(String formatMessage, Detail t, Object... params) {
        info(formatMessage, new WrappedDetail(t), params);
    }

    public void debug(String formatMessage, Detail t, Object... params) {
        debug(formatMessage, new WrappedDetail(t), params);
    }

    public void warn(String formatMessage, Detail t, Object... params) {
        warn(formatMessage, new WrappedDetail(t), params);
    }

    public void error(String formatMessage, Detail t, Object... params) {
        error(formatMessage, new WrappedDetail(t), params);
    }

    public void fatal(String formatMessage, Detail t, Object... params) {
        fatal(formatMessage, new WrappedDetail(t), params);
    }
}
