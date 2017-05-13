## web工程模块，典型的java web项目。包括网易(杭州)工作项目代码总结
- 引入与web操作相关的依赖jar包。
- 开发与web相关的业务，巩固一些基础（如struts实现过程）

## lq.web.config包
- 放置与properties文件读取相关的类

- 利用@Configuration静态读取properties（延伸：properties文件里配置好json文件的路径，然后在类中利用线程定时加载json数据，反序列化到内存中。实际也是利用了@Configuration注入路径的字符串）
  - json的作用一般在前期代替数据库管理后台的作用。
  
- 动态读取properties文件（利用Thread），通过xml声明引入的属性文件，得到其他属性文件路径名，再利用Resource等工具类注入。父模板和通用工具类写在了common工程模块中。

- 以上是动态读取peoperties文件，其实读取json文件或者其他文件都是类似的，可以做成一个通用的动态读取文件的方法。(关键点在于开启线程时机和获取文件最后一次修改时间，都是现成的方法)

## lq.test.urlmapper包
- UrlMapperController：测试spring mvc的URL路径映射

## record.simple包

### io包

- InOutResource.java 

  - 2016.07.16 从资源文件中读取数据并刷新内存数据。

  - 这里主要针对json数据，在进入app时有欢迎页，而欢迎页相关的数据信息就在json资源文件中。在启动tomcat的时候，会调用此方法，导入json数据，然后赋值到此对象中，相当于刷新内存。

  - 另外项目有定时器，定时调用此方法更新配置数据。

  - Resource类引用了spring core里的包。


### 未迁移过来的包
- OsType.java
  - 2016.07.16 用枚举类表示操作系统的类型

  - 比如app传来了一个osType的参数，先用StringUtils判断是否为空，然后根据此参数找到枚举类再判断，关键代码如下：

		OsType osType = OsType.NULL.genEnumByDescValue(os_typeStr);
		if (osType.equals(OsType.NULL)) {
   		……；
		}

  - 这么做是为了限定传过来的osType参数必须有值且得是规定值，这个时候用枚举类去匹配就很好，如果单纯用字符串去匹配判断，则逻辑代码很长，尤其是当日后增加了参数类型，还得去修改逻辑代码；但用了枚举，则只需增加一个枚举变量即可。

### freemarker包

- FMTools.java
  - 2016.07.22 将freemarker操作的API封装成一个JAVA类。


### mybatis包
- generatorConfig.xml
  - 2016.7.24 利用mybatis generator插件自动生成mybatis文件

  - 使用generatorConfig.xml生成文件时，对于id自增长的情况要修改generatorConfig.xml的配置
    - 对于sqlserver，在 `<table>`标签里要加入：`<generatedKey column="id" sqlStatement="sqlserver" identity="true" />`
    
    - 对于oracle，加入 `<generatedKey column="id" sqlStatement="select idauto.nextval from dual" identity="true" />`
  
- GetSqlSessionFactory.java
  - 2016.8.3 使用单例模式获取SqlSessionFactory对象

  - 公司使用spring框架管理mybatis，而spring默认的是单例模式。这里是学习mybatis做的记录

		<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
			<property name="dataSource" ref="dataSource" />
			<property name="configLocation" value="classpath:yanxuan-mybatis-config.xml" />
			<property name="mapperLocations"
				value="classpath:com/core/dao/mapper/*.xml" />
		</bean>
  - mybatis-config.xml文件放在了resources资源文件夹中

### reflect包
- demo.java
  - 2016.8.3 实现java反射的一些基本操作

### mvc包
  - 模仿struts2，自己手动实现struts2框架，包含的主要技术是：解析xml文件、java反射、对应配置文件标签的封装类。

  - 流程：
	- 先创建类似struts2的xml配置文件
	- `<result>`标签封装到**ResultMapping.java**中
	- `<action>`标签信息封装到**ActionMapping.java**中包含了ResultMapping的集合
	- `<actions>`标签封装到**ActionMappingManager.java**中包含了ActionMapping的集合
	- **Action**类作为接口，设置的模拟action类都实现此接口，运用到了向下转型的知识点。
	- **ActionManager.java**则负责反射实例化实现了Action接口的action业务类。
	
  - 实际最重要的两个类是ActionMappingManager和ActionManager，前者负责将xml配置文件封装成对象，后者负责拿到类名后通过反射实例化对象。后者需要从前者中拿类名。

  - 访问路径   /20160716/list.do

### upload包
- OneFileUpload.java
  - 配置文件中声明上传请求

		<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
			<property name="maxUploadSize" value="2048000000" />
		</bean> 

  - 访问路径
	- 单文件上传： /20160716/springmvc/oneFileUpload.jsp
	- 多文件上传： /20160716/springmvc/moreFileUpload.jsp
	- 下载： /20160716/downloadFile.spring

