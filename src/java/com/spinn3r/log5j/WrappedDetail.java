package com.spinn3r.log5j;

import java.lang.Throwable;

/**
 * We'd like to include special {@link Detail} objects in existing log frameworks like Logback
 * without modification. This is an ugly but helpful way to do that, where we wrap the object
 * in an empty {@link Throwable}.
 * <p>
 * Created by jlevy.
 * Date: 10/27/14
 */
public class WrappedDetail extends Throwable {

    private final Detail detail;

    public WrappedDetail(Detail detail) {
        // Set the message to be a printable version of the detail, and empty out the stack trace
        // so it won't print with off-the-shelf appenders.
        super(detail.toString());
        setStackTrace(new StackTraceElement[0]);
        this.detail = detail;
    }

    @Override
    public String getMessage() {
        return detail.toString();
    }

    public Detail getDetail() {
        return detail;
    }

    @Override
    public String toString() {
        return detail.toString();
    }
}
