# simple-spring-framework

模拟实现spring中的一些功能

* BeanDefinition
* bean的注册和获取
* 懒加载
* Component扫描注册bean
* 基于autowire的依赖注入
* ApplicationContextAware和BeanNameAware
* 基于代理实现的transactional


## 验证


├── com.wenjie
│   ├── bean
│       ├── TestApplication.java //验证入口启动类
│       ├── processor
│       │   └── WenjieBeanPostProcessor.java // 模拟BeanPostProcessor
│       └── service
│           ├── OrderService.java //验证用服务
│           └── UserService.java //验证用服务
│   └── springframework //模拟框架代码包
