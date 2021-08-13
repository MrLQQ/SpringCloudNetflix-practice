微服务架构4个核心问题：

1. 服务这么多，客户端该怎么访问？
2. 这么多服务，服务之间如何通信？
3. 这么多服务，如何治理？
4. 服务器挂了怎么办？




解决方案：

> Spring Cloud 生态！

1. Spring Cloud Netflix 一站式解决方案

   API网关，zuul组件

   Feign `-基于->` Http Client `-基于->` Http 通信方式，同步，阻塞

   服务注册与发现：Eureka

   熔断机制：Hystrix

   ……

   

2. Apache Dubbo Zookeeper 半自动，需要整合别人的

   API：没有，找第三方组件，或者自己实现

   Dubbo：通信

   Zookeeper：服务注册与发现

   没有熔断机制，借助Hystrix	 

   

3. Spring Cloud Alibaba 一站式解决方案，更简单



新概念：服务网格->Server Mesh

​			istio



# 1、常见面试题

1.1、什么是微服务?

1.2、微服务之间如何独立通信的？

1.3、Spring Cloud和Dubbo有哪些区别？

1.4、Spring Boot和Spring Cloud，请你谈谈对他们的理解

1.5、什么是服务熔断？什么是服务降级

1.6、微服务的优缺点分别是什么？说下你在项目开发中遇到的坑

1.7、你所知道的微服务技术栈有哪些？请列举一二

1.8、eureka和zookeeper都可以提供服务注册与发现的哪些功能?



# 2、微服务概述

## 2.1、什么是微服务

微服务（Microservice Architecture）是近几年流行的一种建构思想，关于他的概念很难一言以蔽之。

究竟什么是微服务呢？我们再次引用ThoughtWorks公司的首席科学家Martion Fowler于2014年提出的一段话：

​		原文：[https://martinfowler.com/articles/microservices.html](https://martinfowler.com/articles/microservices.html)

​		汉化：[https://blog.csdn.net/u013970991/article/details/53333921](https://blog.csdn.net/u013970991/article/details/53333921)

* 就目前而言，对于微服务，业界并没有一个统一的，标准的定义

* 但通常而言，微服务是一种架构模式，或者说是一种架构风格，**它提倡将单一的应用程序划分成一组小的服务**，每个服务运行在其独立的自己的进程内，服务之间相互协调，相互配置，为用户提供最终价值。服务之间采用轻量级的通信机制互相沟通，每个服务都围绕着具体的业务进行构建，并且额能够被独立的部署到生产环境中，另外，应尽量避免统一的，集中式的服务管理机制，对具体的一个服务而言，应根据业务上下文，选择合理的语言，工具对其进行构建，可以有一个非常轻量级的集中式管理来协调这些服务，可以使用不同的语言来编写服务，也可以使用不同的数据存储；



**可能有的人觉得官方的话太过生涩，我们从技术维度来理解**

* 微服务化的核心就是将传统的一站式应用，根据业务拆分成一个一个的服务，彻底地去解耦，每一个微服务提供单个业务功能的服务，一个服务做一件事情，从技术角度看就是一种小而独立的处理过程，类似进程的概念，能够自行单独启动或者销毁，拥有自己独立的数据库。



 ## 2.2、微服务于微服务架构

**微服务**

强调的是服务的大小，他关注的是某一个点，是具体解决某一个问题/提供落地对应服务的一个服务应用，狭义的看，可以看作是IDEA中一个个微服务工程，或者Module！

```
IDEA工具里卖弄使用Maven开发的一个个独立的小Module，他具体是使用SpringBoot开发的一个小模块，专业的事情交给专业的模块来做，一个模块就做着一件事情
强调的是一个个的个体，每个个体完成一个具体的任务或者功能
```



**微服务架构**

一种新的架构形式，Martin Fowler,2014年提出



微服务架构是一种架构模式，它提倡将单一应用程序划分为一组小的服务，服务之间互相协调，五项配合，为用户提供最终价值。每个服务运行在其独立的进程中，服务于服务间采用轻量级的通信机制互相协作，每个微服务都围绕着具体的业务进行构建，并且能够被独立的部署到生产环境中，另外，应尽量避免统一的，集中式的服务管理机制，对具体的一个服务而言，应根据业务上下文，选择合适的语言，工具对其进行构建。



## 2.3、微服务优缺点

**优点**

* 每个服务足够内聚，足够小，代码容易理解，这样能聚焦一个指定的业务功能或业务需求；
* 开发简单，开发效率提高，一个服务可能就是专一的只干一件事；
* 微服务能够被小团队独立开发，这个小团队是2~5人的开发人员组成；
* 微服务是松耦合的，是有功能意义的服务，无论是在开发阶段或部署阶段都是独立的。
* 微服务能使用不同的语言开发。
* 易于和第三方集成，微服务允许容易且灵活的方式集成自动部署，通过持续集成工具，如jenkins，Hudson，bamboo
* 微服务易于被一个开发人员理解，修改和维护，这样小团队能够更关注自己的工作成果。无需通过合作才能体现价值。
* 微服务允许你利用融合最新技术
* **微服务只是业务逻辑的代码，不会和HTML，CSS或其他界面混合**
* **每个微服务都有自己的存储能力，可以有自己的数据库，也可以有同意数据库**



**缺点**

* 开发人员要处理分布式系统的服务性
* 多服务运维难度，随着服务的增加，运维的压力也在增大
* 系统部署依赖
* 服务间通信成本
* 数据一致性
* 系统集成测试
* 性能监控



## 2.4、微服务技术栈有哪些？

| 微服务条目                               | 落地技术                                                     |
| ---------------------------------------- | ------------------------------------------------------------ |
| 微服务开发                               | Spring Boot，Spring，Spring MVC                              |
| 服务配置与管理                           | Netflix公司的Archaius、阿里的Diamond等                       |
| 服务注册与发现                           | Eureka、Consul、Zookeeper等                                  |
| 服务调用                                 | Rest、RPC、gRPC                                              |
| 服务熔断器                               | Hystrix、Envoy等                                             |
| 负载均衡                                 | Ribbon、Nginx等                                              |
| 服务接口调用（客户端调用服务的简化工具） | Feign等                                                      |
| 消息队列                                 | Kafka、RabbitMQ、ActiveMQ等                                  |
| 服务配置中心管理                         | Spring Cloud Config、Chef等                                  |
| 服务路由（API网关）                      | Zuul等                                                       |
| 服务监控                                 | Zabbix、Nagios、Metrics、Specataor等                         |
| 全链路追踪                               | Zipkin、Brave、Dapper等                                      |
| 服务部署                                 | Docker、OpenStack、Kubernetes                                |
| 数据流操作开发包                         | Spring Cloud Stream(封装与Redis，Rabbit，Kafka等发送接收消息) |
| 事件消息总线                             | Spring Cloud Bus                                             |



## 2.5、为什么选择Spring Cloud作为微服务架构

#### 1、选择依据

* 整体解决方案和框架成熟度
* 社区热度
* 可维护性
* 学习曲线

#### 2、当前各大IT公司用的微服务架构有哪些？

* 阿里：Dubbo+HFS
* 京东：JSF
* 新浪：Motan
* 当当网：Dubbox
* ……

#### 3、各微服务框架对比

| 功能点/服务框架 | Netflix/Spring Cloud                                         | Motan                                                        | gRPC                      | Thrift   | Dubbo/Dubbox                        |
| --------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------- | -------- | ----------------------------------- |
| 功能定位        | 完整的微服务架构                                             | RPC架构，但整合了ZK或Consul，实现集群环境的基本服务注册/发现 | RPC框架                   | RPC框架  | 服务框架                            |
| 支持Rest        | 是，Ribbon支持多种可插拔的序列化选择                         | 否                                                           | 否                        | 否       | 否                                  |
| 支持RPC         | 否                                                           | 是（Hession2）                                               | 是                        | 是       | 是                                  |
| 支持多语言      | 是（Rest形式）                                               | 否                                                           | 是                        | 是       | 否                                  |
| 负载均衡        | 是（服务端zuul+客户端Ribbon），zuul-服务，动态路由，云端负载均衡Eureka（战队中间层服务器） | 是（客户端）                                                 | 否                        | 否       | 是（客户端）                        |
| 配置服务        | Netfix Archaius，Spring Cloud Config Server集中配置          | 是（zookeeper提供）                                          | 否                        | 否       | 否                                  |
| 服务调用链监控  | 是（zuul），zuul提供边缘服务，API网关                        | 否                                                           | 否                        | 否       | 否                                  |
| 高可用/容错     | 是（服务端Hystrix+客户端Ribbon）                             | 是（客户端）                                                 | 否                        | 否       | 是（客户端）                        |
| 典型应用案例    | Netflix                                                      | Sina                                                         | Google                    | Facebook |                                     |
| 社区活跃程度    | 高                                                           | 一般                                                         | 高                        | 一般     | 2017年后重新开始维护，之前中断了5年 |
| 学习难度        | 中                                                           | 底                                                           | 高                        | 高       | 底                                  |
| 文档丰富程度    | 高                                                           | 一般                                                         | 一般                      | 一般     | 高                                  |
| 其他            | Spring Cloud Bus为我们的应用程序带来的了更多管理端点         | 支持降级                                                     | Netflix内部在开发集成gRPC | IDL定义  | 实践的公司比较多                    |



# 3、Spring Cloud入门概述

## 3.1、 Spring Cloud是什么

Spring官网：[https://spring.io/](https://spring.io/)

Spring Cloud，基于Spring Boot提供了一套微服务解决方案，包括服务注册与发现，配置中心，全链路监控，服务网关，负载均衡，熔断器等组件，除了基于NetFlix的开源组件做高度抽象封装之外，还有一些选型中立的开源组件。

Spring Cloud利用Spring Boot的开发便利性，巧妙地简化了分布式系统基础设施的开发，Spring Cloud为开发人员提供了快速构建分布式系统的一些工具，**包括配置管理，服务发现，断路器，路由，微代理，事件总线，全局锁，决策竞选，分布式会话等等**，他们都可以用Spring Boot的开发风格做到一键启动和部署。

Spring Boot并没有重复造轮子，他只是将目前各家公司开发的比较成熟，经得起实际考验的服务框架组合起来，通过Spring Boot风格进行再封装，屏蔽掉了复杂的配置和实现原理，**最终给开发者留出了一套简单易懂，已部署和易维护的分布式系统开发工具包**。

Spring Cloud是分布式微服务框架下的一站式解决方案，是各个微服务框架落地技术的集合体，俗称微服务全家桶。



## 3.2、Spring Cloud和Spring Boot关系

* Spring Boot专注于快速方便的开发单个个体微服务。 -jar
* Spring Cloud是关注全局的微服务协调整理治理框架，他将Spring Boot开发的一个个单体微服务整合并管理起来，为各个微服务之间提供：配置管理，服务发现，断路器，路由，微代理，事件总线，全局锁，决策竞选，分布式会话等等集成服务。
* Spring Boot可以离开Spring Cloud独立使用，开发项目，但是Spring Cloud离不开Spring Boot，属于依赖关系
* **Spring Boot专注于快速、方便的开发单个个体微服务，Spring Cloud关注全局服务治理框架**



## 3.3、Dubbo和Spring Cloud技术选型

### 1、分布式+服务治理Dubbo

目前成熟的互联网框架：应用服务化拆分+消息中间件



### 2、Dubbo和Spring Cloud对比

可以看一下社区活跃度

[Eco System For Apache Dubbo · GitHub](https://github.com/dubbo)

[Spring Cloud · GitHub](https://github.com/spring-cloud)

**结果：**

|              | Dubbo         | Spring                       |
| ------------ | ------------- | ---------------------------- |
| 服务注册中心 | Zookeeper     | Spring Cloud Netflix Eureka  |
| 服务调用方式 | RPC           | REST API                     |
| 服务监控     | Dubbo-monitor | Spring Boot Admin            |
| 断路器       | 不完善        | Spring Cloud Netflix Hystrix |
| 服务网关     | 无            | Spring Cloud Netflix Zuul    |
| 分布式配置   | 无            | Spring Cloud Config          |
| 服务跟踪     | 无            | Spring Cloud Sleuth          |
| 消息总线     | 无            | Spring Cloud Bus             |
| 数据流       | 无            | Spring Cloud Stream          |
| 批量任务     | 无            | Spring Cloud Task            |

**最大区别：Spring Cloud抛弃了Dubbo的RPC通道，采用的是基于HTTP的REST方式。**

严格来说，这两种方式各有优劣。虽然从一定程度上来说，后者牺牲了服务调用的性能，但也避免了上面提到的原生RPC带来的问题。而且REST相比RPC更为灵活，服务提供方和调用方的依赖只依靠一纸契约，不存在代码级别的强依赖，这在强调快速演化的微服务环境下，显得更加合适。

**品牌机与组装机的区别**

很明显，Spring Cloud的功能比Dubbo更加强大，涵盖面更广，而且作为Spring的牵头项目，它也能够与Spring Framework、Spring Boot、Spring Data、Spring Batch等其他Spring项目完美融合，这些对于微服务而言是至关重要的。使用Dubbo构建的微服务架构就像组装电脑，各环节我们的选择自由度很高，但是最终结果有可能因为一条内存质量不能就点不亮了，总是让人不怎么放心，但是如果你是一名高手，按这些都是不是问题；而Spring Cloud就像品牌机，在Spring Source的整合下，做了大量的兼容性测试，保证了机器拥有更高的稳定性，但是如果要在使用非原装组件外的东西，就需要对其基础有足够的了解。

**社区支持与更新力度**

最为重要的是，Dubbo停止了5左右的更新，虽然2017.7重启了。对于技术发展的新需求，需要有开发者自行拓展升级（比如当当网弄出来DubboX），这对于很多想要采用微服务架构的中小软件组织，显然是不太合适的，中小公司没有这么强大的技术能力去修改Dubbo源码+周边的一整套解决方案，并不是每一个公司都有阿里的大牛+真是的线上生产环境测试过。

**总结：**

曾风靡国内的开源RPC服务框架Dubbo在重启维护后，令许多用户为之雀跃， 但同时，也迎来了一些之一的声音。互联网技术发展迅速，Dubbo是否还能跟上时代？Dubbo与Spring Cloud相比又有何优势和差异？是否会有相关举措保护Dubbo的后续更新频率？

人物：Dubbo重启维护开发的刘军，主要负责责任人之一

刘军，阿里巴巴中间件高级研发工程师，主导了Dubbo重启维护以后的几个发行版计划，专注于高性能RPC框架和微服务相关领域。曾负责网易考拉RPC框架的研发及知道在内部使用，参与了服务治理平台、分布式跟踪系统、分布式一致性框架等从无到有的设计与开发过程。

**解决的问题域不一样：Dubbo的定位是一款RPC框架，Spring Cloud的目标是微服务架构下的一站式解决方案**



## 3.4 Spring Cloud能干什么

* Distributed/versioned configuration (分布式/版本配置)
* Service registration and discovery (服务注册与发现)
* Routing (路由)
* Service-to-service calls (服务到服务的调用)
* Load balancing (负载均衡配置)
* Circuit Breakers (断路器)
* Distributed messaging (分布式消息管理)
* ……

## 3.5、Spring Cloud在哪下

官网：[https://spring.io/projects/spring-cloud](https://spring.io/projects/spring-cloud)

![image-20210805000658659](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210805000705.png)

``` 
Spring Cloud是一个由众多独立子项目组成的大型综合项目，每个子项目有不同的发行节奏，都维护着自己的发布版本号。Spring Cloud通过一个资源清单BOM（Bill of Materials）来管理每个版本的子项目清单。为避免与子项目的发布好混淆，所有没有采用本号的方式，而是通过命名的方式。

这些版本名称命名方式采用了伦敦地铁站的名称，同时根据字母表的顺序来对应版本事件顺序，比如：最早的Release版本：Angel，第二个Release版本：Brixton，然后是Camden、Dalston、Edgware，目前最新的是Hoxton版本。
```



参考书：

* [Spring Cloud Netflix 中文文档 参考手册 中文版](https://www.springcloud.cc/spring-cloud-netflix.html)
* 中文API文档：[Spring Cloud Dalston 中文文档 参考手册 中文版](https://www.springcloud.cc/spring-cloud-dalston.html)
* Spring Cloud中文社区：[https://www.springcloud.io/](https://www.springcloud.io/)

* Spring Cloud中文网：[https://www.springcloud.cc/](https://www.springcloud.cc/)



# 4、Spring Cloud学习

## 4.1、总体介绍

* 我们会使用一个Dept部门模块做一个微服务通用案例Consumer消费者（client）通过REST调用Provider提供者（Server）提供的服务。

* 回忆Spring，SpringMVC，MyBatis等以往学习的知识

* Maven的分包分模块架构复习

* ```
  一个简单的Maven模块结构是这样的：
  
  -- app-parent：一个夫项目（app-parent)聚合很多子项目（app-util，app-dao，app-web...)
  	|--pom.xml
  	|
  	|--app-core
  	||----pom.xml
  	|
  	|--app-web
  	||----pom.xml
  	...
  ```

  一个父工程带着多个子Module子模块

  MicroServiceCloud父工程（Project）下初次带着3个子模块（Moudle）

  * microservicecloud-api 【封装的整体entity/接口/公共配置等】
  * microservicecloud-provider-dept-8001 【服务提供者】
  * microservicecloud-consumer-dept-80 【服务消费者】



## 4.2、Spring Cloud版本选择

**大版本说明**

| Spring Boot | Spring Cloud              | 关系                                           |
| ----------- | ------------------------- | ---------------------------------------------- |
| 1.2.x       | Angel版本（天使）         | 兼容Spring Boot 1.2.x                          |
| 1.3.x       | Brixton版本（布里克林顿） | 兼容Spring Boot 1.3.x，也兼容Spring Boot 1.4.x |
| 1.4.x       | Camden版本（卡姆登）      | 兼容Spring Boot 1.4.x，也兼容Spring Boot 1.5.x |
| 1.5.x       | Dalston版本（多尔斯顿）   | 兼容Spring Boot 1.5.x，不兼容Spring Boot 2.0.x |
| 1.5.x       | Edgware版本（埃奇韦尔）   | 兼容Spring Boot 1.5.x，不兼容Spring Boot 2.0.x |
| 2.0.x       | Finchley版本（芬奇利）    | 兼容Spring Boot 2.0.x，不兼容Spring Boot 1.5.x |
| 2.1.x       | Greenwich（格林威治）     |                                                |



##  4.3、创建父工程

* 新建父工程项目SpringCloud，切记Packaging是pom模式
* 主要定义POM文件，将后续各个子模块公用的jar包等统一提取出来，类似一个抽象父类

**pom.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.mrlqq</groupId>
    <artifactId>springcloud</artifactId>
    <version>1.0-SNAPSHOT</version>
    <modules>
        <module>springcloud-api</module>
    </modules>

    <!--打包方式 pom-->
    <packaging>pom</packaging>

    <properties>
        <junit.version>4.13.2</junit.version>
        <lombok.version>1.18.20</lombok.version>
        <log4j.version>1.2.17</log4j.version>
        <logback-core.version>1.2.3</logback-core.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <!--springcloud的依赖-->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Hoxton.SR12</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <!--SpringBoot-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>2.3.2.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <!--数据库-->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
                <version>1.2.5</version>
            </dependency>

            <!--SpringBoot 启动器-->
            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>2.2.0</version>
            </dependency>
            <!--junit-->
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>${junit.version}</version>
                <scope>test</scope>
            </dependency>
            <!--lombok-->
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>${lombok.version}</version>
            </dependency>
            <!--Log4j-->
            <dependency>
                <groupId>log4j</groupId>
                <artifactId>log4j</artifactId>
                <version>${log4j.version}</version>
            </dependency>
            <!--日志测试-->
            <dependency>
                <groupId>ch.qos.logback</groupId>
                <artifactId>logback-core</artifactId>
                <version>${logback-core.version}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>

</project>
```



## 4.2、创建api模块

在父工程下创建springcloud-api模块，该模块提供API服务，需要创建实体类。



### 1、POM

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud</artifactId>
        <groupId>org.mrlqq</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>springcloud-api</artifactId>

    <properties>
        <maven.compiler.source>12</maven.compiler.source>
        <maven.compiler.target>12</maven.compiler.target>
    </properties>

    <!--当前的Module自己需要的依赖，如果父以来中已经配置了版本。这里就不用写了-->
    <dependencies>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
    </dependencies>

</project>
```



### 2、创建数据库（db01）及数据表（dept）

![image-20210806093211096](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210806093211.png)

### 3、创建对应的实体类

```java
package com.MrLQQ.springcloud.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Dept 实体类
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)    // 链式写法
public class Dept implements Serializable {

    private Long deptno;
    private  String dname;

    // 这个数据库是存在哪个数据库的字段。
    // 微服务，一个服务对应一个数据库，同一个信息可能在不同的数据库
    private String db_source;

    public Dept(String dname){
        this.dname = dname;
    }

}

```



## 4.3、创建服务提供者

创建新模块springcloud-provider-dept-8001，并且提供数据库操作服务。

###  1、POM

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud</artifactId>
        <groupId>org.mrlqq</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>springcloud-provider-dept-8001</artifactId>

    <properties>
        <maven.compiler.source>12</maven.compiler.source>
        <maven.compiler.target>12</maven.compiler.target>
    </properties>

    <dependencies>
        <!--我们需要拿到实体类，所以要配置api moudle-->
        <dependency>
            <groupId>org.mrlqq</groupId>
            <artifactId>springcloud-api</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <!--junit-->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <scope>test</scope>
        </dependency>
        <!--数据库-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <!--数据源-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
        </dependency>
        <!--日志文件-->
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-core</artifactId>
        </dependency>
        <!--mybatis-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>
        <!--test-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-test</artifactId>
        </dependency>
        <!--web-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--jetty-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jetty</artifactId>
        </dependency>
        <!--热部署工具-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>
    </dependencies>

</project>
```



### 2、application.yml

配置一些主要信息

```yaml
server:
  port: 8001

# mybatis配置
mybatis:
  type-aliases-package: com.MrLQQ.springcloud.pojo
  config-location: classpath:mybatis/mybatis-config.xml
  mapper-locations: classpath:mybatis/mapper/*.xml

# spring配置
spring:
  application:
    name: springcloud-provider-dept
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/db01?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC
    password: password
    username: root


```

并且在resources下创建mapper.xml和config.xml文件

![image-20210806093828353](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210806093828.png)



**mybatis-config.xml**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybaits.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>
    <settings>
        <!--开启二级缓存-->
        <setting name="cacheEnabled" value="true"/>
    </settings>
</configuration>
```





### 3、创建对应的服务

创建对应的controller、dao、service

![image-20210806094008078](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210806094008.png)

#### 3.1、DeptDao

```java
package com.MrLQQ.springcloud.dao;

import com.MrLQQ.springcloud.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DeptDao {

    public boolean addDept(Dept dept);

    public Dept queryById(Long id);

    public List<Dept> queryAll();
}

```



**修改对应的DeptMapper.xml**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybaits.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.MrLQQ.springcloud.dao.DeptDao">
    
    <insert id="addDept" parameterType="com.MrLQQ.springcloud.pojo.Dept">
        insert into dept(dname, db_source)
        values (#{dname},DATABASE())
    </insert>

    <select id="queryById" resultType="com.MrLQQ.springcloud.pojo.Dept" parameterType="Long">
        select * from dept where deptno = #{deptno};
    </select>
    <select id="queryAll" resultType="com.MrLQQ.springcloud.pojo.Dept">
        select * from dept;
    </select>

</mapper>
```



#### 3.2、服务接口与服务实现类

**DeptService**

```java
package com.MrLQQ.springcloud.service;

import com.MrLQQ.springcloud.pojo.Dept;

import java.util.List;

public interface DeptService {

    public boolean addDept(Dept dept);

    public Dept queryById(Long id);

    public List<Dept> queryAll();

}

```



**DeptServiceImpl**

```java
package com.MrLQQ.springcloud.service;

import com.MrLQQ.springcloud.dao.DeptDao;
import com.MrLQQ.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService{

    @Autowired
    private DeptDao deptDao;

    @Override
    public boolean addDept(Dept dept) {
        return deptDao.addDept(dept);
    }

    @Override
    public Dept queryById(Long id) {
        return deptDao.queryById(id);
    }

    @Override
    public List<Dept> queryAll() {
        return deptDao.queryAll();
    }
}

```



####  3.3、创建Controller

**DeptController**

```java
package com.MrLQQ.springcloud.controller;

import com.MrLQQ.springcloud.pojo.Dept;
import com.MrLQQ.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 提供Restful服务
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @PostMapping("/dept/add")
    public boolean addDept(Dept dept){
        return deptService.addDept(dept);
    }

    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return deptService.queryById(id);
    }

    @GetMapping("/dept/list")
    public List<Dept> getAll(){
        return deptService.queryAll();
    }

}

```



####  3.4、主方法

创建主方法DeeptProvider_8001

```java
package com.MrLQQ.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 启动类
@SpringBootApplication
public class DeptProvider_8001 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider_8001.class, args);
    }
}

```



## 4.4、创建服务消费者

再创建一个新模块为服务消费者springcloud-customer-dept-80

![image-20210806095129894](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210806095129.png)

**POM**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud</artifactId>
        <groupId>org.mrlqq</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>springcloud-customer-dept-80</artifactId>

    <properties>
        <maven.compiler.source>12</maven.compiler.source>
        <maven.compiler.target>12</maven.compiler.target>
    </properties>

    <!--实体类+web-->
    <dependencies>
        <dependency>
            <groupId>org.mrlqq</groupId>
            <artifactId>springcloud-api</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>
    </dependencies>


</project>
```



**application.yaml**

```yaml
server:
  port: 80
```



将RestTemplate注册到spring中

**ConfigBean.java**

```java
package com.MrLQQ.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}

```



创建Controller，通过使用RestTemplate，传入服务提供者的服务接口（url）作为参数获得结果

**DeptCustomerController.java**

```java
package com.MrLQQ.springcloud.controller;

import com.MrLQQ.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptCustomerController {

    // RestTemplate 供我们直接调用就可以了！ 注册到Spring中
    // (url, 实体:Map, Class<T> responseType)
    @Autowired
    private RestTemplate restTemplate;  // 提供多种便捷访问远程http服务的方法，简单的Restful服务模板

    private static final String REST_URL_PREFIX = "http://localhost:8001";

    @RequestMapping("/customer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return restTemplate.getForObject(REST_URL_PREFIX+"dept/get/"+id, Dept.class);
    }

    @RequestMapping("/customer/dept/add")
    public boolean add(Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add", dept,Boolean.class);
    }

    @RequestMapping("/customer/dept/list")
    public List<Dept> list(){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/list", List.class);
    }

}

```



**主方法DeptCustomer_80**

```java
package com.MrLQQ.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeptCustomer_80 {
    public static void main(String[] args) {
        SpringApplication.run(DeptCustomer_80.class, args);
    }
}

```



> 然后就实现了服务的拆分，首先是服务的API-->服务提供者-->服务消费者。进行了完全解耦。



# 5、Eureka服务注册与发现

## 5.1、什么是Eureka

* Netflix在设计Eureka时，遵循的就是AP原则
* Eureka是Netflix的一个子模块，也是核心模块之一。Eureka是一个基于REST的服务，用于定位服务，用于定位服务，一是实现云端中间层服务发现和故障转移，服务注册与发现对于微服务来说是非常重要的，有了服务发现与注册，只需使用服务的标识符，就可以访问到服务，而不需要修改服务调用的配置文件了，功能类似于Dubbo的注册中心，比如Zookeeper。

## 5.2、原理讲解

* Eureka的基本架构

  * SpringCloud封装了Netflix公司开发的Eureka模块来实现服务注册和发现（对比Zookeeper）

  * Eureka采用了C-S的架构设计，EurekaServer作为服务注册功能的服务器，他是服务注册中心

  * 而系统中的其他微服务，使用Eureka的客户端链接到EurekaServer并维持心跳链接。这样系统的维护人员就可以通过EurekaServer来监控系统中各个微服务是否正常运行，SpringCloud的一些其他模块（比如Zuul）就可以通过EurekaServer来发现系统中的其他微服务，并执行相关的逻辑

  * 和Dubbo架构对比  ![image-20210808113550435](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210808113550.png)![image-20210808113515958](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210808113516.png)

  * Eureka包括两个组件：**Eureka Server** 和 **Eureka Client**

  * Eureka Server提供服务注册服务，各个节点启动后，会在EurekaServer中进行注册，这样EurekaServer中的服务注册表中将会存储所有服务节点的信息，服务节点的信息可以在界面中直观的看到。

  * Eureka Client是一个Java客户端，用于简化EurekaServer的文档，客户端同时也具有一个内置的，使用轮询负载算法的负载均衡器。在应用启动后，将会向EurekaServer发送心跳（默认周期为30秒）。如果EurekaServer在多个心跳周期内没有接收到某个节点的心跳，EurekaServer将会从服务注册表中把这个服务节点移除掉（默认周期为90表）

    

* 三大角色

  * Eureka Server：提供服务的注册与发现。zookooper

  * Service Provider：将自身服务注册到Eureka中，从而使消费方能找到。

  * Service Consumer：服务消费方从Eureka中获取注册服务列表，从而找到消费服务。

    

## 5.3、构建步骤

### 1、eureka-server

1. 创建springcloud-eureka-7001模块

   ![image-20210808131833444](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210808131833.png)

2. 导入eureka依赖

   **pom.xml**

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
       <parent>
           <artifactId>springcloud</artifactId>
           <groupId>org.mrlqq</groupId>
           <version>1.0-SNAPSHOT</version>
       </parent>
       <modelVersion>4.0.0</modelVersion>
   
       <artifactId>springcloud-eureka-7001</artifactId>
   
       <properties>
           <maven.compiler.source>12</maven.compiler.source>
           <maven.compiler.target>12</maven.compiler.target>
       </properties>
   
       <!--导包-->
       <dependencies>
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-eureka-server</artifactId>
               <version>1.4.7.RELEASE</version>
           </dependency>
           <!--热部署插件-->
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-devtools</artifactId>
           </dependency>
       </dependencies>
   
   </project>
   ```

3. 创建application.yaml添加Eureka配置

   ```yaml
   server:
     port: 7001
   
   # Eureka配置
   eureka:
     instance:
       hostname: localhost   # Eureka服务端的实例名称
     client:
       register-with-eureka: false   # 表示是否向Eureka注册中心注册自己
       fetch-registry: false   # 如果fetch-registry为false，侧表示自己为中注册中心
       service-url:    # 监控界面
         defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
   
   ```

4. 创建注册方法开启Eureka

   ```java
   package com.MrLQQ.springcloud;
   
   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;
   import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
   
   @SpringBootApplication
   @EnableEurekaServer // EnabliEurekaServer 服务端的启动类，可以接受别人注册进来
   public class EurekaServer_7001 {
       public static void main(String[] args) {
           SpringApplication.run(EurekaServer_7001.class,args);
       }
   }
   
   ```



### 2、修改服务提供者

1. 添加Eureka依赖

   ```xml
   <dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-eureka</artifactId>
       <version>1.4.7.RELEASE</version>
   </dependency>
   ```

2. application中增加对Eureka的配置

   ```yaml
   # Eureka的配置，服务注册到哪里
   eureka:
     client:
       service-url:
         defaultZone: http://localhost:7001/eureka/
     instance:
       instance-id: springcloud-provider-dept-8001   # 修改默认描述
   
   ```

3. 在启动类上添加开启Eureka客户端注解

   ```java
   @EnableEurekaClient //在服务器后自动注册到Eureka
   ```



------

分别启动Eureka注册中心和服务提供者，浏览器访问*localhost:7001*

![Snipaste_2021-08-08_13-39-38](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210808133950.jpg)



### 3、完善监控信息

1. 在服务端的pom中添加actuator依赖，用于完善监控信息

   ```xml
   <!--actuator完善监控信息-->
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-actuator</artifactId>
   </dependency>
   ```

   pom.xml完全展示

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
       <parent>
           <artifactId>springcloud</artifactId>
           <groupId>org.mrlqq</groupId>
           <version>1.0-SNAPSHOT</version>
       </parent>
       <modelVersion>4.0.0</modelVersion>
   
       <artifactId>springcloud-provider-dept-8001</artifactId>
   
       <properties>
           <maven.compiler.source>12</maven.compiler.source>
           <maven.compiler.target>12</maven.compiler.target>
       </properties>
   
       <dependencies>
           <!--Eureka依赖-->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-eureka</artifactId>
               <version>1.4.7.RELEASE</version>
           </dependency>
           <!--actuator完善监控信息-->
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-actuator</artifactId>
           </dependency>
           <!--我们需要拿到实体类，所以要配置api moudle-->
           <dependency>
               <groupId>org.mrlqq</groupId>
               <artifactId>springcloud-api</artifactId>
               <version>1.0-SNAPSHOT</version>
           </dependency>
           <!--junit-->
           <dependency>
               <groupId>junit</groupId>
               <artifactId>junit</artifactId>
               <scope>test</scope>
           </dependency>
           <!--数据库-->
           <dependency>
               <groupId>mysql</groupId>
               <artifactId>mysql-connector-java</artifactId>
           </dependency>
           <!--数据源-->
           <dependency>
               <groupId>com.alibaba</groupId>
               <artifactId>druid</artifactId>
           </dependency>
           <!--日志文件-->
           <dependency>
               <groupId>ch.qos.logback</groupId>
               <artifactId>logback-core</artifactId>
           </dependency>
           <!--mybatis-->
           <dependency>
               <groupId>org.mybatis.spring.boot</groupId>
               <artifactId>mybatis-spring-boot-starter</artifactId>
           </dependency>
           <!--test-->
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-test</artifactId>
           </dependency>
           <!--web-->
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-web</artifactId>
           </dependency>
           <!--jetty-->
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-jetty</artifactId>
           </dependency>
           <!--热部署工具-->
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-devtools</artifactId>
           </dependency>
       </dependencies>
   
   </project>
   ```

2. 在application中添加info配置（随意添加）

   ```yaml
   # info配置
   info:
     app.name: MrLQQ-springcloud
     company.name: blog.MrLQQ.com
   
   ```

3. 重新启动服务端程序后，再次访问Eureka，点击注册服务的Staus便可访问刚才添加的info

   ![Snipaste_2021-08-08_13-48-46](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210808134904.jpg)

![Snipaste_2021-08-08_13-46-52](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210808134716.jpg)

### 4、获取注册信息

1. 在服务提供端的controller里添加discovery()方法

   ```java
   // 注册进来的微服务，获取一些信息
   @GetMapping("/dept/discovery")
   public Object discovery(){
       // 获得微服务列表的清单
       List<String> services = client.getServices();
       System.out.println("discovery -> services : " + services);
   
       // 得到一个具体的微服务信息,通过具体的微服务id，applicationName
       List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
       for (ServiceInstance instance : instances) {
           System.out.println(
               instance.getHost()+"\t"+
               instance.getPort()+"\t"+
               instance.getUri()+"\t"+
               instance.getServiceId()
           );
       }
       return this.client;
   }
   ```

2. 启动服务后便可得到输出信息

   ![image-20210808135231876](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210808135231.png)



### 5、Eureka集群

![image-20210808143132850](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210808143132.png)

1. 建多个eureka模块

![image-20210808142926635](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210808142926.png)

2. 修改每个eureka的application中的链接，使其进行关联

   **springcloud-eureka-7001**

   ```yaml
   server:
     port: 7001
   
   # Eureka配置
   eureka:
     instance:
       hostname: eureka7001.com   # Eureka服务端的实例名称
     client:
       register-with-eureka: false   # 表示是否向Eureka注册中心注册自己
       fetch-registry: false   # 如果fetch-registry为false，侧表示自己为中注册中心
       service-url:    # 监控界面
         # 单机：http://${eureka.instance.hostname}:${server.port}/eureka/
         # 集群（关联）：
         defaultZone: http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/
   
   ```

   **springcloud-eureka-7002**

   ```yaml
   server:
     port: 7002
   
   # Eureka配置
   eureka:
     instance:
       hostname: eureka7002.com   # Eureka服务端的实例名称
     client:
       register-with-eureka: false   # 表示是否向Eureka注册中心注册自己
       fetch-registry: false   # 如果fetch-registry为false，侧表示自己为中注册中心
       service-url:    # 监控界面
         # 单机：http://${eureka.instance.hostname}:${server.port}/eureka/
         # 集群（关联）：
         defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7003.com:7003/eureka/
   
   ```

   **springcloud-eureka-7003**

   ```yaml
   server:
     port: 7003
   
   # Eureka配置
   eureka:
     instance:
       hostname: eureka7003.com   # Eureka服务端的实例名称
     client:
       register-with-eureka: false   # 表示是否向Eureka注册中心注册自己
       fetch-registry: false   # 如果fetch-registry为false，侧表示自己为中注册中心
       service-url:    # 监控界面
         # 单机：http://${eureka.instance.hostname}:${server.port}/eureka/
         # 集群（关联）：
         defaultZone: http://eureka7002.com:7002/eureka/,http://eureka7001.com:7001/eureka/
   
   ```

3. 修改服务提供者的链接

   ```yaml
   server:
     port: 8001
   
   # mybatis配置
   mybatis:
     type-aliases-package: com.MrLQQ.springcloud.pojo
     config-location: classpath:mybatis/mybatis-config.xml
     mapper-locations: classpath:mybatis/mapper/*.xml
   
   # spring配置
   spring:
     application:
       name: springcloud-provider-dept
     datasource:
       type: com.alibaba.druid.pool.DruidDataSource
       driver-class-name: com.mysql.cj.jdbc.Driver
       url: jdbc:mysql://localhost:3306/db01?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC
       password: password
       username: root
   
   # Eureka的配置，服务注册到哪里
   eureka:
     client:
       service-url:
         defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/
     instance:
       instance-id: springcloud-provider-dept-8001   # 修改默认描述
   
   # info配置
   info:
     app.name: MrLQQ-springcloud
     company.name: blog.MrLQQ.com
   
   ```

4. 启动集群测试

   ![image-20210808143911499](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210808143911.png)



## 5.4、对比Zookeeper

**回顾CAP原则**

RDBMS（Mysql、Oracle、sqlServer） ===> ACID

NoSQL（redis、mongdb）===> CAP

**ACID是什么？**

* A（Atomicity）原子性
* C（Consistency）一致性
* I（Isolation）隔离性
* D（Durability）持久性

**CAP是什么？**

* C（Consistency）强一致性
* A（Availability）可用性
* P（Parition tolerance）分区容错性

CAP原则最多只能实现两个：CA、AP、CP 。不能三者兼顾

==CAP理论核心==

* 一个分布式系统不可能同时很好的满足一致性，可用性和分区容错性这三个需求
* 根据CAP原理，将NoSQL数据库分成了满足CA原则、满足CP原则和满足AP缘散三大类：
  * CA：单点集群，满足一致性，可用性的系统，通常可扩展性较差
  * CP：满足一致性，分区容错性的系统，通常性能不是特别高
  * AP：满足可用性，分区容错性的系统，通常可能对一致性要求低一些



#### 作为服务注册中心，Eureka比Zookeeper好在哪里？

著名的CAP理论指出，一个分布式系统不可能同时满足C（一致性）、A（可用性）、P（容错性）。

由于分区容错性P在分布式系统中是必须要保证的，因此我们只能在AC之间进行权衡

* Zookeeper保证的是CP
* Eureka保证的AP



**Zookeeper保证的CP**

​	当想注册中心查询服务列表，我们可以容忍注册中心返回的是几分钟以前的注册信息，但不能接受服务直接down掉不可用。也就是说，服务注册功能对可用性的要求要高于一致性。但是zookeeper会出现这样一种情况，当master节点因为网络古装与其他节点失去联系时，剩余节点会重新进行leader选举。问题在于，选举leader的时间太长，30~120s，且选举期间整个zookeeper集群都是不可用的，这就导致在选举期间注册服务瘫痪，在云部署的环境下，因为网络问题使得zookeeper集群失去master节点是较大概率会发生的事件，虽然服务最终能够恢复，但是漫长的选举事件导致的注册长期不可用是不能容忍的。



**Eureka保证的AP**

Eureka看明白了这一点，因此在设计时就优先保证可用性，**Eureka各个节点都是平等的**，几个节点挂掉不会影响正常节点的工作，剩余的节点依然可以提供注册和查询服务。而Eureka的客户端在向某Eureka注册时，如果发现连接失败，则会自动切换至其他节点，只要有一台Eureka还在，就能保证注册服务的可用性，只不过查到的信息可能不是最新的，除此之外，Eureka还有一种自我保护机制，如果在15分钟内超过85%的节点都没有正常的心跳，那么Eureka就认为客户端与注册中心出现了网络故障，此时会出现以下几种情况：

1. Eureka不再从注册列表中移除以你为长时间没收到心跳而应该过期的服务
2. Eureka仍然能够接受新服务的中注册和查询请求，但是不会被同步到其他节点上（即保证当前节点依然可用）
3. 当网络稳定时，当前实例新的注册信息会被同步到其他节点中



==因此，Eureka可以很好的应对因网络故障导致部门节点失去联系的情况，而不会像zookeeper那样使整个注册服务瘫痪==



# 6、Ribbon

## 6.1、什么是Ribbon

**ribbon是什么**

* Spring Cloud Ribbon是基于Netflix Ribbon实现的一套==**客户端负载均衡的工具**==
* 简单的说，Ribbon是Netfli发布的开源项目，主要功能是提供客户端的软件负载均衡算法，将Netflix的中间层服务连接在一起。Ribbon的客户端组件提供一些列完整的配置项如：连接超时、重试等等。简单的说，就是在配置文件中列出Load Balance（简称LB：负载均衡）后面所有的机器，Ribbon会自动的帮助你基于某种规则（如简单轮询，随即链接等等）去链接这些机器。我们也很容易使用Ribbon实现自定义的负载均衡算法！



**Ribbon能干嘛？**

* LB，即负载均衡（Load Balance），在微服务或分布式集群中经常用的一种应用。 
* 负载均衡简单的说就是将用户的请求平摊的分配到多个服务上，从而达到系统的HA（高可用）。
* 常见的负载均衡软件由`Nginx`，`Lvs`等等
* dubbo、SPring CLoud中均给我们提供了负载均衡，**Spring Cloud的负载均衡算法可以自定义**
* 负载均简单分类：
  * 集中式LB
    * 即在服务的消费方和提供方之间使用独立的LB设置，如Nginx：反向代理服务器，由该设置负责把访问请求通过某种策略转发至服务的提供方
  * 进程式LB
    * 将LB逻辑集成到消费方，消费方从服务注册中心获知哪些地址可用，然后自己再从这些地址中选出一个合适的服务器。
    * ==Ribbon就属于进程内LB==，他只是一个类库，继承于消费方进程，消费方通过它获取到服务提供方的地址！

## 6.2、实现步骤

1. 创建多个服务提供者

   ![image-20210810232146681](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210810232146.png)

2. 在消费者中添加Ribbon和Eureka依赖

   ```xml
   <!-- Ribbon-->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-ribbon</artifactId>
               <version>1.4.6.RELEASE</version>
           </dependency>
           <!--Eureka依赖-->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-eureka</artifactId>
               <version>1.4.7.RELEASE</version>
           </dependency>
   ```

3. 在消费者添加Eureka配置

   ```yaml
   # Eureka配置
   eureka:
     client:
       register-with-eureka: false   # 不向Eureka中注册自己
       service-url:
         defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/
   
   
   ```

4. 在消费者的ConfigBean中，对GetRestTemplate()方法添加`@LoadBalanced`注解，开启负载均衡功能

   ```java
   package com.MrLQQ.springcloud.config;
   
   import org.springframework.cloud.client.loadbalancer.LoadBalanced;
   import org.springframework.context.annotation.Bean;
   import org.springframework.context.annotation.Configuration;
   import org.springframework.web.client.RestTemplate;
   
   @Configuration
   public class ConfigBean {
   
       // 配置负载均衡实现RestTemplate
       // IRule
       // AvailabilityFilteringRule : 贤惠过滤掉跳闸 访问故障的服务，对剩下的及逆行轮询
       // RoundRobinRule: 轮询（默认）
       // RandomRule: 随机
       // RetryRule: 会先按照轮询获取服务，如果服务获取失败，则会在指定的时间内进行重试
       @Bean
       @LoadBalanced   // Ribbon
       public RestTemplate getRestTemplate(){
           return new RestTemplate();
       }
   
   }
   
   ```

   

5. 修改Controller的访问地址，为变量（服务名字）

   ```java
   package com.MrLQQ.springcloud.controller;
   
   import com.MrLQQ.springcloud.pojo.Dept;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.web.bind.annotation.PathVariable;
   import org.springframework.web.bind.annotation.RequestMapping;
   import org.springframework.web.bind.annotation.RestController;
   import org.springframework.web.client.RestTemplate;
   
   import java.util.List;
   
   @RestController
   public class DeptCustomerController {
   
       // RestTemplate 供我们直接调用就可以了！ 注册到Spring中
       // (url, 实体:Map, Class<T> responseType)
       @Autowired
       private RestTemplate restTemplate;  // 提供多种便捷访问远程http服务的方法，简单的Restful服务模板
   
       // 通过Ribbon实现， 我们这里的地址，应该是一个变量，通过服务来访问
       // private static final String REST_URL_PREFIX = "http://localhost:8001";
       private static final String REST_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";
   
       ……………………
   
   }
   
   ```



### 自定义负载均衡

自定义负载均衡时，应当把自定义的方法放在程序主入口的上级目录，不应该放在于主方法同级或下级目录，避免自定义负载均衡方法覆盖系统方法

![image-20210810233257965](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210810233258.png)

1. 通过集成`AbstractLoadBalancerRule`方法编写自己的负载均衡方法。

   ```java
   package com.MrLQQ.myrule;
   
   import com.netflix.loadbalancer.AbstractLoadBalancerRule;
   import com.netflix.loadbalancer.ILoadBalancer;
   import com.netflix.loadbalancer.Server;
   
   import java.util.List;
   import com.netflix.client.config.IClientConfig;
   
   public class LqqRandomRule extends AbstractLoadBalancerRule {
   
       // 每个服务访问5次，换下一个服务
       // total=0，默认=0，如果=5，我们指向下一个服务节点
       // index=0，默认0，如果total=5，index+1
   
       private int total = 0;  //被调用的次数
       private int currentIndex=0; //当前式谁在提供服务
       
       public Server choose(ILoadBalancer lb, Object key) {
           if (lb == null) {
               return null;
           }
           Server server = null;
   
           while (server == null) {
               if (Thread.interrupted()) {
                   return null;
               }
               List<Server> upList = lb.getReachableServers(); //获得活着的服务
               List<Server> allList = lb.getAllServers();  //获得全部的服务
   
               int serverCount = allList.size();
               if (serverCount == 0) {
                   return null;
               }
   
   //            int index = chooseRandomInt(serverCount);   // 生生产区间随机函数
   //            server = upList.get(index); // 从活着的服务中，随机获得一个
   
               //-========================================
   
               if (total<5){
                   server = upList.get(currentIndex);
                   total++;
               }else {
                   total=0;
                   currentIndex++;
                   if (currentIndex>=upList.size()){
                       currentIndex = 0;
                   }
                   server = upList.get(currentIndex);  // 从活着的服务中，获取只当的服务来进行操作
               }
   
               //-========================================
   
               if (server == null) {
                   Thread.yield();
                   continue;
               }
   
               if (server.isAlive()) {
                   return (server);
               }
   
               // Shouldn't actually happen.. but must be transient or a bug.
               server = null;
               Thread.yield();
           }
   
           return server;
   
       }
   
       @Override
       public Server choose(Object key) {
           return this.choose(this.getLoadBalancer(),key);
       }
   
       @Override
       public void initWithNiwsConfig(IClientConfig iClientConfig) {
   
       }
   }
   ```

2. 将自己的方法加入到Bean

   ```java
   package com.MrLQQ.myrule;
   
   import com.netflix.loadbalancer.IRule;
   import org.springframework.context.annotation.Bean;
   import org.springframework.context.annotation.Configuration;
   
   @Configuration
   public class LqqRule {
   
   
       @Bean
       public IRule myRule(){
           return new LqqRandomRule(); //默认是轮询，现在是自定义的LqqRandomRUle
       }
   }
   ```

3. 在主方法使用`@RibbonClient`注解使用自己的负载均衡

   ```java
   package com.MrLQQ.springcloud;
   
   import com.MrLQQ.myrule.LqqRule;
   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;
   import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
   import org.springframework.cloud.netflix.ribbon.RibbonClient;
   
   
   // Ribbon 和 Eureka 整合以后，客户端可以直接调用，不用关心IP地址和端口号
   @SpringBootApplication
   @EnableEurekaClient
   // 在微服务开启的时候就能去加载我们自定义Ribbon类
   @RibbonClient(name = "SPRINGCLOUD-PROVIDER-DEPT", configuration = LqqRule.class)
   public class DeptCustomer_80 {
       public static void main(String[] args) {
           SpringApplication.run(DeptCustomer_80.class, args);
       }
   }
   ```

> 在使用Spring Cloud Ribbon的时候要注意Ribbon于Eureka之间的版本冲突问题
>
> 我的环境是：Java 12.0.2、Spring Boot 2.3.2、Spring Cloud Hoxton.SR2、spring-cloud-starter-ribbon 1.4.7.RELEASE、spring-cloud-starter-Eureka 1.4.7.RELEASE



# 7、Feign负载均衡

## 7.1、简介

feign是声明式的web service客户端，他让微服务之间的调用变得更简单的了，类似controller调用service。Spring Cloud继承了Ribbon和Eureka，可在使用Feign时提供负载均衡的http客户端。

只需要创建一个接口，然后添加注解即可。



Feign，主要是社区，大家都习惯面向接口编程。这个是很多开发人员的规范。调用微服务访问两种方法

1. 微服务名字【Ribbon】
2. 接口和注解【Feign】



**Feign能干什么？**

* Feign旨在使编写Java Http客户端变得更容易
* 前面在使用Ribbon + RestTemplate的时候，利用RestTemplate对Http请求的封装处理，形成了一套模板化的调用方法。但是在实际开发中，由于对服务依赖的调用可能不止一处，往往一个接口会被多处调用，所哟通常都会针每个微服务自行封装一些客户端类来包装这些依赖服务的调用。所以，Feign在此基础上做了进一步封装，由他来帮助我们定义和实现依赖服务接口的定义，==在Feignb的是线下，我们只需要创建一个接口并使用注解的方式来配置它（类似于以前Dao接口上标注Mapper注解，现在是一个微服务接口上面标注一个Feigb注解即可。）==即可完成对服务提供方的接口绑定，简化了使用Spring Cloud Ribbon时，自动封装服务调用客户端的开发量。



**Feign继承了Ribbon**

* 利用Ribbon维护了MicroServiceCloud-Dept的服务列表信息，并且通过轮询实现了客户端的服务在均衡，而与Ribbon不同的是，通过Feign只需要定义服务绑定接口且以声明的方法，优雅而且简单的实现了服务调用。



## 7.2、Feign使用步骤

1. 创建Feign模块

   ![image-20210811153612803](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210811153613.png)

2. 在feignb模块和api模块中导入Feign依赖

   ```xml
   <!--feign-->
   <dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-feign</artifactId>
       <version>1.4.7.RELEASE</version>
   </dependency>
   ```

3. 在api模块中创建service包，创建DeptCLientService接口

   ```java
   @Component	// 注册服务
   @FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT")
   public interface DeptClientService {
   
   
       @GetMapping("/dept/get/{id}")
       public Dept queryById(@PathVariable("id") Long id);
   
       @GetMapping("/dept/list")
       public List<Dept> queryAll();
   
       @PostMapping("/dept/add")
       public boolean addDept(Dept dept);
   }
   ```

4. 并在feign模块中controller里使用接口服务

   ```java
   @RestController
   public class DeptConsumerController {
   
       @Autowired
       private DeptClientService service = null;
   
   
       @RequestMapping("/customer/dept/add")
       public boolean add(Dept dept){
           return this.service.addDept(dept);
       }
   
       @RequestMapping("/customer/dept/get/{id}")
       public Dept get(@PathVariable("id") Long id){
           return this.service.queryById(id);
       }
   
       @RequestMapping("/customer/dept/list")
       public List<Dept> list(){
           return this.service.queryAll();
       }
   
   }
   ```

5. 在feign启动类中使用`@EnableFeigbClients()`开启Feign功能

   ```java
   @SpringBootApplication
   @EnableEurekaClient
   @EnableFeignClients(basePackages = {"com.MrLQQ.springcloud"})
   @ComponentScan({"com.MrLQQ.springcloud"})
   public class FeignDeptConsumer_80 {
       public static void main(String[] args) {
           SpringApplication.run(FeignDeptConsumer_80.class, args);
       }
   }
   ```



# 8、Hystrix服务熔断

#### 分布式系统面临的问题

复杂分布式体系结构中的应用程序有数十个依赖关系，每个依赖关系在某些时候将不可避免的失败！



#### 服务雪峰

​		多个微服务之间调用时的时候，假设微服务A调用微服务B和微服务C，微服务B和微服务C又调用其他的微服务，这就是所谓的“扇出”，如果扇出的链路上某个微服务的调用响应时间过长或者不可用，对微服务A的调用就会占用越来越多的系统资源，进而引起系统崩溃，所谓的“雪崩效应”

​		对于高流量的应用来说，单一的后端依赖可能会导致所有服务器上的所有资源都在几秒中内饱和。比失败更糟糕的是，这些应用程序还可能导致服务之间的延迟增加，备份队列，线程和其他系统资源紧张，导致整个系统发生更多的级联故障，这些都表示需要对故障和延迟进行隔离和管理，以便单个依赖关系的失败，不能取消整个应用程序或系统。



#### 什么是Hystrix

​		Hystrix是一个用于处理分布式系统的延迟和容错的开源库，在分布式系统里，许多依赖不可避免的会调用失败，比如超时，异常等。Hystrix能够保证在一个依赖出问题的情况下，不会导致整体服务失败，避免级联故障，以提高分布式系统的弹性。

​		“断路器”本身是一种开关装置，当某个服务单元发生故障之后，通过断路器的故障监控（类似熔断保险丝），**向调用方返回一个服务预期的，可处理的备选响应（FallBack），而不是长时间的等待或者抛出调用方法无法处理的异常，这样就可以保证了服务调用方的线程不会被长时间，不需要的占用，**从而避免了故障在分布式系统中的蔓延，乃至雪崩。



**能干嘛**

* 服务降级
* 服务熔断
* 服务限流
* 接近实时的监控
* ……



**官网资料**

[Home · Netflix/Hystrix Wiki (github.com)](https://github.com/Netflix/Hystrix/wiki)



## 8.1、服务熔断

熔断机制是对应雪崩效应的一种微服务链路保护机制。

当扇出链路的某个微服务不可用或者响应时间太长时，会及进行服务的降级，==进而熔断该节点微服务的调用，快速返回错误的相应信息==。当检测到该节点为服务调用相应正常后恢复调用链路。在Spring Cloud 框架里熔断机制通过Hystrix实现。Hystrix会监控微服务间调用的状况，当失败的调用到一定阈值，缺省是5秒内20次调用失败就会启动熔断机制，熔断机制的注解是`@HystrixConmmand`。

**实现步骤**

> *服务熔断是在服务端进行的，某个服务超时或者异常，引起熔断*

1. 创建Hystrix的服务提供者模块

   ![image-20210812143931002](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210812143931.png)

2. 添加Hystrix依赖

   ```xml
   <!--Hystrix依赖-->
   <dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-hystrix</artifactId>
       <version>1.4.7.RELEASE</version>
   </dependency>
   ```

3. 编写Controller使用`@HystrxCommand`注解指向一个备用方法

   ```java
   // 提供Restful服务
   @RestController
   public class DeptController {
   
       @Autowired
       private DeptService deptService;
   
       @GetMapping(value = "/dept/get/{id}")
       @HystrixCommand(fallbackMethod = "hystrixGet")
       public Dept get(@PathVariable("id") Long id){
           Dept dept = deptService.queryById(id);
           if (dept == null) {
               throw new RuntimeException("id=>"+id+",不存在该用户，或者信息无法找到");
           }
           return dept;
       }
   
       // 备选方案
       public Dept hystrixGet(@PathVariable("id") Long id){
           return new Dept()
                   .setDeptno(id)
                   .setDname("id=>"+id+"没有对应的信息，null--@Hysttrix")
                   .setDb_source("no this database in MySQL");
       }
   
   }
   ```

4. 在启动类上开启对熔断的支持`@EnableCircuitBreaker`

   ```java
   // 启动类
   @SpringBootApplication
   @EnableEurekaClient // 在服务启动后自动中注册到Eureka
   @EnableDiscoveryClient  // 服务发
   @EnableCircuitBreaker   // 添加对熔断的支持
   public class DeptProvider_hystrix_8001 {
       public static void main(String[] args) {
           SpringApplication.run(DeptProvider_hystrix_8001.class, args);
       }
   }
   ```

   





## 8.2、服务降级

**实现步骤**

>  *服务降级在客户端实现，从整体往回走那请求负载考虑。当某个服务熔断或者关闭之后，服务将不再被调用*
>
> 此时在客户端，我们可以准备一个`FallBackFactory`，返回一个默认值（缺省值），整体的服务水平下降了

1. 首先在API中创建一个DeptClientServiceFallBackFactory服务

   ```java
   // 降级
   @Component
   public class DeptClientServiceFallBackFactory implements FallbackFactory {
       @Override
       public DeptClientService create(Throwable throwable) {
           return new DeptClientService() {
               @Override
               public Dept queryById(Long id) {
                   return new Dept()
                           .setDeptno(id)
                           .setDname("id=>"+id+"没有对应的信息，客户端提供了降级的信息，这个服务现在已被关闭")
                           .setDb_source("没有数据");
               }
   
               @Override
               public List<Dept> queryAll() {
                   return null;
               }
   
               @Override
               public boolean addDept(Dept dept) {
                   return false;
               }
           };
       }
   }
   ```

2. 在Feign服务端的配置文件中开启降级

   ```yaml
   # 开启降级
   feign:
     hystrix:
       enabled: true
   ```

3. 之后启动各项服务后，在突然接入服务提供端的时候，客户端仍然能显示一些提醒文字，而不会报错

   ![正常访问](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210812150213.png)

   ![服务断开后](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210812150247.png)



## 8.3、服务监控

**实现步骤**

通过Hystrix Dashboard实现对服务的监控

1. 首先创建一个dashboard模块

   ![image-20210812150633533](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210812150633.png)

2. 添加依赖，修改服务端口9001

   ```xml
    <!--实体类+web-->
   <dependencies>
   
       <!--Hystrix依赖-->
       <dependency>
           <groupId>org.springframework.cloud</groupId>
           <artifactId>spring-cloud-starter-hystrix</artifactId>
           <version>1.4.7.RELEASE</version>
       </dependency>
       <!--Hystrix 监控依赖-->
       <dependency>
           <groupId>org.springframework.cloud</groupId>
           <artifactId>spring-cloud-starter-hystrix-dashboard</artifactId>
           <version>1.4.7.RELEASE</version>
       </dependency>
   
       <!-- Ribbon-->
       <dependency>
           <groupId>org.springframework.cloud</groupId>
           <artifactId>spring-cloud-starter-ribbon</artifactId>
           <version>1.4.6.RELEASE</version>
       </dependency>
       <!--Eureka依赖-->
       <dependency>
           <groupId>org.springframework.cloud</groupId>
           <artifactId>spring-cloud-starter-eureka</artifactId>
           <version>1.4.7.RELEASE</version>
       </dependency>
   
   
       <dependency>
           <groupId>org.mrlqq</groupId>
           <artifactId>springcloud-api</artifactId>
           <version>1.0-SNAPSHOT</version>
       </dependency>
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-web</artifactId>
       </dependency>
       <!--热部署-->
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-devtools</artifactId>
       </dependency>
   
   </dependencies>
   ```

3. 启动类中开启监控

   ```java
   @SpringBootApplication
   @EnableHystrixDashboard //开启监控
   public class DeptConsumerDashboard_9001 {
       public static void main(String[] args) {
           SpringApplication.run(DeptConsumerDashboard_9001.class,args);
       }
   }
   ```

4. 在需要被监控的服务的启动类中加入一个Bean

   ```java
   //增加一个Servlet
   @Bean
   public ServletRegistrationBean hystrixMetricsStreamServlet(){
       ServletRegistrationBean registrationBean = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
       registrationBean.addUrlMappings("/actuator/hystrix.stream");
       return registrationBean;
   }
   ```

5. 启动服务后打开监控页面 localhost:9001/hystrix

   ![image-20210812151326153](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210812151326.png)

6. 监控目标服务的端口+/actuator/hystrix.stream，比如：http://localhost:8001/actuator/hystrix.stream

   ![创建新的监控](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210812151518.png)

   ![监控中](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210812151559.png)

   ![样图说明](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210812151713.png)



# 9、Zuul路由网关

## 9.1、概述

### 什么是Zuul？

Zuul包括了对请求的路由和过滤两个重要的功能：

其中路由功能负责将外部请求转发到具体的微服务实例上，是实现外部访问统一入口的基础，而过滤器功能则负责对请求的处理过程进行干预，是实现请求校验，服务聚合等功能的基础，Zuul和Eureka进行整合，将Zuul自身注册为Eureka服务治理下的应用，同时从Eureka中获得其他微服务的消息，也即以后的访问微服务都是通过Zuul跳转后获得。

​		注意：Zuul服务最终还是会注册进Eureka

​		提供：代理+路由+过滤 三大功能



**Zuul能干嘛**

* 路由
* 过滤



官网文档：[https://github.com/Netflix/zuul](https://github.com/Netflix/zuul)



## 实现步骤

1. 创建zuul模块

   ![image-20210813160255535](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210813160302.png)

2. 导入zuul依赖

   ```xml
   <!--zuul-->
   <dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-zuul</artifactId>
       <version>1.4.7.RELEASE</version>
   </dependency>
   ```

3. 配置文件

   ```yaml
   server:
     port: 9527
   spring:
     application:
       name: springcloud-zuul
   eureka:
     client:
       service-url:
         defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/
     instance:
       instance-id: zuul9527.com
       prefer-ip-address: true
   
   info:
     app.name: MrLQQ-springcloud
     company.name: blog.MrLQQ.com
   zuul:
     routes:
       mydept.serviceId: springcloud-provider-dept
       mydept.path: /mydept/**
     ignored-services: springcloud-provider-dept   # 忽略服务，不能再使用这个路径访问了
     prefix: /mrlqq    # 设置公共的访问前缀
   ```

4. 创建入口类打开zuul网关`@EnableZuulProxy`

   ```java
   @SpringBootApplication
   @EnableZuulProxy    // 开启Zuul代理
   public class ZuulApplication_9527 {
       public static void main(String[] args) {
           SpringApplication.run(ZuulApplication_9527.class,args);
       }
   }
   ```



> 再通过Zuul访问服务的时候，如果在配置文件里面还未设置代理路由，那么在访问的时候应该通过小写的服务名进行访问。



# Spring Cloud config分布式配置

## 概述

**分布式系统面临的配置文件的问题**

微服务意味着要将单体应用中的业务拆分成一个个子服务，每个服务的粒度相对较小，因此系统中会出现大量的服务，由于每个服务都需要必要的配置信息才能运行，所以一套集中式的，动态的配置管理设施是必不可少的。

Spring Cloud提供了Config Server来解决这个问题，我们每个微服务自己带着一个application.yaml，那上百个的配置文件要修改起来，是非常麻烦的



**什么是Spring Cloud config分布式配置中心**

![image-20210813161558121](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210813161558.png)

Spring Cloud Config微服务架构中的微服务提供集中化的外部配置支持，配置服务器为**各个不同微服务应用**的所有环节提供了一个**中心化的外部配置**。

Spring Cloud Config 分为**服务端**和**客户端**两部分：

​		服务端也称为 分布式配置中心，他是一个独立的微服务应用，用来连接配置服务器并为客户端提供获取配置信息，加密，解密信息等访问接口。

​		客户端则是通过指定的配置中心来管理应用资源，以及与业务线管的配置内容，并在启动的时候从配置中心获取和加载配置信息。配置服务器默认采用git来存储配置i辛纳希，这样就有助于对环境进行版本管理。并且可以通过git客户端工具来方便的管理和访问配置内容。



**Spring Cloud config分布式配置中心能干嘛**

* 集中管理配置文件
* 不同环境，不同配置，动态化的配置更新，分环境部署，比如 /dev, /test,  /prod, /beta, /release
* 运行期间动态调整配置，不再需要在每个服务部署的机器上编写配置文件，服务会向配置中心统一拉取配置自己的信息。
* 当配置发生变动时，服务不需要重启，即可感知到配置的变化，并应用新的配置
* 将配置信息以REST接口的形式暴露



**SPringle Cloud Config分布式配置中心与githunb整合**

由于Spring Cloud Config 默认使用Git来存储配置文件（也有其他方式，比如支持SVN和本地文件），到那时最推荐的还是Git，而且使用的是 http/http访问的形式。



## 实现步骤

1. 首先我们要在本地安装git，然后我们在github上创建一个新的仓库，在里面提交我们的配置文件

   ![image-20210813183713697](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210813183713.png)

   ![image-20210813183748841](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210813183748.png)

2. 在项目里创建一个config server服务器用来连接github

  ![image-20210813184217814](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210813184217.png)

  导入必要的依赖

  ```xml
  dependencies>
      <!--config server依赖-->
      <dependency>
          <groupId>org.springframework.cloud</groupId>
          <artifactId>spring-cloud-config-server</artifactId>
      </dependency>
      <!--actuator-->
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-actuator</artifactId>
      </dependency>
      <!--web依赖-->
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-web</artifactId>
      </dependency>
  
  </dependencies>
  ```
  
  修改配置文件
  
  ```yaml
  server:
    port: 3344
  
  spring:
    application:
      name: springcloud-config-server
      # 连接远程仓库
    cloud:
      config:
        server:
          git:
            uri: https://github.com/MrLQQ/springcloud-config.git  # https，不是git
  
  # 通过config-server可以连接到git，访问其中的资源以及配置
  ```
  
   在入口类中开启Config服务`@EnableConfigServer`
  
  ```java
  @SpringBootApplication
  @EnableConfigServer     // 开启Config服务
  public class Config_Server_3344 {
      public static void main(String[] args) {
          SpringApplication.run(Config_Server_3344.class,args);
      }
  }
  ```
  
3.  创建config-client模块进行测试
  
  ![image-20210813184918389](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210813184918.png)
  
  添加必要的依赖文件
  
  ```xml
   <!--config-->
      <dependency>
          <groupId>org.springframework.cloud</groupId>
          <artifactId>spring-cloud-starter-config</artifactId>
      </dependency>
      <!--actuator-->
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-actuator</artifactId>
      </dependency>
      <!--web依赖-->
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-web</artifactId>
      </dependency>
  </dependencies>
  ```
  
   添加配置信息
  
  ```yaml
  # 系统级别的配置
  
  spring:
    cloud:
      config:
        uri: http://localhost:3344
        name: config-client   # 需要从git上面读取的资源名称，不需要后缀
        profile: test
        label: master # 分支
  ```
  
4. 创建controller，写一个用于测试的方法，把从通过config server获取到git上的配置信息打印出来

   ```java
   @RestController
   public class ConfigClientController {
   
       @Value("${spring.application.name}")
       private String applicationName;
   
       @Value("${eureka.client.service-url.defaultZone}")
       private String eurekaServer;
   
       @Value("${server.port}")
       private String port;
   
       @RequestMapping("/config")
       public String getConfig(){
           return "applicationName = " + applicationName+
                   "eurekaServer = " + eurekaServer+
                   "port = " + port;
       }
   
   }
   ```

5. 在浏览器中进行访问测试

   ![image-20210813185648147](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210813185648.png)

   > 我们的访问端口是根据我们client中的配置文件里的`spring.cloud.config.profile`的值进行改变的，因为这个值表示的是我们要选择的github上配置文件的名字。如下图



   ![config-client的配置文件选择的是”test“](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210813190101.png)

![config-client的配置信息将会被github上test配置替换](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20210813190012.png)

