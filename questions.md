# MyBatis遇到的问题
不同版本的配置不一样

```xml
<!--旧版本依赖-->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper</artifactId>
            <version>3.7.5</version>
        </dependency>
        <dependency>
            <groupId>com.github.jsqlparser</groupId>
            <artifactId>jsqlparser</artifactId>
            <version>0.9.1</version>
        </dependency>
```

```xml
    <!--   PageHelper3.7.5的mybatis配置文件-->
    <plugins>
        <plugin interceptor="com.github.pagehelper.PageHelper">
            <!--            指定方言：mysql分页关键字limit-->
            <property name="dialect" value="mysql"/>
        </plugin>
    </plugins>
```

```xml
<!--新版本依赖-->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper</artifactId>
            <version>5.3.0</version>
        </dependency>

        <dependency>
            <groupId>com.github.jsqlparser</groupId>
            <artifactId>jsqlparser</artifactId>
            <version>4.4</version>
        </dependency>
```



```xml
    <!--    PageHelper5.3.0的mybatis配置文件-->
    <plugins>
        <plugin interceptor="com.github.pagehelper.PageInterceptor">
            <!-- config params as the following -->
    <!--            <property name="helper-dialect" value="mysql"/>-->
        </plugin>
    </plugins>
```