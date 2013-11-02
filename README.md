### logunit

logunit is a small and simple framework to allow testing of logging output 
from within unit tests. 

### Code example
The following example uses JUnit 4 as unit testing framework and log4j as logging framework. Read below for instructions on how to use different frameworks. You can browse a couple of examples [here](https://github.com/thombergs/logunit/tree/master/logunit-examples/src/test/java/org/wickedsource/logunit/showcase).

Basically, you define what logging output you expect before calling the code under test. After the code under test has been executed, you call LogUnit.assertExpectations() to verify that all expectations have been fulfilled.

```
@Test
public void test() {
  // retrieve the Log4J implementation of LogUnit
  LogUnit logunit = LogUnit.get();
    
  // use the expect* methods to define what you expect to be logged in
  // your unit test
  logunit.expect("An error occured!", LogLevel.ERROR);

  // produce an error message (in real code you would not do this
  // directly, but call the code under test that does the logging)
  logger.error("An error occured!");

  // assert that the expectations defined earlier were actually fulfilled
  logunit.assertExpectations();
}
```

### Using log4j
Using log4j 1.2.x, you should include the following maven module (or download the jar directly):

#### Maven coordinates
```
<dependency>
  <groupId>org.wickedsource</groupId>
  <artifactId>logunit-log4j12</artifactId>
  <version>1.1</version>
  <scope>test</scope>
</dependency>
```

#### log4j configuration
Configure log4j to use org.wickedsource.logunit.log4j.LogUnitLog4jConsoleAppender as appender. This enables logunit to listen on log events. An example configuration is the following (log4j.properties in the root of the classpath):

```
log4j.rootLogger=debug, stdout
log4j.appender.stdout=org.wickedsource.logunit.log4j.LogUnitLog4jConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%5p [%t] (%F:%L) - %m%n
```

### Using logback
Using logback, you should include the following maven module (or download the jar directly):

#### Maven coordinates
```
<dependency>
  <groupId>org.wickedsource</groupId>
  <artifactId>logunit-logback</artifactId>
  <version>1.1</version>
  <scope>test</scope>
</dependency>
```

#### logback configuration
Configure logback to use org.wickedsource.logunit.logback.LogUnitLogbackConsoleAppender as appender. This enables logunit to listen on log events. An example configuration is the following (logback.xml in the root of the classpath):

```
<configuration>
  <appender name="STDOUT" class="org.wickedsource.logunit.logback.LogUnitLogbackConsoleAppender">
    <encoder>
      <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
    </encoder>
  </appender>
  <root level="debug">
    <appender-ref ref="STDOUT" />
  </root>
</configuration>
```

### Using JUnit 4
If you use JUnit 4 as unit testing framework, simply add the following maven module to your classpath (or download the jar directly). It will then be automatically used by the central LogUnit class.

```
<dependency>
  <groupId>org.wickedsource</groupId>
  <artifactId>logunit-junit4</artifactId>
  <version>1.1</version>
  <scope>test</scope>
</dependency>
```

### Using TestNG
If you use TestNG as unit testing framework, simply add the following maven module to your classpath (or download the jar directly). It will then be automatically used by the central LogUnit class.

```
<dependency>
  <groupId>org.wickedsource</groupId>
  <artifactId>logunit-testng</artifactId>
  <version>1.1</version>
  <scope>test</scope>
</dependency>
```