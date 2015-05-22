alt-log5j
=========

Github fork of Spinn3r's Log5j with a number of improvements.

Based on: https://bitbucket.org/burtonator/log5j/wiki/Home

First, an apology. Java logging is a morass of competing and incompatible
frameworks, so I'm sorry to add yet another variation to this sadly complex
world.

The rationale: Log5j's APIs are actually a lot nicer to use than the more popular
SLF4J APIs. For example, it has printf-style formatting (much more handy than
{}'s, with better control over formatting), and automatic determination of the
logger name, so you don't have to retype the class name. But it was missing some
needed features:

- Support for Logback. Log5j by default uses Log4j, but it's impossible to use
  some libraries with just Log4j nowadays (and no, log4j-over-slf4j isn't ideal
  either, for a few reasons).

- Support for an optional, arbitrary "detail" objects on log events. A detail
  is an arbitrary object attached to any log event that implements the `Detail`
  marker interface. For compatibility with Logback or other log systems, the
  object is simply wrapped in an empty throwable (`WrappedDetail`) so it can be
  attached to any log event. Existing log appenders write the detail following
  the log line, like an exception, while custom appenders can unwrap and handle
  these `Detail` objects in special, desired ways.  For example, a large object
  can be logged without any serialization into a usual log string, and then
  accessed directly (e.g. by a custom appender that writes to a database or
  streams the object to a browser-based viewer).

- Support for SLF4J Markers. These allow any log message to be categorized or
  "marked", allowing new ways to slice and dice logs without relying on severity
  or logger name (Java class).

- More configuration support, including file-based configuration

This fork is just a modification of Log5j to support a few such fixes.

Many thanks to [Kevin Burton](https://bitbucket.org/burtonator) for the 
original work.
