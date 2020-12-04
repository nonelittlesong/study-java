# [Ecplise](https://www.eclipse.org/downloads/packages/)

**系统环境**

- Ubuntu 16.04
- JDK  
  dev  — jdk     1.8.0_131  
  proc — openjdk 1.8.0_191
- [Gradle 5.4.1](https://gradle.org/releases/)
- Eclipse 4.6.3（Neon）
- STS (Spring Tools Suite)  
  `http://download.springsource.com/release/TOOLS/update/3.8.4.RELEASE/e4.6/springsource-tool-suite-3.8.4.RELEASE-e4.6.3-updatesite.zip`
- Buildship 2.x  
  `https://download.eclipse.org/buildship/updates/e46/releases/2.x`
- Spring Boot 2.1.2.RELEASE

## 安装 Gradle 5.4.1

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

**编辑 `~/.gradle/gradle.properties`，注释掉默认 proxy：**

```
···
#Mon Sep 03 09:54:27 CST 2018
#systemProp.https.proxyPort=46559
#systemProp.http.proxyHost=127.0.0.1
#systemProp.https.proxyHost=127.0.0.1
#systemProp.http.proxyPort=46559
```

否则，构建 Gradle 项目时无法正常连接网路

## 配置 JRE

单击 <kbd>Window</kbd> → <kbd>Preferences</kbd> → <kbd>Java</kbd> → <kbd>Installed JREs</kbd>，打开 JRE 配置面板

如果正常配置了 JAVA 系统环境，会有一个默认的 JRE。

具体配置，参考[Eclipse 如何配置 JDK - CSDN](https://blog.csdn.net/a754315344/article/details/98033274)。

## 配置 Gradle

单击 <kbd>Window</kbd> → <kbd>Preferences</kbd> → <kbd>Gradle</kbd>，打开 Gradle 配置面板

**Gradle distribution**  

选择 `Local installation directory`，例如 `/opt/gradle/gradle-5.4.1`。

**Gradle User Home**

Linux 系统 `$HOME` 目录下的 `.gradle` 文件夹，例如 `/home/song/.gradle`。

:warning: 不要选择 `Offline Mode`！:warning:
