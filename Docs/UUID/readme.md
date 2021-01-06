# [UUID](https://docs.oracle.com/javase/8/docs/api/java/util/UUID.html)

通用唯一识别码（Universally Unique Identifier）。

<details>
<summray>References</summray>

- [UUID | javatpoint](https://www.javatpoint.com/java-uuid)
- [UUID是如何保证唯一性的？ | 知乎](https://www.zhihu.com/question/34876910)

<details>

UUID 结构：

![uuid.png](https://gitlab.com/nonelittlesong/res/-/raw/master/imgs/java/java-uuid.png)

## 1. UUID 的类型

- 基于时间的 UUID（version 1）
- DCE(Distribute Computing Environment) 安全 UUID（version 2）
- 基于名称的 UUID（version 3 和 5）
- 随机生成的 UUID（version 4）

### 1.1. Version 1

通过计算当前时间戳、随机数和 MAC 得到。

### 1.2. Version 2

和 Version 1 算法相同，但会把前 4 位换为 POSIX 的 UID 或 GID。

### 1.3. Version 3 and 5

>Java 不提供 version 5 的实现

使用命名空间和名称的哈希值生成 UUID。如，  
DNS(域名系统)，URLs 和 OIDs(Object Identifiers) 等。

区别是使用的哈希算法不同：

- Version 3 使用 MD5(128-bits) 算法
- Version 5 使用 SHA-1(160-bits) 算法

### 1.4. Version 4

使用随机数作为源。使用不可预测的值作为种子生成随机数，减少碰撞的几率。

## 2. UUID 变量

- `0` — 兼容 NCS
- `2` — Leach-Salz
- `6` — 兼容微软
- `7` — 保留

![version-variant.png](https://gitlab.com/nonelittlesong/res/-/raw/44709c4d90cf0513a620f4fe086abb9ca8e446d2/imgs/java/java-uuid2.png)

| 变量 | R 的范围 |
| --- | --- |
| 0 | 0 - 7 |
| 2 | 8 - b |
| 6 | c , d |
| 7 | e , f |

## 应用

- 网络应用的 session ID
- 交易 ID
- 随机的文件名
- 数据库表的主键