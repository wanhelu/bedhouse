# 在WSL中配置docker

参照<https://www.cnblogs.com/walker-lin/p/11214127.html> <https://www.cnblogs.com/fansys/articles/10831307.html>

## 安装docker本体

* 更新存储库

```
sudo apt-get update
```

* 允许apt通过https使用repository安装软件包

```
sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg-agent \
    software-properties-common
```

* 添加Docker官方GPG key

```
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
```

*验证key的指纹

```
sudo apt-key fingerprint 0EBFCD88
```

* 添加稳定版repository

```
sudo add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable"
sudo apt-get update
```

* 查看可获取的版本
  ```apt-cache madison docker-ce```
* 安装与操作系统相匹配的版本
  ```sudo apt-get install docker-ce=<VERSION_STRING>```
* 验证：

```
sudo docker --version
sudo docker run hello-world
```

## 适配WSL

* 以管理员身份启动WSL
* 安装cgroupfs-mount

```
sudo apt -y install cgroupfs-mount
```

* 每次启动时

```
sudo cgroupfs-mount
sudo service docker start
```

# Docker部署jar包运行

参照<https://blog.csdn.net/weixin_42054155/article/details/90815393>
无需配置expose项

## 相关命令

```
docker build -t my/demo .   //构建镜像
docker run -d --name demo -p 20321:20321 my/demo   //运行镜像
docker ps   //列出所有活动容器
docker ps -a  //列出所有容器
docker images  //列出所有镜像
docker stop 容器名 //停止容器
docker start 容器名 //启动容器
docker rm 容器名  //删除容器
docker rmi 镜像名  //删除镜像
docker logs --tail  500 -f 容器名 //查看日志  
```

# 连接数据库

## Mysql远程连接授权IP

参照<https://www.cnblogs.com/jesu/p/7405528.html>

## docker中连接宿主机数据库

参照<https://jingyan.baidu.com/article/e73e26c061b3bd24adb6a782.html>  
宿主机中使用ifconfig查看信息，使用docker0的ip来连接宿主机
