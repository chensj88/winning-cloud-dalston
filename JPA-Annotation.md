# JPA 注解

## @Entity

```sql
被Entity标注的实体类将会被JPA管理控制，在程序运行时，JPA会识别并映射到指定的数据库表
唯一参数name:指定实体类名称，默认为当前实体类的非限定名称。
若给了name属性值即@Entity(name="XXX")，则jpa在仓储层(数据层)进行自定义查询时，所查的表名应是XXX。
如：select s from XXX s
```

## @Table

```java
当你想生成的数据库表名与实体类名称不同时，使用 @Table(name="数据库表名"),与@Entity标注并列使用，置于实体
类声明语句之前
@Entity
@Table(name="t_student")
public class student{
  ...
}
```


@Table中的参数(不常用)

* catalog: 用于设置表所映射到的数据库的目录
* schema: 用于设置表所映射到的数据库的模式
* uniqueConstraints: 设置约束条件

## @Id

```java
@Id 用于实体类的一个属性或者属性对应的getter方法的标注，被标注的的属性将映射为数据库主键
```

## @GeneratedValue

```java
与@Id一同使用，用于标注主键的生成策略，通过 strategy 属性指定。默认是JPA自动选择合适的策略
在 javax.persistence.GenerationType 中定义了以下几种可供选择的策略：

- IDENTITY：采用数据库ID自增长的方式产生主键，Oracle 不支持这种方式。
- AUTO： JPA 自动选择合适的策略，是默认选项。
- SEQUENCE：通过序列产生主键，通过@SequenceGenerator标注指定序列名，MySQL 不支持这种方式。
- TABLE：通过表产生主键，框架借由表模拟序列产生主键，使用该策略更易于做数据库移植。
```

## @Basic
```
@Basic表示一个简单的属性到数据库表的字段的映射,对于没有任何标注的 getXxxx() 方法,默认即为@Basic(fetch=FetechType.EAGER)
@Basic参数：
  1. fetch 表示该属性的加载读取策略
    1.1 EAGER 主动抓取 （默认为EAGER）
    1.2 LAZY 延迟加载,只有用到该属性时才会去加载
  2. optional (默认为 true)
    表示该属性是否允许为null
```

## @Column
```
通常置于实体的属性声明之前，可与 @Id 标注一起使用
@Column参数：

  1. name: 指定映射到数据库中的字段名
  2. unique: 是否唯一，默认为false
  3. nullable: 是否允许为null，默认为true
  5. insertable: 是否允许插入，默认为true
  6. updatetable: 是否允许更新，默认为true
  7. columnDefinition: 指定该属性映射到数据库中的实际类型，通常是自动判断。
```
## @Transient

```
JPA会忽略该属性，不会映射到数据库中，即程序运行后数据库中将不会有该字段
```

## @Temporal

```
Java中没有定义 Date 类型的精度，而数据库中，表示时间类型的数据有 DATE,TIME,TIMESTAMP三种精度
  - @Temporal(TemporalType.DATE) 表示映射到数据库中的时间类型为 DATE，只有日期
  - @Temporal(TemporalType.TIME) 表示映射到数据库中的时间类型为 TIME，只有时间
  - @Temporal(TemporalType.TIMESTAMP) 表示映射到数据库中的时间类型为 TIMESTAMP,日期和时间都有
```

## @Embedded 和 @Embeddable

```
用于一个实体类要在多个不同的实体类中进行使用，而本身又不需要独立生成一个数据库表
```
网上有一份比较详细说明，可参考[链接](http://blog.csdn.net/lmy86263/article/details/52108130)

## @JoinColumn

```
定义表关联的外键字段名
常用参数有：
  1. name: 指定映射到数据库中的外键的字段名
  2. unique: 是否唯一，默认为false
  3. nullable: 是否允许为null，默认为true
  4. insertable: 是否允许插入，默认为true
  5. updatetable: 是否允许更新，默认为true
  6. columnDefinition: 指定该属性映射到数据库中的实际类型，通常是自动判断。
```

## @OneToOne

```java
参数：

targetEntity： 指定关联实体类型，默认为被注解的属性或方法所属的类
cascade： 级联操作策略
CascadeType.ALL 级联所有操作
CascadeType.PERSIST 级联新增
CascadeType.MERGE 级联归并更新
CascadeType.REMOVE 级联删除
CascadeType.REFRESH 级联刷新
CascadeType.DETACH 级联分离
fetch： fetch 表示该属性的加载读取策略 (默认值为 EAGER)
EAGER 主动抓取
LAZY 延迟加载,只有用到该属性时才会去加载
optional： 默认为true，关联字段是否为空
如果为false，则常与@JoinColumn一起使用
mappedBy： 指定关联关系，该参数只用于关联关系被拥有方
只用于双向关联@OneToOne,@OneToMany,@ManyToMany。而@ManyToOne中没有
@OneToOne(mappedBy = “xxx”)
表示xxx所对应的类为关系被拥有方，而关联的另一方为关系拥有方
关系拥有方：对应拥有外键的数据库表
关系被拥有方：对应主键被子表引用为外键的数据库表
orphanRemoval:默认值为false
判断是否自动删除与关系拥有方不存在联系的关系被拥有方(关系被拥有方的一个主键在关系拥有方中未被引用，
当jpa执行更新操作时，是否删除数据库中此主键所对应的一条记录，若为true则删除)
//单向 一对一
@Entity  
public class Emp{//员工
  @Id
  @GeneratedValue
  privte Integer eId;

  @Column(length = 40)
  private String empName;

  @OneToOne(cascade = CascadeType.ALL)
  private Identity identity;
  //get,set方法省略
}

@Entity
public class Identity{//身份证
  //...
}

//双向 一对一
@Entity  
public class Emp{
  @Id
  @GeneratedValue
  privte Integer eId;

  @Column(length = 40)
  private String empName;

  @OneToOne(cascade = CascadeType.ALL)
  private Identity identity;
  //get,set方法省略
}

@Entity
public class Identity{
  @Id
  @GeneratedValue
  privte Integer iId;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "identity")
  private Emp emp;
  //...
}
```
以上例子，双向一对一，Emp 为关系拥有方，Identity 为关系被拥有方。
执行spring-data-jpa新增操作时，如果通过Identity的数据访问层进行新增操作(IdentityRepository.save())
，Emp表和Identity表都有数据，但是不会设置这两条数据的关系，Emp表中的外键为null。
反之，以关系拥有方Emp的数据访问层进行新增操作(EmpRepository.save()),Emp表和Identity表都有数据，并且
设置了两条数据的关系，即Emp表中的外键也得到正确新增

## @ManyToOne、@OneToMany

多对一(也可叫一对多，只是前后表颠倒一下而已)，只有双向多对一时才用得到`@OneToMany`。多对一中多的一方必定是对应数据库中拥有外键的表，即是关系拥有方，`@ManyToOne`只用在多对一中代表多的一类中，因为`mappedBy`只用于关系被拥有方，所以`@ManyToOne`参数中不包含`mappedBy`。

`@ManyToOne`参数:

* targetEntity： 指定关联实体类型，默认为被注解的属性或方法所属的类

* cascade： 级联操作策略

  * CascadeType.ALL 级联所有操作

  * CascadeType.PERSIST 级联新增

  * CascadeType.MERGE 级联归并更新

  * CascadeType.REMOVE 级联删除

  * CascadeType.REFRESH 级联刷新

  * CascadeType.DETACH 级联分离
  
* fetch： fetch 表示该属性的加载读取策略(@ManyToOne 的默认值是 EAGER，@OneToMany 的默认值是 LAZY)
    * EAGER 主动抓取
    * LAZY 延迟加载,只有用到该属性时才会去加载
    
* optional： 默认为true，关联字段是否为空
         如果为false，则常与@JoinColumn一起使用

`@OneToMany`参数除上述以外还有：

* `mappedBy`: 指定关联关系，该参数只用于关联关系被拥有方
  只用于双向关联`@OneToOne`,`@OneToMany`,`@ManyToMany`。而`@ManyToOne`中没有
  `@OneToMany(mappedBy = “xxx”)`
  表示xxx所对应的类为关系被拥有方，而关联的另一方为关系拥有方
  * 关系拥有方：对应拥有外键的数据库表
  * 关系被拥有方：对应主键被子表引用为外键的数据库表
* `orphanRemoval`:默认值为false
  判断是否自动删除与关系拥有方不存在联系的关系被拥有方(关系被拥有方的一个主键在关系拥有方中未被引用，
  当jpa执行更新操作时，是否删除数据库中此主键所对应的一条记录，若为true则删除)
```java
//单向 多对一
@Entity
public class Emp{
  @Id
  @GeneratedValue
  privte Integer eId;

  @Column(length = 40)
  private String empName;

  @ManyToOne(cascade = CascadeType.ALL)
  private Dept dept;
  //...
}

@Entity
public class Dept{
  @Id
  @GeneratedValue
  privte Integer dId;

  @Column(length = 40)
  private String deptName;
  //...
}
```

```java
//双向 多对一
@Entity
public class Emp{
  @Id
  @GeneratedValue
  privte Integer eId;

  @Column(length = 40)
  private String empName;

  @ManyToOne(cascade = CascadeType.ALL)
  private Emp emp;
  //...
}

@Entity
public class Dept{
  @Id
  @GeneratedValue
  privte Integer dId;

  @Column(length = 40)
  private String deptName;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "emp")
  private List<Emp> emps;
  //...
}
```
> 无论双向关联还是单向关联，数据库中均会在Emp表中自动生成一个外键（dept_d_id）

## @ManyToMany

* targetEntity： 指定关联实体类型，默认为被注解的属性或方法所属的类
* cascade： 级联操作策略
  * CascadeType.ALL 级联所有操作
  * CascadeType.PERSIST 级联新增
  * CascadeType.MERGE 级联归并更新
  * CascadeType.REMOVE 级联删除
  * CascadeType.REFRESH 级联刷新
  * CascadeType.DETACH 级联分离
* fetch： fetch 表示该属性的加载读取策略 (默认值为 LAZY)
    * EAGER 主动抓取
    * LAZY 延迟加载,只有用到该属性时才会去加载
* mappedBy: 指定关联关系，该参数只用于关联关系被拥有方
        只用于双向关联`@OneToOne`,`@OneToMany`,`@ManyToMany`。而`@ManyToOne`中没有。
        @ManyToMany(mappedBy = “xxx”)
        表示xxx所对应的类为关系被拥有方，而关联的另一方为关系拥有方：
    * 关系拥有方：对应拥有外键的数据库表
    * 关系被拥有方：对应主键被子表引用为外键的数据库表

```java
//单向 多对多
@Entity
public class Student{
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Course> courses;
    //...
}

@Entity
public class Course{
  //...
}
//双向 多对多
@Entity
public class Student{
  @ManyToMany(cascade = CascadeType.ALL)
  private List<Course> courses;
  //...
}

@Entity
public class Course{
  @ManyToMany(cascade = CascadeType.ALL, mappedBy = "courses")
  private List<Student> students;
  //...
}
```

> 所有双向关联使用时需谨慎，查询时容易引起栈内存溢出，尽量使用单向关联

## @Enumerated

当实体类中有枚举类型的属性时，默认情况下自动生成的数据库表中对应的字段类型是枚举的索引值，是数字类型的，若希望数据库中存储的是枚举对应的String类型，在属性上加入`@Enumerated(EnumType.STRING)`注解即可。

```java
@Enumerated(EnumType.STRING)
@Column(nullable = true)

private RoleEnum role;
```