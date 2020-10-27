# wgs-extend
* 扩展包，以依赖包的方式可插拔配置到项目中，单功能单包，最大选择化集成
## lock配置
* api配置方式：demo模块LockConfigration类配置
* 配置扫描包路径：com.wgs.extend.lock.zk或com.wgs.extend.lock.redis
## fdfs连接池配置
* 只支持xml配置文件方式加载，配置好必要的配置项：
    wgs:
      extend:
        object_pool:
          minIdle: 0
          maxIdle: 8
          maxTotal: 32
          testOnCreate: false
          testOnBorrow: true
          testOnReturn: false
          testWhileIdle: true
          blockWhenExhausted: true
          maxWaitMillis: 5000
          minEvicTime: 60000
          timeBetEvicRun: 300000
        fdfs:
          charSet: UTF-8
          trackerServers: 172.20.32.252:22122,172.20.32.252:22122
          trackerHttpPort: 80
          connectTimeout: 10000
          networkTimeout: 30000
          antiStealToken: false
          httpSecretKey: xxx1234567890
    springboot项目启动类中导入配置文件：@ImportResource("classpath*:application-fdfs.xml")即可
    spring项目在配置文件：<import resource="classpath*:application-fdfs.xml" />
# 正在完成中