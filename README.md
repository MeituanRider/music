# music
springboot+mybatis-plus+vue的音乐网站
该音乐网站使用了较新的技术栈。
## 项目截图

> 前台截图预览

![](https://tva1.sinaimg.cn/large/007S8ZIlly1geec0a2vd9j31c00u0n4z.jpg)<br/>

![](https://tva1.sinaimg.cn/large/007S8ZIlly1geec0qtdxrj31c00u07wj.jpg)<br/>

![](https://tva1.sinaimg.cn/large/007S8ZIlly1geec19x0e6j31c00u0npe.jpg)<br/>

![](https://tva1.sinaimg.cn/large/007S8ZIlly1geec1nmbt4j31c00u0hcf.jpg)<br/>

![](https://tva1.sinaimg.cn/large/007S8ZIlly1geec1yc0gkj31c00u0kjm.jpg)<br/>

![](https://tva1.sinaimg.cn/large/007S8ZIlly1geec29vvdtj31c00u0nok.jpg)<br/>

![](https://tva1.sinaimg.cn/large/007S8ZIlly1geec2ixqk1j31c00u0qf8.jpg)

<br/>

![](https://tva1.sinaimg.cn/large/007S8ZIlly1geec31i06gj31c00u0wtw.jpg)<br/>

![](https://tva1.sinaimg.cn/large/007S8ZIlly1geec3ozxt9j31c00u0qbv.jpg)<br/>

![](https://tva1.sinaimg.cn/large/007S8ZIlly1geec41r7onj31c00u047y.jpg)<br/>

> 后台截图预览

![](https://tva1.sinaimg.cn/large/006tNbRwly1g9hhhu4n7tj31c00u04qq.jpg)<br/>

![](https://tva1.sinaimg.cn/large/00831rSTly1gdj8jf3uusj31c00u0n5b.jpg)<br/>

![](https://tva1.sinaimg.cn/large/00831rSTly1gdie89mujrj31c00u07kx.jpg)<br/>

![](https://tva1.sinaimg.cn/large/00831rSTly1gdie8sox6uj31c00u01gb.jpg)<br/>

![](https://tva1.sinaimg.cn/large/00831rSTly1gdie9beckpj31c00u0qh9.jpg)<br/>

![](https://tva1.sinaimg.cn/large/00831rSTly1gdie9qq7yhj31c00u0ttq.jpg)<br/>
# 使用教程
1.下载网站依赖的歌曲及图片，将 data 夹里的文件直接放到 music-server 文件夹下。
2.数据库：将sql文件夹中的 tp_music.sql 文件导入数据库。
3.music-server：启动后端服务之前，有一些地方需要修改，先去 /music-website/music-server/src/main/resources 这个目录下的文件里修改自己的 spring.datasource.username 和 spring.datasource.password，并且修改下圈出来的的文件中 MyPicConfig 类下的 addResourceLocations方法中的路径，否则资源加载不了。
music-server 是本项目的后端，用于监听前端发来的请求，提供接口。music-client 和 music-manage 都是本项目的前端部分，前者是前台，后者是后台。运行时后端必须启动，两个前端项目可以都启动，也可以只启动其中一个，他们是独立的。

然后进入 music-server 文件夹，运行下面命令启动服务器

```js
// 方法一
./mvnw spring-boot:run

// 方法二
mvn spring-boot:run // 前提装了 maven
```

进入 music-client 文件夹，运行下面命令启动前台项目

```js
npm install // 安装依赖

npm run dev // 启动前台项目
```

进入 music-manage 文件夹，运行下面命令启动后台管理项目

```js
npm install // 安装依赖

npm run dev // 启动后台管理项目
```

<br/>
# 后端业务需求
1.管理员用户的登录(后期尝试shiro+md5对该系统进行改进)
2.歌曲收藏列表的增删改查
3.评论的增删改查
4.用户操作的增删改查以及头像图片等io更新文件操作
5.歌单列表增删改查操作，及歌单列表图片的io操作
6.对歌曲获取评分，以及进行评分操作(后期将评分放入redis中)
7.歌手进行增删改查，以及歌手图片更新
8.歌曲进行增删改查，以及上传歌曲的io操作
9.根据title，style进行筛选查找歌单等操作
10.定义个log切面对重要操作进行写入数据库
# 前端cli端与manager端
技术栈Vue + Vue-Router + Vuex + Axios +  ElementUI
首先感谢我的好兄弟帮我对vue中cli端的布局操作进行了很长的设置，manager
端是我个人模仿的element-admin。
# 项目阿里云服务器上线
服务器端安装docker pull自己需要的东西ngnix，mysql等，具体步骤可以参考how2j的docker教程简单易上手

欢迎各位的fork与star.
