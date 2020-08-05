## java SPI使用示例

分为三个模块
- java-spi-demo-api
- java-spi-demo-action
- java-spi-demo-impl1
- java-spi-demo-impl2

### 演示效果

在action模块中, 切换pom文件中引入的impl1/impl2, 执行action模块下的Main方法, 达到不修改代码切换目标接口实现的效果.

### 模块代码说明

#### api
`api`模块中定义接口`SpiDemoInterface` 和对应的管理类`SpiDemoInterfaceManager`

接口类定义唯一方法**helloSpi**  
管理类维护一个接口实例, 通过spi的方式加载第一项可用实现, 调用`getInstance`获取维护的实例

#### impl1/impl2

引入`api`模块, 各自编写实现类`SpiDemoImplA/spiDemoImplB`实现接口方法  
编写`resources/META-INF/services`下的SPI配置文件

#### action

引入`api`模块, 在Main方法中通过manager管理类获取接口实例, 执行`helloSpi`方法.

根据impl依赖的引入情况会打印下面三种情况
1. 抛出runtimeException 没有找到任何SpiDemoInterface的实现类
2. 打印Im impl1
3. 打印Im impl2
