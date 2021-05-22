# 微信点单系统
## 一、项目简介
**该项目是使用springboot开源框架进行开发，基于微信公众平台的企业级点单系统。在防疫背景下，可由本校学生进行运营，从而解决饿了么和美团外卖不能进校问题。项目利用利用微信公众平台sdk来免除了买家端登录和支付的一些繁杂操作，实现扫码即用。
![](https://yjlhz-qingchengplus.oss-cn-beijing.aliyuncs.com/springboot.jpg)  
<br>项目主要分为买家端和卖家端两个部分：**
**<br>1、买家端：展示给买家端基于微信公众号的点单平台，使用微信点开，一键授权后即可开始使用，可实现常规的点单功能，如商品的选择，下单，支付，退款等。
<br>2、卖家端：展示给卖家端的是一个网页的后台管理系统，商家使用微信扫码即可进行授权登录，获取管理权限，可以实现订单和商品的管理，商品上下架，商品添加修改，商品类目添加，订单完结和取消等功能。
<br>买家端与卖家端利用websocket进行实时通信，买家端下单后卖家端会实时收到一个订单提醒。**
## 二、技术选型
该项目后端部分使用到的技术与工具有：
<br>SpringBoot，Spring，SpringMVC，Mybatis，MySql，Redis，WebSocket，微信公众平台sdk，Nginx，Maven，slf4j等
## 三、项目运行环境及部署
前端代码已经部署到Linux虚拟机上(使用的是centos7.x版本),需要使用的Redis、Nginx、MySQL等已在虚拟机中配置好了，需要查看可联系获取(虚拟机开箱即用，只需简单配置ip即可)。
## 四、源码查看指引
**src/main/java/com/yjlhz/sell/下的各个包：**
<br>aspect: 面向切面编程，用来进行后台管理系统的访问权限校验
<br>config: 配置类，用来提前配置好某些类，使用时可以直接被依赖注入
<br>constant: 对一些常量进行存储，如Redis中key的过期时间
<br>controller: 对外的调用接口
<br>converter: 用于对象转换的转换器
<br>dataobject: 实体类对象，对应MySQL中的字段
<br>dto: 用于传给前端的实体类对象
<br>enums: 枚举层，用于存储商品状态、异常类型、订单转态等
<br>exception: 自定义的异常类
<br>form: 用于接收前端数据的表单
<br>handler: 对特定异常的处理，可根据实际需求来更改，主要与aspect包中捕获的异常配合使用
<br>repository: 资源库，即dao层，连接service与数据库
<br>service: 服务层，书写业务逻辑代码
<br>util: 工具类，可用于初始化某些值，如获取一串随机字符用作订单号，对long型的数据比较大小等
<br>VO: 前端格式需要的实体类
<br>test: 测试类
**/src/main/resources/下的各个包：**
<br>templates: 后台管理系统的ftl文件
<br>static: 需要引入的文件，入mp3文件，错误界面等
## 五、