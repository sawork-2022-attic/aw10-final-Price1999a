# aw10-final


Please develop a **fully functional** online purchase order system.

- It should have a superb collection of goods merchandises
- Customer can browse/search for merchandises, add selected one into his shopping cart and checkout to complete a transaction.
- User can get delivery status updates continuously.

The system should be of a **reactive architecture**, which means it should be 

-  Responsive: it should response to the user request timely.
-  Resilient: it should not be easily broken down.
-  Elastic: it should be flexible to scale out.
-  Message Driven: it should has loosely coupled components that communicates with each other asynchronously.


Please design tests/experiements to demostrate that your system fulfills such requirements as stated in [The Reactive Manifesto](https://www.reactivemanifesto.org)

**Submit your codes/documents/tests/experiements of your system.**

## 报告

### 要求
请开发一个功能齐全的在线采购订单系统。

- 它应该有一个极好的商品集合
- 客户可以浏览/搜索商品，将选定的商品添加到购物车并结帐以完成交易。
- 用户可以不断获取交货状态更新。

系统应该是反应式架构，这意味着它应该是
- 高响应性：及时响应用户请求。
- 可靠性：它不应该被轻易崩溃。
- 弹性：它应该灵活地向外扩展。
- 消息驱动：它应该具有松散耦合的组件，彼此异步通信。

### 系统简介

系统架构图如下：
![](aw05-Price1999a/image/aw09.svg)

系统中共有5个微服务构成：
- discovery微服务是eureka服务器，其他微服务均对应的是客户端。
- gateway微服务，网关微服务，实际上就是为了对外提供一个统一的接口。
- products微服务，负责管理商品集合，提供了浏览搜索商品的能力，
- cart微服务，提供了添加商品到购物车以及结账届接口
- delivery微服务，负责处理交货状态，提供了信息查询接口，以及sse功能为用户不断推送订单状态。

通过以上5个微服务，我们实现了要求：一个功能齐全的在线采购订单系统

以下将介绍对应的一些特性

#### 高响应性

#### 可靠性

#### 弹性

#### 消息驱动

