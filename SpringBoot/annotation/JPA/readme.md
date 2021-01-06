# Spring Data JPA

## 1. Bean

在 Entity Bean 中会用到的注解。

### 1.1. [Java Persistence API](https://docs.oracle.com/javaee/7/api/javax/persistence/package-summary.html)

Java Persistence API 定义了一系列的注解，将普通 Java 对象（POJO）映射到数据库表。

javax.persistence:

- Entity
- Table

#### @Entity

应用于实体类，指定某个类是一个实体。

可选元素：

| 修饰符和类型 | 可选元素和描述 |
| --- | --- |
| String | **name** <br> (可选)实体名 |

#### @Table

为被注解的实体指定「主表」，其他表通过 [SecondaryTable]() 或 [SecondaryTables]() 指明。

可选元素：

<table>
<thead>
<tr>
<th>修饰符和类型</th><th>可选元素和描述</th>
</tr>
</thead>
<tbody>
<tr>
<td>String</td>
<td><pre>
<strong>name</strong>
(可选)表的名字。默认实体名。
</pre></td>
</tr>
<tr>
<td>String</td>
<td><pre>
<strong>schema</strong>
(可选)
</pre></td>
</tr>
<tr>
<td></td>
<td></td>
</tr>
<tr>
<td></td>
<td></td>
</tr>
<tr>
<td></td>
<td></td>
</tr>
</tbody>
</table>

## 1.1. @EnableJpaRepositories

用于取代 xml 形式的配置文件。

### 1.1.1. 简单配置

```java
@EnableJpaRepositories("com.spr.repository")
@EnableJpaRepositories({"com.cshtong.sample.repository", "com.cshtong.tower.repository"})
```

### 1.1.2. 单值和多组值配置方式

### 1.1.3. 完整的 @EnableJpaRepositories 注解

```java
@EnableJpaRepositories(
    basePackages = {},
    basePackageClasses = {},
    includeFilters = {},
    excludeFilters = {},
    repositoryImplementationPostfix = "Impl",
    namedQueriesLocation = "",//META-INF/jpa-named-queries.properties
    queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND, //QueryLookupStrategy.Key.x
    repositoryFactoryBeanClass = JpaRepositoryFactoryBean.class, //class
    entityManagerFactoryRef = "entityManagerFactory",
    transactionManagerRef = "transactionManager",
    considerNestedRepositories = false, 
    enableDefaultTransactions = true
)
```

- basePackages  
  扫描

## 2. @EntityScan

用来发现指定包及其子包的 Entity 定义。

```java
@EntityScan("com.demo.domain.*")
```

必须覆盖所有被 Repository 使用的 Entity，否则报错

```diff
- Not a managed type: com.demo.domain.Customer
```

## 属性

### @Column

将属性或 field 映射指定的列。

```java
// Example 1:
@Column(name="DESC", nullable=false, length=512)
public String getDescription() { return description; }

// Example 2:
@Column(name="DESC",
        columnDefinition="CLOB NOT NULL",
        table="EMP_DETAIL")
@Lob
public String getDescription() { return description; }

// Example 3:
@Column(name="ORDER_COST", updatable=false, precision=12, scale=2)
public BigDecimal getCost() { return cost; }
```

元素：

- `name` — 列名，默认属性或 field 名。
- `unique`
- `nullable` — 默认 true。
- `insertable`


### @Id

设定主键。

### @GeneratedValue

只支持简单主键，不支持派生主键。

```java
// Example 1:

@Id
@GeneratedValue(strategy=SEQUENCE, generator="CUST_SEQ")
@Column(name="CUST_ID")
public Long getId() { return id; }

// Example 2:

@Id
@GeneratedValue(strategy=TABLE, generator="CUST_GEN")
@Column(name="CUST_ID")
Long id;
```

[GenerationType](https://docs.oracle.com/javaee/7/api/javax/persistence/GenerationType.html):

- TABLE
- SEQUENCE
- IDENTITY
- AUTO

### @Temporal

只能用于 `java.util.Date` 和 `java.util.Calendar`。

[TemporalType](https://docs.oracle.com/javaee/7/api/javax/persistence/TemporalType.html):

- `DATE` — 映射 `java.sql.Date`
- `TIME` — 映射 `java.sql.Time`
- `TIMESTAMP` — 映射 `java.sql.Timestamp`
