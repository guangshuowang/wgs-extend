# wgs-extend
* 扩展包，以依赖包的方式可插拔配置到项目中，单功能单包，最大选择化集成
* wgs-extend-demo：           各依赖包使用示例
* wgs-extend-demo-annotation：分布式锁使用-注解扫描方式加载
* wgs-extend-demo-api：       分布式锁使用-api配置方式加载，文件服务器fdfs-api配置方式加载                        
* wgs-extend-demo-xml：       文件服务器fdfs-配置文件方式加载



## 分布式锁配置
### lock有zklock和redisLock两种，选择配置
* 引入wgs-extend-lock-redis.jar或者wgs-extend-lock-zookeeper.jar
* api配置方式：demo模块LockConfigration类配置
* 配置扫描包路径：com.wgs.extend.lock.zk或com.wgs.extend.lock.redis


## 文件服务器fdfs连接池配置
* 引入wgs-extend-fdfs.jar
* 只支持xml配置文件方式加载，配置好必要的配置项：
``````    wgs:
````      extend:
````        object_pool:
````          minIdle: 0
````          maxIdle: 8
````          maxTotal: 32
````          testOnCreate: false
````          testOnBorrow: true
````          testOnReturn: false
````          testWhileIdle: true
````          blockWhenExhausted: true
````          maxWaitMillis: 5000
````          minEvicTime: 60000
````          timeBetEvicRun: 300000
````        fdfs:
````          charSet: UTF-8
````          trackerServers: xxx.xxx.xxx.xxx:22122,xxx.xxx.xxx.xxx:22122
````          trackerHttpPort: 80
````          connectTimeout: 10000
````          networkTimeout: 30000
````          antiStealToken: false
````          httpSecretKey: xxx1234567890
*    springboot项目启动类中导入配置文件：@ImportResource("classpath*:application-fdfs.xml")即可
*    spring项目在配置文件：<import resource="classpath*:application-fdfs.xml" />

## dubbo服务日志链路（依赖aop）
# 1、引入wgs-extend-dubbo.jar
# 2、启动类配置@ComponentScan(basePackages = {"com.wgs.extend.dubbo.tracelog","当前项目启动类所在包路径"})
# 适用于dubbo微服务调用与被调用端项目，单次请求日志追踪依靠traceId
# 当前dubbo用的2.7.6版本，配置filter的时候group参数：
#    @Activate(group = CommonConstants.PROVIDER)
#    @Activate(group = CommonConstants.CONSUMER)
#    可能别的版本不是这个CommonConstants，而是Constants，看情况处理。


## 多数据源切换支持
# 1、引入wgs-extend-datasource.jar
# 2、启动类配置@ComponentScan(basePackages = {"com.wgs.extend.datasource.config","当前项目启动类所在的包路径"})
         @ImportResource({"自定义的数据源配置文件"})
#        自定义的数据源配置文件里只需要配置bean》com.wgs.extend.datasource.DataSourceStorage，id=dataSourceStorage
#        这个对象里维护了一个map类型属性，是所有数据源：
#        key用来切换数据源依据，和请求中参数是挂钩的，最好是和参数中的某个属性值一样，不然还要单独维护参数和key的映射
#        value是数据源的id
#   以上配置在demo中都由完整的配置参考
