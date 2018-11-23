参考：  
* http://logging.apache.org/log4j/2.x/  

## 三大组件
1. Logger:  
   负责输出日志信息，并能够对日志信息进行分类。  
2. Appender:  
   定义日志输出的目的地。  
3. Layout:  
   通过在Appender后面附加Layout实现格式化输出。  
   
## 1. 添加配置
gradle项目中添加：  
```
implementation 'org.apache.logging.log4j:log4j-core:2.11.1'
implementation 'org.apache.logging.log4j:log4j-api:2.11.1'
```
## 2. log4j2.xml
例子：  
```
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
        </Console>
        <File name="TimFile" fileName="D:/logs/tim.log">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
        </File>
    </Appenders>
    <Loggers>
        <Logger name="timlog" level="trace" additivity="true">
            <AppenderRef ref="TimFile" />
        </Logger>
        <Root level="error">
            <AppenderRef ref="Console" />
        </Root>
    </Loggers>
</Configuration>
```
在配置文件中，主要有两类，一类是Appenders（定义输出源）;一类是Loggers（定义log类型）。  
additivity="true", 表示会将当前logger特性传递给Root。  

* %d{HH:mm:ss.SSS} 打印log的时间  
* \[%t] 当前线程的名称  
* %-5level 输出日志级别，-5表示左对齐固定输出5个字符，不足右边补0  
* %logger{36} 输出logger名称  
* %msg 日志文本  
* %n 换行  

**其他常用的占位符:**  
* %F 类文件名  
* %M 方法名  
* %L 行号  
* %l 类名、方法名、文件名、行数  

## 3. 按时间，文件大小拆分日志
```
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN" monitorInterval="300">
    <properties>
        <property name="LOG_HOME">D:/logs</property>
        <property name="FILE_NAME">tim</property>
    </properties>
    
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
        </Console>
        <RollingRandomAccessFile name="TimFile" fileName="${LOG_HOME}/${FILE_NAME}.log
            filePattern="${LOG_HOME}/$${date:yyyy-MM}/${FILE_NAME}-%d{yyyy-MM-dd HH-mm}-%i.log">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
            <Policies>
                <TimeBasedTriggeringPolicy interval="1" />
                <SizeBasedTriggeringPolicy size="1024MB" />
            </Policies>
            <DefaultRolloverStrategy max="20" />
        </RollingRandomAccessFile>   
    </Appenders>
    
    <Loggers>
        <Logger name="timlog" level="trace" additivity="false">
            <AppenderRef ref="TimFile"/>
        </Logger>
        <Root level="error">
            <AppenderRef ref="Console" />
        </Root>
    </Loggers>
</Configuration>
```
## 4. 配置不同级别的日志
off > fatal > error > warn > info > debug > trace > all  
```
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN" monitorInterval="300">
    <properties>
        <property name="LOG_HOME">D:/logs</property>
        <property name="FILE_NAME">tim</property>
    </properties>
    
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
        </Console>
        <RollingRandomAccessFile name="InfoFile"
            fileName="${LOG_HOME}/info.log"
            filePattern="${LOG_HOME}/$${date:yyyy-MM}/info-%d{yyyy-MM-dd HH-mm}-%i.log">
            <Filters>
                <ThresholdFilter level="warn" onMatch="DENY" onMismatch="NEUTRAL" />
                <ThresholdFilter level="info" onMatch="ACCEPT" onMismatch="DENY" />
            </Filters>
            <PatternLayout
                pattern="%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
            <Policies>
                <TimeBasedTriggeringPolicy interval="1" />
                <SizeBasedTriggeringPolicy size="10 MB" />
            </Policies>
            <DefaultRolloverStrategy max="20" />
        </RollingRandomAccessFile>
        <RollingRandomAccessFile name="ErrorFile"
            fileName="${LOG_HOME}/error.log"
            filePattern="${LOG_HOME}/$${date:yyyy-MM}/error-%d{yyyy-MM-dd HH-mm}-%i.log">
            <Filters>
                <ThresholdFilter level="fatal" onMatch="DENY" onMismatch="NEUTRAL" />
                <ThresholdFilter level="error" onMatch="ACCEPT" onMismatch="DENY" />
            </Filters>
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
            <Policies>
                <TimeBasedTriggeringPolicy interval="1" />
                <SizeBasedTriggeringPolicy size="10 MB" />
            </Policies>
            <DefaultRolloverStrategy max="20" />
        </RollingRandomAccessFile>
        <RollingRandomAccessFile name="FatalFile"
            fileName="${LOG_HOME}/fatal.log"
            filePattern="${LOG_HOME}/$${date:yyyy-MM}/fatal-%d{yyyy-MM-dd HH-mm}-%i.log">
            <Filters>
                <ThresholdFilter level="fatal" onMatch="ACCEPT" onMismatch="DENY" />
            </Filters>
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
            <Policies>
                <TimeBasedTriggeringPolicy interval="1" />
                <SizeBasedTriggeringPolicy size="10 MB" />
            </Policies>
            <DefaultRolloverStrategy max="20" />
        </RollingRandomAccessFile>
    </Appenders>
    
    <Loggers>
        <Root level="trace">
            <AppenderRef ref="Console" />
            <AppenderRef ref="InfoFile" />
            <AppenderRef ref="ErrorFile" />
            <AppenderRef ref="FatalFile" />
        </Root>
    </Loggers>
</Configuration>
