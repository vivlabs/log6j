alt-log5j
=========

Github fork of spinn3r's log5j with a few small modifications

Based on: https://bitbucket.org/burtonator/log5j/wiki/Home

Log5j's APIs are nicer to use than the more popular SLF4J APIs (for example, it has printf-style formatting and the
automatic determination of the logger name, so you don't have to retype the class name). But it was missing some
small needed features:

- Support for Logback (it's impossible to use some libraries with just Log4j nowadays)
- A few configuration changes, including file-based configuration
