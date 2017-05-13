## common工程模块，与公共职能相关。
- pom.xml文件基本引入了项目所依赖的所有公共jar包。
- 开发一些实用的共用方法（如各种工具类）。

## lq.common.utils.config包
### FileConfigWatch,AutoConfig .java
- 抽象类，可自动重新载入资源配置，属性注入由具体子类实现,体现出了java的多态性。还是搭配@Configuration注解注入其他properties文件路径或者json文件路径。
- 其实实现方法也是利用Thread定时跑而已。
- 顺便写个AutoConfig的通用抽象模板，子类直接继承即可实现动态加载properties文件。
- FileConfigWatch是工具类，AutoConfig是模板，AutoConfig中调用FileConfigWatch对象。因此要想办法保证每个文件只开启一个线程，不然每调用一次FileConfigWatch就会重启一个新线程，会有冗余。
  - 目前做法是在FileConfigWatch中用static Map方法，用文件名作为key，FileConfigWatch对象作为value，以保证新的文件才会去new一个新对象（线程开启放在了构造函数中）。
  
## lq.common.utils.controller包
### BaseAjaxController .java
- 抽象类，所有异步Controller的基类，与返回json格式相关联。

## lq.common.utils.domain.vo包
- 存放公共页面交互对象，如返回到前端的统一封装的AjaxResult类，统一封装Json数据的JsonResult类等。

### lq.common.utils.log包
- 对于log4j发送邮件的功能：Log4j的SMTP的级别默认是ERROR级别，不过通过代码可以自定义级别。