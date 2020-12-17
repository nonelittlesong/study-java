# 和控制器相关的注解

## 1. @RestController

@RestController = @Controller + @ResponseBody

- `@Controlller` — 将当前修饰的类注入到 SpringBoot IOC 容器中。
- `@ResponseBody` — 将该类中所有 API 方法返回的数据，以 JSON 的形式响应给客户端（String 还是 String）。

## 2. @RequestMapping
