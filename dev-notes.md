# Spring Boot Security(Spring Boot 3.0.10)

引入依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

如果只是引入依赖，不做其他操作，SpringBoot会自动增加身份验证，用户名默认user，并在控制台生成一串口令密码

## 关于embed mongodb

老师给的内存mongodb依赖是

```xml
<dependency>
	<groupId>de.flapdoodle.embed</groupId>
	<artifactId>de.flapdoodle.embed.mongo</artifactId>
</dependency>
```

aliyun的镜像源并没有，所以我们切换为默认源，直接把`<mirror>`的内容删掉，下载好[依赖](https://github.com/flapdoodle-oss/de.flapdoodle.embed.mongo)（注明\<version\>）后再恢复为阿里云镜像。

令人疑惑的是，`embed mongodb`不支持`@Document`依赖，这就有些费解了。

* 答：所以仍需引入`starter-data-mongodb`

~~并没有完成`embed mongodb`的要求，而是改用JPA + h2做数据库存储了~~。见下方版本回退办法

## SecurityConfig

### 创建内存用户

```java
@Bean
public InMemoryUserDetailsManager userDetailsManager() {
    UserDetails user1 = User.withUsername("buzz")
            .password("infinity")
            .roles("USER")
            .build();
                                                        
    UserDetails user2 = User.withUsername("woody")
            .password("bullseye")
            .roles("USER")
            .build();
    return new InMemoryUserDetailsManager(user1, user2);
}
```

老师给的提示是

```java
auth
.inMemoryAuthentication()
.withUser("buzz")
.password("infinity")
.authorities("ROLE_USER")
.and()
.withUser("woody")
.password("bullseye")
.authorities("ROLE_USER");
```

上网查资料后发现，这其实是一个被弃用类`WebSecurityConfigurerAdapter`的configure方法里的写法，在SpringBoot 3.0.x时这个类已经被弃用了

### SecurityFilterChain

最迷的部分，各种chain方法连接在一起，还不清楚具体含义

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.
            authorizeHttpRequests(request -> request.anyRequest().authenticated())
            .formLogin()
            .and()
            .csrf().disable()
            .build();
}
```

`.csrf().disable()` 是关闭**跨站请求伪造**防护，允许post请求，防止403 forbidden的问题

### cookie

一次成功登录后，服务端会给客户端返回一串cookie，下次客户端再次访问服务端时，请求头会带上cookie，表示曾经访问过，于是就不需要登录了

我们可以`localhost:8080/logout`退出登录，然后重新login

# Spring Boot Security(Spring Boot 2.7.15)

由于embed mongodb在SpringBoot3.0.x不能用，我们回退到2.7.15版本

* 注意`starter-data-mongodb`也要引入，才能使用`@Document`注解
* 回退后，`javarta`包就得换成`javax`了，也就是说`validation`依赖的路径得重新改一遍:joy:

```xml
<dependency>
    <groupId>de.flapdoodle.embed</groupId>
    <artifactId>de.flapdoodle.embed.mongo</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```

在`application.yml`中指定embed版本

```yaml
spring:
	mongodb:
		embedded:
			version: 5.0.5
```

## `SecurityConfig.java`

继承即将被deprecated的`WebSecurityConfigurerAdapter`方法，重写configure，在其中配置关闭csrf防护和内存用户

```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
                .and()
                    .formLogin()	// 显式声明，否则localhost会拒绝访问
                .and()
                    .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("buzz")
                .password("infinity")
                .authorities("ROLE_USER")
                .and()
                .withUser("woody")
                .password("bullseye")
                .authorities("ROLE_USER");
    }
}

```

By default, Spring Security **form login** is enabled. However, as soon as any servlet-based configuration is provided, **form based login must be explicitly provided**.
