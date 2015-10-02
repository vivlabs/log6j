Log6j
=====

Log6j is a fork of Spinn3r's Log5j with a number of improvements.

Based on: https://bitbucket.org/burtonator/log5j/wiki/Home and https://code.google.com/p/log5j/

## Rationale and features

First, an apology. Java logging is a morass of competing and incompatible
frameworks accumulated over decades. I'm [so sorry](http://xkcd.com/927/)
to add yet another variation to this sadly complex world.

Log5j's APIs are actually a lot nicer to use than the more popular
SLF4J APIs. For example, it has printf-style formatting, which is much more handy
than {}-style formatting, with better control over details. And it has automatic
determination of the logger name from the enclosing class, so you don't have to
retype the class name.

But it was missing some needed features. Log6j has these additions over Log5j:

- **Logback support**: Log5j by default uses Log4j, but it's impossible to use
  some libraries with just Log4j nowadays (and no, using the SLF4J 
  [legacy options](http://www.slf4j.org/legacy.html) also wouldn't be ideal).

- **Detail objects**: Log6j supports the addition of optional, arbitrary "detail"
  objects into log statements. A detail is an arbitrary object attached to any log
  event that implements the `Detail` marker interface. For compatibility with 
  Logback or other log systems, the object is simply wrapped in an empty throwable
  (`WrappedDetail`) so it can be attached to any log event. Existing log
  appenders write the detail following the log line, like an exception, while
  custom appenders can unwrap and handle these `Detail` objects in special,
  desired ways.  For example, a large object can be logged without any
  serialization into a usual log string, and then accessed directly (e.g. by a
  custom appender that writes to a database or specially formats or streams the
  object somewhere else).

- **Marker support**: Log6j supports
  [SLF4J Markers](http://stackoverflow.com/questions/16813032/what-is-markers-in-java-logging-frameworks-and-that-is-a-reason-to-use-them).
  These allow any log message to be categorized or "marked", allowing new
  ways to slice and dice logs without relying on severity or logger name
  (Java class).

- **Configurability**: More configuration support, including whether the system
  uses sync or async logging and a maximum allowed size for writing Detail objects.

- Minor bugfixes.

## Using it

Note this is a direct fork of Log5j. It uses the same old Log5j package
name, `com.spinn3r.log5j`, so it can be dropped into an existing Log5j project.

To build, check out, `mvn package` and use the log6j jarfile. (At the moment,
this is not published as a maven artifact.)

## Credits

Many thanks to [Kevin Burton](https://bitbucket.org/burtonator) for the 
original work.

## License

Apache 2
