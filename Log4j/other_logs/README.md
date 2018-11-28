* https://github.com/elvishew/xLog
* https://github.com/pmwmedia/tinylog
* https://github.com/orhanobut/logger
* https://github.com/fengzhizi715/SAF-Kotlin-log
* https://github.com/danylovolokh/VoNaLogger
* https://github.com/tony19/logback-android
* https://github.com/vbauer/herald
* https://github.com/qos-ch/slf4j

## logback.xml
### configuration
属性：  
* defug 为true时，打印logback内部日志信息  
### timestamp
属性：  
* key 标识时间戳的名字  
* datePattern 设置当前时间  
### loger
用来设置某一个包或者具体的某一个类的日志。  
loger可以包含零个或多个appender-ref。
属性：  
* name 用来指定受此loger约束的包或类  
* addtivity 是否向上级传递打印信息，默认为true  
### appender
属性：  
* name appender的名字  
* class appender的类  
ConsoleAppender的字节点：  
* encoder 格式化  
* target System.out或System.err,默认System.out  
FileAppender的字节点：  
* file 被写入的文件名，可以是绝对目录和相对目录，如果上级目录不存在会自动创建，没有默认值。  
* append 默认true。
* encoder 格式化。
* prudent 如果是true，日志会被安全写入，效率低。默认false。
