![](https://img-blog.csdnimg.cn/5c520381e11b4f39a59cbb8781f7a150.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NzYwMDg4MA==,size_16,color_FFFFFF,t_70)
>jieyue捷阅网是基于springboot开发的轻量级单体架构购物商城网站，并分为用户、商户、后台三大模块。用户模块能够进行商品搜索浏览、在线下单、微信支付等功能；商户模块则可以对收益情况以及订单成交情况进行查看、并且可对商品进行上下架和增加删除等操作；后台模块可进行群发消息通知、RBAC权限管理、商户的停用启用以及商城的广告海报设置等功能。

---

# 前言
**网站制作者为大二在读学生，若网站在设计中存在不足之处，还请各位大牛指正！！！**

- 网站单体架构地址：[https://gitee.com/bosen-once/jieyue](https://gitee.com/bosen-once/jieyue)

- 微服务架构地址：[https://gitee.com/bosen-once/jieyue-spring-cloud](https://gitee.com/bosen-once/jieyue-spring-cloud)

<br/>

# 快速体验

**用户模块：**[http://119.91.222.184/user/login](http://119.91.222.184)（账号-密码：bosen_once@163.com-123456）

**商户模块：**[http://119.91.222.184/merchant/login](http://119.91.222.184/merchant/login)（账号-密码：warrior@qq.com-123456）

**后台模块：**[http://119.91.222.184/admin/login](http://119.91.222.184/admin/login)（账号-密码：bosen_once@163.com-123456）


<br/>

# 技术选型
|  技术   | 版本  | 端口 | 说明 |
|  ----  | ----  |  ----  | ----  |
| Tomcat | 8.5.0 | 80 | Web服务器 |
| MySQL| 5.7.16 | 3306 | 关系型数据库 |
| Redis | 2.8.21 | 6379 | 缓存框架框架、主要用于主页页面缓存 |
| RabbitMQ | 3.7.8 | 5672、15672 | 消息中间件、用于群发消息的队列处理 |
| Elasticsearch | 7.7.0 | 9200、9300 | 搜索引擎、用于搜索商品的关键字查找 |
| SpringBoot | 2.3.5 | - | MVC核心框架 |
| Mybatis | 3.5.0 | - | ORM框架 |
| Maven | 3.3.9 | - | 用于构建springboot项目 |
| wxpay-sdk | - | - | 微信支付工具包 |

---

# 架构图
![](https://img-blog.csdnimg.cn/a8b5180113ac4f6eb42bbce6064cb004.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NzYwMDg4MA==,size_16,color_FFFFFF,t_70#pic_center)

---

# 各模块的功能
### 用户模块
|  页面   | 主要功能  | 
|  ----  | ----  |  
| 登录页面 | 登录、注册账号、发送邮箱验证码 。|
| 商城主页 | 登录、退出登录、随机推送商品、随机推送商家、推送热卖商品、推送新出的商品、展示商城广告海报；并且以上**商品信息在请求过一次MySQL后，将会保存在Redis缓存中十分钟**，刷新页面时会从Redis中获取，不会再次访问MySQL，以达到减轻MySQL压力的目的。 |
| 我的账户 | 显示登录用户的头像、昵称等信息；用户可通过该页面对自己的头像、昵称、密码进行修改。 |
| 系统消息 | 显示网站后台管理员发送的群消息、用户可对消息进行删除操作。|
| 购买记录 | 显示用户购买商品后的详细订单记录；若存在未支付的订单，点击后可跳转至支付页面。 |
| 购物车 | 显示用户的购物车信息、用户可以移出商品、添加商品、批量购买商品。 |
| 商品详情 | 显示商品的名称、价格、介绍、库存、上下架情况、图片；显示商品的用户评价，若存在评价是该登录用户本人所发表的，用户还可对其进行删除评论的操作；若商品已经下架或用户购买数量超过库存数量，将无法进行购买操作。|
| 店铺页面 | 显示商户下的商品、随即推送该商户下的商品；若商户被停用，商户页面将会有停用的字样，并且属于该商户的商品都将会被下架。  |
| 搜索结果 | 当用户通过关键词搜索时，将会通过Elasticsearch搜索引擎进行模糊搜索将符合条件的商品作为搜索结果返回给商户，商户通过点击即可跳转至相应的商品详情页面。  |
| 下单页面 | 当用户在该页面下填好必填和选填的订单信息后，选择微信支付或支付宝支付（暂时只支持微信支付）后，即可生成订单，并且跳转至支付页面。  |
| 支付页面 | 支付页面会显示支付二维码供用户扫码支付，页面每500ms异步检查订单是否已经完成支付，当用户支付成功后，将会提示用户支付成功，并跳转至用户的购买记录页面。  |

### 商户模块
|  页面   | 主要功能  | 
|  ----  | ----  |
| 登录页面 | 登录、注册账号、发送邮箱验证码 。|
| 商户主页 | 退出登录、显示商户盈利、商品评论总数、会员总数、订单成交量（不包含未支付订单）、曲线图动态展示（暂未实现）。 |
| 店铺相关 | 设置商户头像、店铺广告海报（供商城主页随机向用户推送）。 |
| 商品相关 | 商品的添加、删除、修改、上下架。 |
| 优惠相关 | 优惠卷功能（暂未实现） |
| 会员相关 | 查看会员相关信息。 |
| 订单相关 | 查看有关店铺的订单信息，可以筛选已支付或未支付的订单，并且支持通过订单号为关键字搜索订单。  |


### 后台模块
|  页面   | 主要功能  | 
|  ----  | ----  |
| 登录页面 | 登录、注册账号、发送邮箱验证码 。|
| 后台主页 | 退出登录、显示网站盈利、商品的总数、会员总数、订单成交量（不包含未支付订单）、曲线图动态展示（暂未实现）。 |
| 广告设置 | 设置商城主页需要向用户推送的广告海报。 |
| 订单列表 | 显示网站的所有订单，可以筛选已支付或未支付的订单，并且支持通过订单号为关键字搜索订单。  |
| 商户相关 | 显示商户的基本信息、头像、名称、邮箱、状态等；并且可以对其状态和费率进行修改，或删除该商户（停用商户时，商户的所有商品都将会被下架，并且其账号无法在商户模块登录；启用商户时，系统将会发送通知邮件告知该商户，账号已启用；删除商户时，属于商户的所有商品也将会被删除！）。 |
| 会员相关 | 查看会员相关信息。 |
| 权限管理 | 后台权限管理使用RBAC思想，即权限管理分为用户、角色、权限三大块，每一个管理员都是一个用户，并且都需要赋予一个角色，这个角色拥有什么权限，由最高权限的管理员进行设置（其中：**最高权限的管理员默认为id为1 的root，该管理员不受RBAC权限的控制**，且其他管理员也无权对其进行任何的操作）。 |
| 群发消息 | 群发消息给网站用户时，考虑到项目正式运行后，用户量会激增，若群发消息采用同步发送机制，后台管理员会在发送群消息时非常头疼，因此本网站采用RabbitMQ作为消息队列，对群发消息进行异步处理，提高后台管理效率。 |

<br/>

# 相关截图

---

### 用户模块
![](https://img-blog.csdnimg.cn/6c7f1b9a258240a8b05aa4b40ca74bae.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NzYwMDg4MA==,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/5f9ef3b9f62b43538e85acb66a618d08.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NzYwMDg4MA==,size_16,color_FFFFFF,t_70)
![](https://img-blog.csdnimg.cn/c75626e3022e46488de8e535c95c12e2.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NzYwMDg4MA==,size_16,color_FFFFFF,t_70)
![](https://img-blog.csdnimg.cn/b512ac173ddd495d8173995a6c40ee32.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBAQm9zZW7nmoTmioDmnK_liIbkuqvmoIg=,size_20,color_FFFFFF,t_70,g_se,x_16)
![](https://img-blog.csdnimg.cn/a22f2fb9721d457980a2d0bbbaec1e1c.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBAQm9zZW7nmoTmioDmnK_liIbkuqvmoIg=,size_20,color_FFFFFF,t_70,g_se,x_16)
![](https://img-blog.csdnimg.cn/c55768361ac44befa81bef0df1ebeabd.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NzYwMDg4MA==,size_16,color_FFFFFF,t_70)
![](https://img-blog.csdnimg.cn/d17e1a1ccdca4cc789344a4f935fada1.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NzYwMDg4MA==,size_16,color_FFFFFF,t_70)
![](https://img-blog.csdnimg.cn/221242677c5b4617b5803b363a8c0d20.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBAQm9zZW7nmoTmioDmnK_liIbkuqvmoIg=,size_20,color_FFFFFF,t_70,g_se,x_16)

---

### 商户模块
![](https://img-blog.csdnimg.cn/e5135da2d0e14220ad4e246bb943e864.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NzYwMDg4MA==,size_16,color_FFFFFF,t_70)
![](https://img-blog.csdnimg.cn/c03ef945cc0646558d36a577e9e8500c.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NzYwMDg4MA==,size_16,color_FFFFFF,t_70)
![](https://img-blog.csdnimg.cn/57e7fff63939477ea3e994f1009422f8.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NzYwMDg4MA==,size_16,color_FFFFFF,t_70)
![](https://img-blog.csdnimg.cn/4583c3fbc03a4fdb99882d58ebef0610.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NzYwMDg4MA==,size_16,color_FFFFFF,t_70)

---

### 后台模块
![](https://img-blog.csdnimg.cn/b2988d237f2a448e93c37c0fa8bbd757.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NzYwMDg4MA==,size_16,color_FFFFFF,t_70)
![](https://img-blog.csdnimg.cn/8b13321784554869ae08b82a7e6c79c2.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NzYwMDg4MA==,size_16,color_FFFFFF,t_70)
![](https://img-blog.csdnimg.cn/7e1bcaec6ede4d8c82f612088c175d09.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NzYwMDg4MA==,size_16,color_FFFFFF,t_70)
![](https://img-blog.csdnimg.cn/1d1b943375074dfc853ee0c6c15f060e.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NzYwMDg4MA==,size_16,color_FFFFFF,t_70)

<br/>

# 如何部署？
---
### 1.下载源码
将源码下载下来，使用Idea选择open的方式打开项目（第一次打开需要下载项目对应的第三方依赖，需要等待2~3分钟）

![](https://img-blog.csdnimg.cn/e5e5f9f23bb24fc286caa56564df7857.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA5LqR5Li26KiA,size_20,color_FFFFFF,t_70,g_se,x_16)
### 2.配置文件
修改项目配置文件application.yml，主要将服务器ip地址、MySQL、Redis连接信息按照自己的需求进行修改即可。
![](https://img-blog.csdnimg.cn/1281208680ff4461a48202f71629a285.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA5LqR5Li26KiA,size_20,color_FFFFFF,t_70,g_se,x_16)
![](https://img-blog.csdnimg.cn/0c0547bf787c4877b62557cd2d17e729.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA5LqR5Li26KiA,size_20,color_FFFFFF,t_70,g_se,x_16)
### 3.打包项目
填写好项目配置信息后，就可以开始打包项目了：按照下图所示的步骤依次执行，最后打包好的 **`jieyue.jar`** 将会存放在 **`target`** 目录中。
![](https://img-blog.csdnimg.cn/11e170bd1863418d99b38caebcb12c10.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LqR5Li26KiA,size_20,color_FFFFFF,t_70,g_se,x_16)


### 4.安装Docker
docker的安装流程可参考下面这篇文章，笔者在这里就不再赘述了：**[https://www.runoob.com/docker/centos-docker-install.html](https://www.runoob.com/docker/centos-docker-install.html)**

<br/>

### 5.安装MySQL
5.1：执行如下命令拉取MySQL的Docker镜像
```bash
docker pull hub.c.163.com/library/mysql:latest
```
5.2：镜像拉取后，执行如下命令运行MySQL（注意：MYSQL_ROOT_PASSWORD中的密码需要与项目配置文件中的MySQL密码保持一致）
```bash
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -d hub.c.163.com/library/mysql:latest
```
5.3：MySQL运行成功后，需要对MySQL的数据进行初始化，这里笔者推荐使用可视化工具Navicat，填入对应的信息即可成功连接
![](https://img-blog.csdnimg.cn/3bc392ebdbf249dd93a765bcf8d41733.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA5LqR5Li26KiA,size_20,color_FFFFFF,t_70,g_se,x_16)
5.4：新建一个名为jieyue的数据库，创建成功后双击该数据库创立连接，接着右击，选择**运行SQL文件**，运行项目根目录下的 **`jieyue.sql`** 文件即可。
![](https://img-blog.csdnimg.cn/be8eccc8a0084db8a087d8287034cfc3.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA5LqR5Li26KiA,size_19,color_FFFFFF,t_70,g_se,x_16)
### 6.安装Redis
6.1：执行如下命令拉取Redis的Docker镜像
```bash
docker pull hub.c.163.com/library/redis:latest
```
6.2：执行如下命令运行Redis
```bash
docker run -d -p 6379:6379 --name redis hub.c.163.com/library/redis:latest
```
<br/>

### 7.安装OpenJDK
7.1：执行如下命令拉取OpenJDK的Docker镜像

```bash
docker pull hub.c.163.com/cloudndp/library/openjdk:8-jessie
```
7.2：在 **`jieyue.jar`** 包同一目录下创建 **`Dockerfile`** 文件，具体内容如下

```bash
FROM hub.c.163.com/cloudndp/library/openjdk:8-jessie

COPY jieyue.jar /jieyue.jar

CMD java -jar /jieyue.jar

EXPOSE 80
```
7.3：在 **`jieyue.jar`** 包同一目录下执行如下命令，创建镜像

```bash
docker build -t jieyue-image .
```
7.4：最后运行容器即可
```bash
docker run -d -p 80:80 --name jieyue jieyue-image
```
7.5：使用如下命令可查看项目运行日志（若有如下字眼出现，则表明容器运行成功）
```bash
docker logs jieyue
```
![](https://img-blog.csdnimg.cn/1be4eb72a24a449d893a9d973310b073.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LqR5Li26KiA,size_20,color_FFFFFF,t_70,g_se,x_16)

7.6：成功运行后，访问配置文件中填写的服务器ip地址即可（如：笔者这里填写的是199.91.222.184，访问[http://119.91.222.184/](http://119.91.222.184/)，就可以看到网站的主页）至此，捷阅网已成功部署~~！！

<br/>

# 有关网站制作者
---

**个 人 主 页： [云丶言](https://bosen-once.gitee.io/)**
![](https://img-blog.csdnimg.cn/c3f49c6533dc401b9d00bb3655f7e5fb.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA5LqR5Li26KiA,size_15,color_FFFFFF,t_70,g_se,x_16#pic_center)

---

**CSDN博客：[云丶言](https://blog.csdn.net/weixin_47600880)**
![](https://img-blog.csdnimg.cn/45557a1b39054cc6ae793079c8e4f178.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA5LqR5Li26KiA,size_15,color_FFFFFF,t_70,g_se,x_16#pic_center)

---

**微信公众号：云丶言**
![](https://img-blog.csdnimg.cn/37712c950812411fb261377b4cf1345d.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA5LqR5Li26KiA,size_15,color_FFFFFF,t_70,g_se,x_16#pic_center)

---