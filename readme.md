# Spring Cloud

## 一、常用注解

* `@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)`

  * 为Web 测试环境的端口为随机端口的配置

* `@ConfigurationProperties`

  * 设置属性的信息 如`prefix`就是前缀

* `@PathVariable`

  * 可以获取`RESTful`风格的`Uri`路径上的参数。

* `@EnableEurekaServer`

  * 开启Eureka Server

* `@EnableEurekaClient`

  * 开启Eureka Client

* `@EnableDiscoveryClient`
  * 开启客户端注册
  > spring cloud中discovery service有许多种实现（eureka、consul、zookeeper等等），
  > `@EnableDiscoveryClient`基于`spring-cloud-commons`, 
  > `@EnableEurekaClient`基于`spring-cloud-netflix`。
  > 其实用更简单的话来说，就是如果选用的注册中心是`eureka`，那么就推荐`@EnableEurekaClient`，
  > 如果是其他的注册中心，那么推荐使用`@EnableDiscoveryClient`。

* `@Import(EurekaServerInitializerConfiguration.class)`

  * 将标记的class注入到spring IOC容器中

  * 只能注解在类上，以及唯一的参数value上可以配置3种类型的值Configuration，ImportSelector，ImportBeanDefinitionRegistrar

    * **基于Configuration也就是直接填对应的class数组**

      ```java 
      @Import({Square.class,Circular.class})
      ```

    * **基于自定义ImportSelector的使用**

      ```java
      /**
       * 定义一个我自己的ImportSelector
       *
       * @author zhangqh
       * @date 2018年5月1日
       */
      public class MyImportSelector implements  ImportSelector{
          public String[] selectImports(AnnotationMetadata importingClassMetadata) {
              return new String[]{"com.zhang.bean.Triangle"};
          }
      }
      
      // 使用@Import
      @Import({Square.class,Circular.class,MyImportSelector.class})
      ```

    * **基于ImportBeanDefinitionRegistrar的使用**

    ```java
    /**
     * 定义一个自定的ImportBeanDefinitionRegistrar
     *
     * @author zhangqh
     * @date 2018年5月1日
     */
    public class MyImportBeanDefinitionRegistrar  implements ImportBeanDefinitionRegistrar{
        public void registerBeanDefinitions(
                AnnotationMetadata importingClassMetadata,
                BeanDefinitionRegistry registry) {
            // new一个RootBeanDefinition
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Rectangle.class);
            // 注册一个名字叫rectangle的bean
            registry.registerBeanDefinition("rectangle", rootBeanDefinition);
        }
    }
    
    // 使用@Import
    @Import({Square.class,Circular.class,MyImportSelector.class,MyImportBeanDefinitionRegistrar.class})
    ```

    
## 二、坑点

### 1、`eureka client` 使用如下配置时，启动后会自动关闭

在spring cloud `Finchley.RELEASE`、`Greenwich.RELEASE`都有问题

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```
必须添加如下的引用，才不会启动后关闭
```xml
 <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```





## 三、Eureka

### 3.1 Eureka概念

#### （1）Register-服务注册

​	当Eureka Client向Eureka Server注册时，Eureka Client 提供自身的元数据，比如 IP 地址、端口、运行状况H1标的 Uri 主页地址等信息。

#### （2）Renew-服务续约

​	Eureka CLIent 在默认的情况下会每隔 30 秒发送一次心跳来进行服务续约。通过服务续约来告知Eureka Server该Eureka Client 仍然可用，没有出现故障。正常情况下，如果Eureka Server在90 秒内没有收到 ureka Client 的心跳， Eureka Server 会将 Eureka Client 实例从注册列表中删除。注意：官网建议不要更改服务续约的间隔时间。

#### （3）Fetch Registries-获取服务注册列表信息

​	Eureka Client从Eureka Server 获取服务注册表信息，井将其缓存在本地。Eureka Client会
使用服务注册列表信息查找其他服务的信息，从而进行远程调用。该注册列表信息定时（每30 秒） 更新一次，每次返回注册列表信息可能与 Eureka Client 的缓存信息不同，Eureka Client会自己处理这些信息。如果由于某种原因导致注册列表信息不能及时匹配，Eureka Client 会重新获取整个注册表信息。 Eureka Server 缓存了所有的服务注册列表信息，并将整个注册列表以及每个应用程序 信息进行了压缩，压缩内容和没有压缩的内容完全相同。 Eureka Client和Eureka Server 可以使用 JSON和XML 数据格式进行通信。在默认的情况下， Eureka Client使用JSON 格式的方式来获取服务注册列表的信息。

####  （4）Cancel-服务下线

​	Eureka Client 在程序关闭时可以向 Eureka Server 发送下线请求。发送请求后，该客户端的
实例信息将从Eureka Server 的服务注册列表中删除。该下线请求不会自动完成，需要在程序关闭时调用以下代码：

```java
DiscoveryManager.getinstance().shutdownComponent();
```

#### （5）Eviction-服务剔除

在默认情况下，当 Eureka Client 90 秒没有向 Eureka Server 发送服务续约（即心跳〉时，Eureka Server 会将该服务实例从服务注册列表删除，即服务剔除。

### 3.2 Eureka 架构

![Eureka高可用架构](https://github.com/Netflix/eureka/raw/master/images/eureka_architecture.png)

​	在这个架构中有两个角色 ，即 Eureka Server和Eureka Client。而Eureka Client 又分为Applicaton Service和Application Client 即服务提供者和服务消费者。每个区域有一个Eureka 集群，并且每个区域至少有一个 Eureka Server 可以处理区域故障，以防服务器瘫痪。

​	Eureka Client向Eureka Server注册， 将自己的客户端信息提交给 Eureka Server。然后，Eureka Client通过向 Eureka Server 发送心跳（每 30 次）来续约服务。如果某个客户端不能持续续约，那Eureka Server 判定该客户端不可用，该不可用的客户端将在大约 90 秒后从Eureka Server服务注册列表中删除。服务注册列表信息和服务续约信息会被复制到集群中的每个Eureka Server节点。来自任何区域的Eureka Client 都可以获取整个系统的服务注册列表信息，根据这些注 列表信息，Application Client 可以远程调用Applicaton Service 来消费服务。

### 3.3 服务注册

#### 3.3.1 Eureka Client

​	服务注册，即Eureka Client向Eureka Server 提交自己的服务信息，包括 IP 地址、端口、Serviceld 等信息。如果 Eureka Client 置文件中没有配置 Serviceld ，则默认为配置文件中配置的服务名 ，即`$ {spring application.name ｝`的值。

​	当Eureka Client 启动时，会将自身 的服务信息发送到 Eureka Server 这个过程其实非常简单，现在来从源码的角度分析服务注册的过程。在Maven的依赖包下，找到`eureka-client-1.9.8.jar`包。在`com.netflix.discovery`包下有`DiscoveryClient`类，该类包含了
Eureka Client向Eureka Server注册和续约的相关方法。其中，`DiscoveryClient`实现了`EurekaClient`
并且它是单例模式(`@Singleton`)，而 `EurekaClient`继承了`LookupService`接口之间的关系:

![1554910343477](C:\Users\chensj\AppData\Roaming\Typora\typora-user-images\1554910343477.png)

在`DiscoveryClient`类中有 个服务注册的方法`register()`， 该方法Http请求`Eureka Server`注册，代码如下：

```java
boolean register() throws Throwable {
        logger.info(PREFIX + "{}: registering service...", appPathIdentifier);
        EurekaHttpResponse<Void> httpResponse;
        try {
            httpResponse = eurekaTransport.registrationClient.register(instanceInfo);
        } catch (Exception e) {
            logger.warn(PREFIX + "{} - registration failed {}", appPathIdentifier, e.getMessage(), e);
            throw e;
        }
        if (logger.isInfoEnabled()) {
            logger.info(PREFIX + "{} - registration status: {}", appPathIdentifier, httpResponse.getStatusCode());
        }
        return httpResponse.getStatusCode() == Status.NO_CONTENT.getStatusCode();
    }

```

这个方法的调用则是来至于`InstanceInfoReplicator`,该类实现了`Runnable`接口，在`run()`方法中调用了`DiscoveryClient.register()`

```java
  @Override
    public void run() {
        try {
            // 刷新 应用实例信息
            discoveryClient.refreshInstanceInfo();
            // 判断 应用实例信息 是否数据不一致
            Long dirtyTimestamp = instanceInfo.isDirtyWithTime();
            if (dirtyTimestamp != null) {
                // TODO 发起注册
                discoveryClient.register();
                // 设置 应用实例信息 数据一致
                instanceInfo.unsetIsDirty(dirtyTimestamp);
            }
        } catch (Throwable t) {
            logger.warn("There was a problem with the instance info replicator", t);
        } finally {
            // 提交任务，并设置该任务的 Future
            Future next = scheduler.schedule(this, replicationIntervalSeconds, TimeUnit.SECONDS);
            scheduledPeriodicRef.set(next);
        }
    }
```

而`InstancelnfoReplicator`类是在`DiscoveryClient`初始化过程中使用的， 其中有一个`initScheduledTasks()` 方法，该方法主要开启了 取服务注册列表的信息。如果需要向Eureka Server注册，则开启注册，同时开启定时任务Eureka Server服务续约。

```java
private void initScheduledTasks() {
        // 获取服务列表，任务调度获取注册列表代码
        if (clientConfig.shouldFetchRegistry()) {
            // registry cache refresh timer
            int registryFetchIntervalSeconds = clientConfig.getRegistryFetchIntervalSeconds();
            int expBackOffBound = clientConfig.getCacheRefreshExecutorExponentialBackOffBound();
            scheduler.schedule(
                    new TimedSupervisorTask(
                            "cacheRefresh",
                            scheduler,
                            cacheRefreshExecutor,
                            registryFetchIntervalSeconds,
                            TimeUnit.SECONDS,
                            expBackOffBound,
                            new CacheRefreshThread()
                    ),
                    registryFetchIntervalSeconds, TimeUnit.SECONDS);
        }
		// 向Eureka Server注册服务
        if (clientConfig.shouldRegisterWithEureka()) {
            int renewalIntervalInSecs = instanceInfo.getLeaseInfo().getRenewalIntervalInSecs();
            int expBackOffBound = clientConfig.getHeartbeatExecutorExponentialBackOffBound();
            logger.info("Starting heartbeat executor: " + "renew interval is: {}", renewalIntervalInSecs);

            // Heartbeat timer
            // 心跳检测，并续约
            scheduler.schedule(
                    new TimedSupervisorTask(
                            "heartbeat",
                            scheduler,
                            heartbeatExecutor,
                            renewalIntervalInSecs,
                            TimeUnit.SECONDS,
                            expBackOffBound,
                            new HeartbeatThread()
                    ),
                    renewalIntervalInSecs, TimeUnit.SECONDS);

            // InstanceInfo replicator
            instanceInfoReplicator = new InstanceInfoReplicator(
                    this,
                    instanceInfo,
                    clientConfig.getInstanceInfoReplicationIntervalSeconds(),
                    2); // burstSize

            statusChangeListener = new ApplicationInfoManager.StatusChangeListener() {
                @Override
                public String getId() {
                    return "statusChangeListener";
                }

                @Override
                public void notify(StatusChangeEvent statusChangeEvent) {
                    if (InstanceStatus.DOWN == statusChangeEvent.getStatus() ||
                            InstanceStatus.DOWN == statusChangeEvent.getPreviousStatus()) {
                        // log at warn level if DOWN was involved
                        logger.warn("Saw local status change event {}", statusChangeEvent);
                    } else {
                        logger.info("Saw local status change event {}", statusChangeEvent);
                    }
                    // 更新客户端信息，并发送到服务端
                    instanceInfoReplicator.onDemandUpdate();
                }
            };

            if (clientConfig.shouldOnDemandUpdateStatusChange()) {
                applicationInfoManager.registerStatusChangeListener(statusChangeListener);
            }

           
 // 开启 应用实例信息复制器 TODO 并在指定延迟时间后执行一次注册
            instanceInfoReplicator.start(clientConfig.getInitialInstanceInfoReplicationIntervalSeconds());
        } else {
            logger.info("Not registering with Eureka server per configuration");
        }
    }
```

#### 3.3.2 Eureka Server

​		再来跟踪 Eureka server端的代码，在Maven的 org.springframework.cloud:spring-cloud-netflix-eureka-server:2.1.0.RELEASE包下，在org.springframework.cloud.netflix.eureka.server会发现有一 个`EurekaServerBootstrap`的类，将监听`ServletContextEvent`事件，所以` EurekaServerBootstrap`类在程序启动时具有最先初始化的权利，初始化的时候将会调用如下方法：

```java
public void contextInitialized(ServletContextEvent event) {
        try {
            // 初始化 Eureka-Server 配置环境
            initEurekaEnvironment();

            // 初始化 Eureka-Server 上下文
            initEurekaServerContext();

            ServletContext sc = event.getServletContext();
            sc.setAttribute(EurekaServerContext.class.getName(), serverContext);
        } catch (Throwable e) {
            logger.error("Cannot bootstrap eureka server :", e);
            throw new RuntimeException("Cannot bootstrap eureka server :", e);
        }
    }
```

​		在`initEurekaServerContext()`中，会有创建`PeerAwareInstanceRegistry`(应用实例信息的注册表)实例，这个类就是保持客户端注册信息的地方，同时也会创建`PeerEurekaNodes`实例，保存注册中心集群的地方，用于后续将注册信息推送到同等节点时候使用。

```java
 // 【2.2.5】创建 应用实例信息的注册表
        PeerAwareInstanceRegistry registry;
        // AWS 相关，跳过
        if (isAws(applicationInfoManager.getInfo())) {
            registry = new AwsInstanceRegistry(
                    eurekaServerConfig,
                    eurekaClient.getEurekaClientConfig(),
                    serverCodecs,
                    eurekaClient
            );
            awsBinder = new AwsBinderDelegate(eurekaServerConfig, eurekaClient.getEurekaClientConfig(), registry, applicationInfoManager);
            awsBinder.start();
        } else {
            registry = new PeerAwareInstanceRegistryImpl(
                    eurekaServerConfig,
                    eurekaClient.getEurekaClientConfig(),
                    serverCodecs,
                    eurekaClient
            );
        }

        // 【2.2.6】创建 Eureka-Server 集群节点集合
        PeerEurekaNodes peerEurekaNodes = getPeerEurekaNodes(
                registry,
                eurekaServerConfig,
                eurekaClient.getEurekaClientConfig(),
                serverCodecs,
                applicationInfoManager
        );
```

​		通过代码跟踪发现，执行注册的是`com.netflix.eureka.registry.PeerAwareInstanceRegistryImpl#register`,代码如下：

```java
 public void register(final InstanceInfo info, final boolean isReplication) {
        // 租约过期时间
        int leaseDuration = Lease.DEFAULT_DURATION_IN_SECS;
        if (info.getLeaseInfo() != null && info.getLeaseInfo().getDurationInSecs() > 0) {
            leaseDuration = info.getLeaseInfo().getDurationInSecs();
        }
        // 注册应用实例信息
        super.register(info, leaseDuration, isReplication);
        // Eureka-Server peer node 复制
        replicateToPeers(Action.Register, info.getAppName(), info.getId(), info, null, isReplication);
    }
```

​		这里会调用父类`register()`，也就是`com.netflix.eureka.registry.AbstractInstanceRegistry#register`，这个时候就可以知道注册列表信息是保存在一个Map中，在完成client注册后，再讲client推送到各个同等节点中去`replicateToPeers`。

​		使用方法调用方查看，会将代码追踪到`com.netflix.eureka.resources.ApplicationResource#addInstance`,这个接口是一个处理Http协议的Post请求方法，这里面会调用`PeerAwareInstanceRegistryImpl#register`

```java
 @POST
    @Consumes({"application/json", "application/xml"})
    public Response addInstance(InstanceInfo info,
                                @HeaderParam(PeerEurekaNode.HEADER_REPLICATION) String isReplication) {
        // 校验参数是否合法
        logger.debug("Registering instance {} (replication={})", info.getId(), isReplication);
        // validate that the instanceinfo contains all the necessary required fields
        if (isBlank(info.getId())) {
            return Response.status(400).entity("Missing instanceId").build();
        } else if (isBlank(info.getHostName())) {
            return Response.status(400).entity("Missing hostname").build();
        } else if (isBlank(info.getIPAddr())) {
            return Response.status(400).entity("Missing ip address").build();
        } else if (isBlank(info.getAppName())) {
            return Response.status(400).entity("Missing appName").build();
        } else if (!appName.equals(info.getAppName())) {
            return Response.status(400).entity("Mismatched appName, expecting " + appName + " but was " + info.getAppName()).build();
        } else if (info.getDataCenterInfo() == null) {
            return Response.status(400).entity("Missing dataCenterInfo").build();
        } else if (info.getDataCenterInfo().getName() == null) {
            return Response.status(400).entity("Missing dataCenterInfo Name").build();
        }

        // AWS 相关，跳过
        // handle cases where clients may be registering with bad DataCenterInfo with missing data
        DataCenterInfo dataCenterInfo = info.getDataCenterInfo();
        if (dataCenterInfo instanceof UniqueIdentifier) {
            String dataCenterInfoId = ((UniqueIdentifier) dataCenterInfo).getId();
            if (isBlank(dataCenterInfoId)) {
                boolean experimental = "true".equalsIgnoreCase(serverConfig.getExperimental("registration.validation.dataCenterInfoId"));
                if (experimental) {
                    String entity = "DataCenterInfo of type " + dataCenterInfo.getClass() + " must contain a valid id";
                    return Response.status(400).entity(entity).build();
                } else if (dataCenterInfo instanceof AmazonInfo) {
                    AmazonInfo amazonInfo = (AmazonInfo) dataCenterInfo;
                    String effectiveId = amazonInfo.get(AmazonInfo.MetaDataKey.instanceId);
                    if (effectiveId == null) {
                        amazonInfo.getMetadata().put(AmazonInfo.MetaDataKey.instanceId.getName(), info.getId());
                    }
                } else {
                    logger.warn("Registering DataCenterInfo of type {} without an appropriate id", dataCenterInfo.getClass());
                }
            }
        }

        // 注册应用实例信息 @see com.netflix.eureka.registry.PeerAwareInstanceRegistryImpl.register
        registry.register(info, "true".equals(isReplication));

        // 返回 204 成功
        // 204 to be backwards compatible
        return Response.status(204).build();
    }
```

### 3.4 服务续约

#### 3.4.1 Eureka Client

同样是在在`com.netflix.discovery`包下有`DiscoveryClient`类中，查看`DiscoveryClient#renew`方法

```java
 boolean renew() {
        EurekaHttpResponse<InstanceInfo> httpResponse;
        try {
            httpResponse = eurekaTransport.registrationClient.sendHeartBeat(instanceInfo.getAppName(), instanceInfo.getId(), instanceInfo, null);
            logger.debug("{} - Heartbeat status: {}", PREFIX + appPathIdentifier, httpResponse.getStatusCode());
            if (httpResponse.getStatusCode() == 404) {
                REREGISTER_COUNTER.increment();
                logger.info("{} - Re-registering apps/{}", PREFIX + appPathIdentifier, instanceInfo.getAppName());
                long timestamp = instanceInfo.setIsDirtyWithTime();
                // 发起注册
                boolean success = register();
                if (success) {
                    instanceInfo.unsetIsDirty(timestamp);
                }
                return success;
            }
            return httpResponse.getStatusCode() == 200;
        } catch (Throwable e) {
            logger.error("{} - was unable to send heartbeat!", PREFIX + appPathIdentifier, e);
            return false;
        }
    }
```

查看这个方法的调用，来至于`com.netflix.discovery.DiscoveryClient.HeartbeatThread`的`run`方法，这个是在DiscoveryClient初始化(initScheduledTasks)的时候使用的，用于创建心跳检测使用的定时器的时候，会启动这个线程，就会按照时间来启动任务：

```java
// TODO 心跳检测，并续约
scheduler.schedule(
    //监督任务，在执行超时时调度子任务
    new TimedSupervisorTask(
        "heartbeat",
        scheduler,
        heartbeatExecutor,
        renewalIntervalInSecs,
        TimeUnit.SECONDS,
        expBackOffBound,
        new HeartbeatThread()
    ),
    renewalIntervalInSecs, TimeUnit.SECONDS);

// HeartbeatThread.class
private class HeartbeatThread implements Runnable {
    public void run() {
        // 续约
        if (renew()) {
            lastSuccessfulHeartbeatTimestamp = System.currentTimeMillis();
        }
    }
}

```

#### 3.4.2 Eureka Server

​	同样是在`com.netflix.eureka.registry.PeerAwareInstanceRegistryImpl`中，方法为`renew`

```java
 public boolean renew(final String appName, final String id, final boolean isReplication) {
     // 续租
     if (super.renew(appName, id, isReplication)) {
         // Eureka-Server 复制
         replicateToPeers(Action.Heartbeat, appName, id, null, null, isReplication);
         return true;
     }
     return false;
 }
```

继续向上跟踪方法调用方法，就会到`com.netflix.eureka.resources.InstanceResource#renewLease`,这是一个处理http协议的put请求，用于接收客户端发送的put请求

```java
 @PUT
    public Response renewLease(
            @HeaderParam(PeerEurekaNode.HEADER_REPLICATION) String isReplication,
            @QueryParam("overriddenstatus") String overriddenStatus,
            @QueryParam("status") String status,
            @QueryParam("lastDirtyTimestamp") String lastDirtyTimestamp) {
        boolean isFromReplicaNode = "true".equals(isReplication);
        // 续租
        boolean isSuccess = registry.renew(app.getName(), id, isFromReplicaNode);

        // 续租失败
        // Not found in the registry, immediately ask for a register
        if (!isSuccess) {
            logger.warn("Not Found (Renew): {} - {}", app.getName(), id);
            return Response.status(Status.NOT_FOUND).build();
        }

        // 比较 InstanceInfo 的 lastDirtyTimestamp 属性
        // Check if we need to sync based on dirty time stamp, the client
        // instance might have changed some value
        Response response = null;
        if (lastDirtyTimestamp != null && serverConfig.shouldSyncWhenTimestampDiffers()) {
            response = this.validateDirtyTimestamp(Long.valueOf(lastDirtyTimestamp), isFromReplicaNode);
            // Store the overridden status since the validation found out the node that replicates wins
            if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()
                    && (overriddenStatus != null)
                    && !(InstanceStatus.UNKNOWN.name().equals(overriddenStatus))
                    && isFromReplicaNode) {
                registry.storeOverriddenStatusIfRequired(app.getAppName(), id, InstanceStatus.valueOf(overriddenStatus));
            }
        } else { // 成功
            response = Response.ok().build();
        }
        logger.debug("Found (Renew): {} - {}; reply status={}" + app.getName(), id, response.getStatus());
        return response;
    }
```

### 3.5 续约时间与剔除时间设置

​	续约时间：表示客户端多长时间发送一次请求到服务端，表示当前客户端依然可以使用

```yaml
eureka:
  instance:
    # 续约的时间间隔，默认为30s,不建议修改
    lease-renewal-interval-in-seconds: 30
```

​	剔除时间: 表示服务单多久未接收到客户端发出的续约请求或者心跳检测，服务端将会把这个应用实例从可用实例剔除。

```yaml
eureka:
  instance:
    # 剔除多久未接受到心跳的实例的时间参数，不建议修改
    lease-expiration-duration-in-seconds: 90
```

### 3.6 为什么Eureka Client获取服务实例这么慢

#### 3.6.1 Eureka Client注册延迟

​		从上面注册的代码中可以看出，注册使用的是如下的代码

```java
// 开启 应用实例信息复制器 TODO 并在指定延迟时间后执行一次注册
instanceInfoReplicator.start(clientConfig.getInitialInstanceInfoReplicationIntervalSeconds());
```

`InstanceInfoReplicator`的`start`方法中会传入一个时间参数，促使注册的流程延迟执行

```java
 public void start(int initialDelayMs) {
        if (started.compareAndSet(false, true)) {
            // 设置 应用实例信息 数据不一致
            instanceInfo.setIsDirty();  // for initial register
            // 提交任务，并设置该任务的 Future
            // 延迟initialDelayMs后，运行且仅仅运行一次InstanceInfoReplicator的run()
            Future next = scheduler.schedule(this, initialDelayMs, TimeUnit.SECONDS);
            scheduledPeriodicRef.set(next);
        }
    }

```

而这个时间参数是来自于`com.netflix.discovery.DiscoveryClient`创建的时候，使用的`EurekaClientConfig`中获取的，通过跟踪代码发现，这个方法来自于`com.netflix.discovery.DefaultEurekaClientConfig#getInitialInstanceInfoReplicationIntervalSeconds`，延迟了40s

```java
public int getInitialInstanceInfoReplicationIntervalSeconds() {
        return configInstance.getIntProperty(
                namespace + INITIAL_REGISTRATION_REPLICATION_DELAY_KEY, 40).get();
    }
```

#### 3.6.2 Eureka Server 的响应缓存

​	Eureka Server 维护每 30 更新一次响应缓存，可通过更改配置`eureka.server.response-cache-update-interval-ms`来修改。所以即使是刚刚注册的实例，也不会立即出现在服务注册列表中。

#### 3.6.3  Eureka Client 缓存

​	Eureka Client 保留注册表信息的缓存。该缓存每30秒更新一次(如前所述).因此,Eureka Client刷新本地缓存并发现其他新注册的实例可能需要30 秒。

#### 3.6.4 LoadBalancer 缓存

​	Ribbon的负载平衡器从本地的 Eureka Client 获取服务注册列表信息。 Ribbon本身还维护了缓存，以避免每个请求都需要从 Eureka Client获取服务注册列表。此缓存每30秒刷新一次(可由 ribbon.ServerListRefreshlnterval设置），所以可能至少需要30秒的时间才能使用新注册的实例。
​	综上因素，一个新注册的实例，默认延迟 40 秒向服务注册中心注册，所以不能马上被Eureka Server 发现。另外，刚注册的 Eureka Client 不能立即被其他服务调用，原因是调用方由于各种缓存没有及时获取到最新的服务注册列表信息。

### 3.7 Eureka自我保护

​		当有一个新的 Eureka Server 出现时，它尝试从相邻 Peer节点获取所有服务实例注册表信息。如果从相邻 Peer 点获取信息时出现了故障 Eureka Server 会尝试其他的 Peer 节点。如果Eureka Server能够成功获取所有的服务实例信息，则根据配置信息设置服务续约的阀值。在任何时间，如果 Eureka Server接收到的服务续约低于为该值配置的百分比（默认为 15 分钟内低于 85% ）,则服务器开启自我保护模式，即不再剔除注册列表的信息。
​		这样做的好处在于，如果是 Eureka Server 自身的网络问题而导致Eureka Client无法续约，Eureka Client 注册列表信息不再被删除，也就是Eureka Client 还可以被其他服务消费。
​	在默认情况下， Eureka Server 的自我保护模式是开启的，如果需要关闭，则在配置文件添加以下代码：

```yam
eureka:
  server:
    # 自我保护
    enable-self-preservation: false
```

### 3.8 高可用注册中心

#### 3.8.1 两个集群
```yaml
# peer1 
server:
  # 端口
  port: 8000
eureka:
  instance:
    hostname: peer1
  client:
    # 本身为注册中心，不需要去检索服务信息
    register-with-eureka: false
    # 本身为注册中心，是否需要在注册中心注册，默认true  集群设置为true
    fetch-registry: true
    # 注册地址
    service-url:
      # 默认访问地址
      defaultZone: http://peer2:8001/eureka
spring:
  application:
    name: eureka-server
  devtools:
    restart:
      enabled: true
      additional-paths: src/main/java

```

```yaml
# peer2 
server:
  # 端口
  port: 8000
eureka:
  instance:
    hostname: peer1
  client:
    # 本身为注册中心，不需要去检索服务信息
    register-with-eureka: false
    # 本身为注册中心，是否需要在注册中心注册，默认true  集群设置为true
    fetch-registry: true
    # 注册地址
    service-url:
      # 默认访问地址
      defaultZone: http://peer1:8000/eureka
spring:
  application:
    name: eureka-server
```



#### 3.8.2 多个集群

##### *appliction.yml*

```yml
eureka:
  client:
    service-url:
      # 默认访问地址
      defaultZone: http://peer1:8000/eureka,http://peer2:8001/eureka,http://peer3:8002/eureka
```

##### *application-peerX.yml*

```yaml
server:
  # 端口
  port: 8000
eureka:
  instance:
    hostname: peerX
  client:
    # 本身为注册中心，不需要去检索服务信息
    register-with-eureka: false
    # 本身为注册中心，是否需要在注册中心注册，默认true  集群设置为true
    fetch-registry: true
spring:
  application:
    name: eureka-server-peerX
```

修改上面的`peerX`到指定参数即可

### 四、Ribbon

​	 Spring Cloud Ribbon是一个基于HTTP和TCP的客户端负载均衡工具，它基于Netflix Ribbon实现。通过Spring Cloud的封装，可以让我们轻松地将面向服务的REST模版请求自动转换成客户端负载均衡的服务调用。Spring Cloud Ribbon虽然只是一个工具类框架，它不像服务注册中心、配置中心、API网关那样需要独立部署，但是它几乎存在于每一个Spring Cloud构建的微服务和基础设施中。因为微服务间的调用，API网关的请求转发等内容，实际上都是通过Ribbon来实现的，包括后续的Feign，它也是基于Ribbon实现的工具。所以，对Spring Cloud Ribbon的理解和使用，对于我们使用Spring Cloud来构建微服务非常重要。

### 4.1 RestTemplate

​	`RestTemplate`是`Spring Resources`中一个访问 第三方RESTful API接口的网络请求框架。RestTemplate 的设计原则和其他Spring Template（例如 JdbcTemplate、JmsTemplate ）类似，都是为执行复杂任务提供了具有默认行为的简单方法。
​	`RestTemplate`是用来消费`REST`服务的，所以`RestTemplate`主要方法都与REST的HTTP协议的一些方法紧密相连，例如HEAD、GET、POST、PUT、DELETE、OPTIONS 等方法，这些方法在`RestTemplate`类对应的方法为 `headForHeaders()`、 `getForObject()`、`postForObject()`、`put()`和`delete()`等。



