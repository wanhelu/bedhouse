# bedhouse
实训项目  
本项目的GitHub地址<https://github.com/wanhelu/bedhouse>
## 项目说明
### 技术栈
#### 前端
Vue + Vue-Router + Vuex + Axios + ElementUI
#### 后端
springboot+mybatis
## 安装
### 安装IDEA和MYSQL
请自行查找教程  
另外强烈推荐DATAGRIP 相当好用
### 导入项目
#### 数据库配置
打开datagrip 连接数据库 新建一数据库名叫bedhouse 右键点击左侧栏中的bedhouse，选择运行sql脚本，选择此项目目录下的 bedhouse-server/sql/sql文件 导入成功后应当可以见到表结构
#### 项目配置
##### 在idea中克隆项目
```
git clone https://github.com/wanhelu/bedhouse.git
```
##### 配置maven
##### 配置NPM
##### 修改配置文件
修改 bedhouse-server/src/main/resources/application.properties中的内容  
修改 spring.datasource.username 和 spring.datasource.password 两项为自己的数据库用户名和密码
## 运行
### 启动后端
进入bedhouse-server目录 运行以下命令
```
mvn spring-boot:run
```
### 启动前端
进入bedhouse-client目录 运行以下命令
```
npm install // 安装依赖

npm run serve // 启动项目
```