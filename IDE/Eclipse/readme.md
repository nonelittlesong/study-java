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

## 2. 配置 JRE

单击 <kbd>Window</kbd> → <kbd>Preferences</kbd> → <kbd>Java</kbd> → <kbd>Installed JREs</kbd>，打开 JRE 配置面板

如果正常配置了 JAVA 系统环境，会有一个默认的 JRE。

具体配置，参考[Eclipse 如何配置 JDK - CSDN](https://blog.csdn.net/a754315344/article/details/98033274)。

## 3. 配置 Gradle

单击 <kbd>Window</kbd> → <kbd>Preferences</kbd> → <kbd>Gradle</kbd>，打开 Gradle 配置面板

**Gradle distribution**  

选择 `Local installation directory`，例如 `/opt/gradle/gradle-5.4.1`。

**Gradle User Home**

Linux 系统 `$HOME` 目录下的 `.gradle` 文件夹，例如 `/home/song/.gradle`。

:warning: 不要选择 `Offline Mode`！:warning:

## 4. Spring Tools 4 for Eclipse

**问题**  
无法在 Ubuntu 16.04 的系统下正常运行，无法打开文件浏览器

```diff
org.eclipse.m2e.logback.configuration: The org.eclipse.m2e.logback.configuration bundle was activated before the state location was initialized.  Will retry after the state location is initialized.
***WARNING: GTK+ version too old (micro mismatch)
***WARNING: SWT requires GTK 3.20.0
***WARNING: Detected: 3.18.9

(Spring Tool Suite 4:6861): Gtk-WARNING **: Theme parsing error: <data>:7:18: '-gtk-key-bindings' is not a valid property name

(Spring Tool Suite 4:6861): Gtk-WARNING **: Theme parsing error: <data>:31:11: 'min-height' is not a valid property name
org.eclipse.swt.SWTError: No more handles
    at org.eclipse.swt.SWT.error(SWT.java:4895)
    at org.eclipse.swt.SWT.error(SWT.java:4784)
    ···
    at org.eclipse.equinox.launcher.Main.basicRun(Main.java:594)
    at org.eclipse.equinox.launcher.Main.run(Main.java:1465)
    at org.eclipse.equinox.launcher.Main.main(Main.java:1438)
```

**原因**  
可能是 [GTK](https://www.gtk.org/) 的版本不兼容（为了避免其他程序出现问题，没有选择更新 GTK）。

**Solutions**(类似问题的解决方案，仅供参考)

- [rafcon 及 gtk+-3.20.0 安装指南 | CSDN](https://blog.csdn.net/lmffcl/article/details/86309180?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1.control)
- [org.eclipse.swt.SWTError while running eclipse on ubuntu | StackExchange](https://unix.stackexchange.com/questions/94497/org-eclipse-swt-swterror-no-more-handles-gtk-init-check-failed-while-runnin)
- [查看 Eclipse 运行的 GTK 版本 | Eclipse Wiki](https://wiki.eclipse.org/SWT/Devel/Gtk/GtkVersion)

### 4.1. 其他版本的 STS

- [STSs | Pivotal](https://github.com/spring-projects/toolsuite-distribution/wiki/Spring-Tool-Suite-3)
- [STS for e4.6](http://download.springsource.com/release/TOOLS/update/3.8.4.RELEASE/e4.6/springsource-tool-suite-3.8.4.RELEASE-e4.6.3-updatesite.zip)
- [STSs | OSChina](https://my.oschina.net/openoschina/blog/3013939)

## 5. 查看 Ecplise 运行的 GTK 版本

### 5.1. 通过配置

在 Ecplise 中  
Help -> About -> Installation Details -> Configuration Tab.
寻找 `gtk.version`：  

GTK3 中：
```
org.eclipse.swt.internal.gtk.version=3.20.3
```

GTK2 中：  
```
org.eclipse.swt.internal.gtk.version=2.24.30
```

如果啥都找不到，（很有可能）运行的是 GTK2。

### 5.2. GtkInspector

按 <kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>D</kbd>，只能在 GTK3.14 以上版本使用。

### 5.3. 系统 GTK

在 Fedora 中

```
pkg-config --modversion gtk+-3.0 
```

在 Ubuntu 中

```
dpkg -l libgtk2.0-0 libgtk-3-0
```

### 5.4. Eclipse Lint

- 如果是通过网站下载的 ecplise，lint 文件在 ecplise 可执行文件所处的目录下
- 如果是通过如 `yum/dnf` 下载，lint 文件在 `/etc/` 目录下

:warning: Lint 文件只能决定大版本 2 或 3，不能决定小版本！