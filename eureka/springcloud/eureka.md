##功能
心跳检测，健康检查，负载均衡

##步骤
客户端 
    eureka discovery client
    @EnableDiscoveryClient
服务端 
    eureka server
    @EnableEurekaServer

##端口号
三台eureka互相注册
    在各自的配置文件记录另外两台的地址
客户端注册到服务器
    将自己注册到三台eureka上

##拆分微服务
不适合微服务  
    有很多事务
    访问压力不大，可用性不大
康威定律
    沟通模式决定业务
    
    
##扩展立方模型
    水平复制
    数据分区
    功能解耦
        单一职责，松耦合，高内聚
##
   先考虑功能，再到数据 
        
    
    


    
      
```java
    

