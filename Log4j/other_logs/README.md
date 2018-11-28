* https://github.com/elvishew/xLog
* https://github.com/pmwmedia/tinylog
* https://github.com/orhanobut/logger
* https://github.com/fengzhizi715/SAF-Kotlin-log
* https://github.com/danylovolokh/VoNaLogger
* https://github.com/tony19/logback-android
* https://github.com/vbauer/herald
* https://github.com/qos-ch/slf4j

## 一、 logback.xml
### 1. configuration
属性：  
* defug 为true时，打印logback内部日志信息  
### 2. timestamp
属性：  
* key 标识时间戳的名字  
* datePattern 设置当前时间  
### 3. loger
用来设置某一个包或者具体的某一个类的日志。  
loger可以包含零个或多个appender-ref。
属性：  
* name 用来指定受此loger约束的包或类  
* addtivity 是否向上级传递打印信息，默认为true  
### 4. appender
**属性：**  
* name appender的名字  
* class appender的类  

**ConsoleAppender的字节点：**  
* encoder 格式化  
* target System.out或System.err,默认System.out  

**FileAppender的字节点：**  
* file 被写入的文件名，可以是绝对目录和相对目录，如果上级目录不存在会自动创建，没有默认值。  
* append 默认true。
* encoder 格式化。
* prudent 如果是true，日志会被安全写入，效率低。默认false。

**RollingFileAppender的字节点：**  
* rollingPolicy 当发生滚动时，决定RollingFileAppender的行为，涉及文件移动和重命名。  
* triggerPolicy 告诉RollingFileAppender何时激活滚动。  
* prudent 当为true时，不支持FixedWindowRollingPolicy，支持TimeBasedRollingPolicy。有两个限制，不能文件压缩，不能设置file属性。

### 4. rollingPloicy
**TimeBasedRollingPolicy:**  
字节点：  
* fileNamePattern %d的默认格式yyyy-MM-dd。RollingFileAppender的file字节点可有可无，通过设置file，可以为活动文件和归档文件指定不同的位置，当前日志总是记录到file指定的文件（活动文件），活动文件的名字不会改变;如果没有设置file，活动文件的名字会根据fileNamePattern的值，每隔一段时间改变一次。  
   e.g. **/foo/%d{yyyy-MM,aux}/%d.log**  
* maxHistory 控制保留归档文件的最大数量。  

