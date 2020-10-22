# wgs-extend-lock

分布式锁依赖jar

引用当前jar在项目中，使用zk锁：
* 1、配置环境变量：zk锁根目录**wgs.extend.lock.path**，默认值**/distributed-lock/**，配置值以/结尾
* 2、配置环境变量：zk地址**zookeeper.registry.address**
* 3、配置环境变量：扫描路径**com.wgs.extend.lock.zk**


