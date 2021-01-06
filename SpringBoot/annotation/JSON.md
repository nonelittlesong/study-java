# [JSON 注解](http://fasterxml.github.io/jackson-annotations/javadoc/2.6/com/fasterxml/jackson/annotation/package-summary.html)

Springboot 使用 jackson 完成对象到 json 的转化。有时候自动转的内容太多，或者不符合我们的期望，因此，需要调整转为 json 的过程。

参考：

- [@JsonIgnoreProperties与@JsonIgnore | CSDN](https://blog.csdn.net/russle/article/details/84147236)
- [json过滤某些属性之@jsonignore | 简书](https://www.jianshu.com/p/54ba009064b0)

## 1. @JsonIgnore

用来忽略某些字段，可以用在变量或者 Getter 方法上，用在 Setter 方法时，效果和变量上一样。  
这个注解一般用在我们要忽略的字段上。

## 2. @JsonIgnoreProperties

- `JsonIgnoreProperties(ignoreUnknown = true)` — 忽略类中不存在的字段
- `@JsonIgnoreProperties({ “property1”, “property2” })` — 忽略类中指定的字段

## 3. @JsonFormat

帮助我们完成格式转换。例如，  
对于 `Date` 类型，默认在 REST 接口返回 long，使用 `@JsonFormat(timezone = “GMT+8”, pattern = “yyyy-MM-dd HH:mm:ss”)`，则返回 `"2018-11-16 22:58:15"`。
