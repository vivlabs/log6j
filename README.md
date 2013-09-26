alt-log5j
=========

Github fork of Spinn3r's Log5j with a few small modifications

Based on: https://bitbucket.org/burtonator/log5j/wiki/Home

First, an apology. Java logging is a morass of competing and incompatible
frameworks, so I'm sorry to add yet another variation to this sadly complex
world.

The rationale: Log5j's APIs are actually a lot to use than the more popular
SLF4J APIs. For example, it has printf-style formatting (much more handy than
{}'s), and automatic determination of the logger name, so you don't have to
retype the class name.But it was missing some small needed features:

- Support for Logback. Log5j by default uses Log4j, but it's impossible to use
  some libraries with just Log4j nowadays (and no, log4j-over-slf4j isn't ideal
  either, for a few reasons)
- More configuration support, including file-based configuration

This fork is just a modification of Log5j to support a few such fixes.

Many thanks to [Kevin Burton](https://bitbucket.org/burtonator) for the 
original work.
