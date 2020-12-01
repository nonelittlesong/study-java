# Spring Boot

## 1. Spring Tools 4 for Eclipse

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

### 1.1. 其他版本的 STS

- [STSs | Pivotal](https://github.com/spring-projects/toolsuite-distribution/wiki/Spring-Tool-Suite-3)
- [STS for e4.6](http://download.springsource.com/release/TOOLS/update/3.8.4.RELEASE/e4.6/springsource-tool-suite-3.8.4.RELEASE-e4.6.3-updatesite.zip)
- [STSs | OSChina](https://my.oschina.net/openoschina/blog/3013939)

## 2. 查看 Ecplise 运行的 GTK 版本

### 2.1. 通过配置

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

### 2.2. GtkInspector

按 <kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>D</kbd>，只能在 GTK3.14 以上版本使用。

### 2.3. 系统 GTK

在 Fedora 中

```
pkg-config --modversion gtk+-3.0 
```

在 Ubuntu 中

```
dpkg -l libgtk2.0-0 libgtk-3-0
```

### 2.4. Eclipse Lint

- 如果是通过网站下载的 ecplise，lint 文件在 ecplise 可执行文件所处的目录下
- 如果是通过如 `yum/dnf` 下载，lint 文件在 `/etc/` 目录下

:warning: Lint 文件只能决定大版本 2 或 3，不能决定小版本！

