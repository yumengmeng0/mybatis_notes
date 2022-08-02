# MyBatis笔记

官方文档：https://mybatis.org/mybatis-3/zh/index.html  
GitHub地址：https://github.com/mybatis/mybatis-3

# MyBatis简介
    基于ORM的半主动轻量级持久层框架
    
# ORM（Objective Relational Mapping，对象关系映射）
    O：对象模型：实体对象
    R：关系型数据库的结构模型：数据库表
    M：映射：将实体对象与数据库表建立映射关系
    
    
# XML配置

* MyBatis 的配置文件包含了会深深影响 MyBatis 行为的设置和属性信息。 配置文档的顶层结构如下：
    
    * configuration（配置）
    * properties（属性）
    * settings（设置）
    * typeAliases（类型别名）
    * typeHandlers（类型处理器）
    * objectFactory（对象工厂）
    * plugins（插件）
    * environments（环境配置）
        * environment（环境变量）
        * transactionManager（事务管理器）
        * dataSource（数据源）
    * databaseIdProvider（数据库厂商标识）
    * mappers（映射器）

```xml

```

# XML映射文件

# MyBatis高级查询

## 1.1 ResultMap属性
    建立对象关系映射
    * resultType
        实体类的属性名与表中字段名一致，将查询结果自动封装到实体类中
    * resultMap
        实体类的属性名与表中字段名不一致，使用resultMap手动封装

## 1.2 多条件查询

## 1.3 模糊查询
    #{}和${}区别：
        #{}表示一个占位符
            * 通过#{}实现preparedStatement向占位符设置值，自动进行Java类型和jdbc类型转换，#{}可以有效防止sql注入
            * #{}可以接收简单类型值或pojo属性值
            * 如果parameterType是传输单个简单值，#{}中名称随便写
         
         ${}表示拼接字符串
            * ${}可以将parameterType传入的内容拼接在sql中不进行jdbc类型转换，会出现sql注入问题
            * ${}可以接收简单类型值或pojo属性值
            * 如果parameterType传输单个简单类型值，#{}括号中只能是value
                * TextSqlNode.java

    
## 1.4 返回主键

## 1.5 动态sql

### 1.5.1 if
### 1.5.2 set
### 1.5.3 foreach
### 1.5.4 动态sql
    提取重复的sql语句
    
    
    
# PageHelper


## 数据库表的关系
    一对一
    一对多
    多对多
    * mybatis把多对多看成一对一

## mybatis多表配置
    * 多对一（一对一）：<resultMap> + <association>
    * 一对多：<resultMap> + <collection>
    * 多对多：<resultMap> + <collection>，多对多和一对一很相似，难度在于sql语句的编写
    
## 嵌套查询（复用已定义的查询函数）
    嵌套查询是将原来多表查询中的联合语句拆成单个表的查询，再使用mybatis语法嵌套在一起

### 一对一嵌套查询
### 一对多嵌套查询
### 多对多嵌套查询


# 加载策略（延迟加载基于*嵌套查询*实现）
    * 立即加载
    * 延迟加载（懒加载）：在需要用到数据时才进行加载
        * 优点：先从单表查询，需要时再从关联表查询，大大提高数据库性能
        * 缺点：大批量数据查询可能造成用户等待时间变长
    
    * 多表中
        一对多，多对多，采用延迟加载
        一对一，多对一，立即加载
    

## 全局延迟加载策略和局部迟加载策
局部延迟加载策略优先级高于全局迟加载策

```xml

    <settings>
        <!--        全局延迟加载的开关-->
        <setting name="lazyLoadingEnabled" value="true"/>
    </settings>                   
```

```xml
    <collection fetchType="lazy" >
    </collection>   
```



# MyBatis缓存

## 一级缓存
    SqlSession级别，默认开启
    
    执行SqlSession的C（增加）U（更新）D（删除）操作，或调用从clearCache()、commit()、close()方法，都会清空缓存
    
    <select flushCache="true"></select>
    
## 二级缓存
    namespace级别（跨SqlSession），默认关闭
    实现二级缓存，返回的POJO必须是可序列化的（实现Serializable接口）。
    配置方法：映射文件添加<cache/>

```xml
        <!--        全局性地开启或关闭所有映射器配置文件中已配置的任何缓存。 默认true-->
        <setting name="cacheEnabled" value="true"/>
```

```xml
        <!--映射文件添加-->
        <cache/>
```
### 二级缓存脏读
    * 建议不要使用MyBatis二级缓存，需要第第三方缓存技术解决（Redis）

# 注解开发