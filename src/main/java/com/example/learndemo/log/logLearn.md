# LOG4J2日志

-----------------
```html
spingboot集成log4j2日志
第一步: 引入依赖
    1、去除psringboot自带的日志框架依赖

    2、log4j2依赖

    3、lombok依赖(便于后期免于定义Log对象）
第二步: 配置文件
    在resources目录下配置log4j2.xml配置文件
第三步: 配置信息
    在application.properties文件读取log4j2.xml文件
```

-----------------
```html
spingboot集成log4j2日志-异步
Log4j2中的异步日志实现方式有AsyncAppender和AsyncLogger两种。
```


