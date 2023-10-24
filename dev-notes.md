# Spring Boot Security

引入依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

## 关于embed mongodb

老师给的内存mongodb依赖是

```xml
<dependency>
	<groupId>de.flapdoodle.embed</groupId>
	<artifactId>de.flapdoodle.embed.mongo</artifactId>
</dependency>
```

aliyun的镜像源并没有，所以我们切换为默认源，直接把`<mirror>`的内容删掉，下载好依赖后再恢复为阿里云镜像。

令人疑惑的是，`embed mongodb`不支持`@Document`依赖，这就有些费解了。

所以并没有完成`embed mongodb`的要求，而是改用JPA + h2做数据库存储了
