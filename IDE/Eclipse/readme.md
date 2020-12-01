# [Ecplise](https://www.eclipse.org/downloads/packages/)

**系统环境**

- Ubuntu 16.04
- Eclipse 4.6.3
- JDK  
  dev  jdk     1.8.0_131  
  proc openjdk 1.8.0_191
- [gradle 5.4.1](https://gradle.org/releases/)
- springboot 2.1.2.RELEASE

## 1. 安装 Gradle 5.4.1

<details>
<summary>References</summary>

- [Installtion | Gradle](https://gradle.org/install/)
- [安装 Gradle | CSDN](https://blog.csdn.net/dreamfantacy/article/details/85253555)

</details>

解压可执行程序到指定文件夹：

```
$ sudo mkdir /opt/gradle
$ sudo unzip -d /opt/gradle Downloads/Installer/gradle-5.4.1-bin.zip
$ ls /opt/gradle/gradle-5.4.1
bin  getting-started.html  init.d  lib  LICENSE  media  NOTICE
```

配置环境路径：

```
vim ~/.profile # Ubuntu
```

在末尾添加：

```
PATH="$PATH:/opt/gradle/gradle-5.4.1/bin"
```

检查版本：

```
$ gradle -v

Welcome to Gradle 5.4.1!

Here are the highlights of this release:
 - Run builds with JDK12
···
```
