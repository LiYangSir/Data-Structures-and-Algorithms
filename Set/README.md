<h1 align=center>Set 集合</h1>
<h3 align=right>基于链表和二分搜索树</h3>
<div align="center">
<image src="https://img.shields.io/badge/Github-LiYangSir-brightgreen">
<image src="https://img.shields.io/badge/author-teaUrn-green">
<image src="https://img.shields.io/badge/Language-Java-orange">
<image src="https://img.shields.io/badge/Version-1.0-blue">
</div>

------

[TOC]

## 1、什么是集合

&emsp;&emsp;数学上定义为由一个或多个确定的元素所构成的整体。但是在计算机领域，集合被定义为由一个或多个**不同**的元素所构成的整体。也就是说一个集合里面的元素都是不同的。

&emsp;&emsp;这是由于这种特性，比较经典的就是用在语言统计上。查看一篇文章当中用了多少个词汇，进而判断用户阅读难度系数。

## 2、集合类的实现——基于链表

&emsp;&emsp;同栈和队列相同，我们都是基于一些其他的数据结构来封装我们的类。所以我们需要涉及集合的接口。由于我们之前已经封装好了链表底层，具体的函数方法可以查看[LinkedList链表](/LinkedList/README.md)这篇文章。

### 2.1、接口函数实现

&emsp;&emsp;具体的函数方法依然是增删改查四个操作。这里可以并没有改操作，由于我们并不涉及索引概念，所以就没有改操作。
**接口函数实现：**
```java
public interface Set<E> {
    void add(E e);
    void remove(E e);
    boolean contains(E e);
    int getSize();
    boolean isEmpty();
}
```
### 2.2、基本操作函数

&emsp;&emsp;由于我们之前已经封装好了链表底层，具体的函数方法可以查看[LinkedList链表](/LinkedList/README.md)这篇文章。

**程序实现：**

```java
@Override
public int getSize() {
    return linkedList.getSize();
}

@Override
public boolean isEmpty() {
    return linkedList.isEmpty();
}
```
### 2.2、增加元素

**程序实现：**
```java
@Override
public void add(E e) {
    if (!linkedList.contains(e))   //集合中没有该元素
        linkedList.addFirst(e);  //向链表头添加元素时间复杂度最低
}
```

### 2.3、删除元素

**程序实现：**
```java
@Override
public void remove(E e) {
    linkedList.removeElement(e);
}
```

### 2.4、查询元素

**程序实现：**
```java
@Override
public boolean contains(E e) {
    return linkedList.contains(e);
}

@Override
public int getSize() {
    return linkedList.getSize();
}

@Override
public boolean isEmpty() {
    return linkedList.isEmpty();
}
```

## 3、集合类的实现——基于二分搜索树

&emsp;&emsp;由于我们之前已经封装好了链表底层，具体的函数方法可以查看[ BST 二分搜索树 ](/BST/README.md)这篇文章。

### 3.1、基本操作函数

```java
@Override
public int getSize() {
    return bst.getSize();
}

@Override
public boolean isEmpty() {
    return bst.isEmpty();
}
```

### 3.2、增加元素

**程序实现：**
```java
@Override
public void add(E e) {
    bst.add(e);
}
```

### 3.3、删除元素

**程序实现：**
```java
@Override
public void remove(E e) {
    bst.remove(e);
}
```

### 3.4、查询元素

**程序实现：**
```java
@Override
public boolean contains(E e) {
    return bst.contains(e);
}
```

## 4、时间复杂度分析

&emsp;&emsp;我们从上面可以看出来，我们这几个方法都非常简介，这也正是由于我们底层封装的好。

||增加元素|删除元素|查询元素|
|----|---|---|---|
|链表|O(N)|O(N)|O(N)|
|二分搜索树|O(log(N))|O(log(N))|O(log(N))|

&emsp;&emsp;这里小伙伴可以会问，链表中添加元素在头部添加难道不应该是O(1)的复杂度吗，其实不是的，那是因为在集合这个数据结构当中，我们需要保证元素不重复，就要**先对链表进行遍历一遍**，所以复杂度也就是O(N)级别。

&emsp;&emsp;因此我们可以在表中看出来，底层是二分搜索树的性能**远高于**链表的实现。

## 最后

总的来说集合这种数据结构相对来说比较简单。

更多精彩内容，大家可以转到我的主页：[曲怪曲怪的主页](http://quguai.net:8090/)

或者关注我的微信公众号：**TeaUrn**

或者扫描下方二维码进行关注。里面有惊喜等你哦。

**源码地址**：可在公众号内回复 **数据结构与算法源码** 即可获得。

<img src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/%E5%85%AC%E4%BC%97%E5%8F%B7%E4%BA%8C%E7%BB%B4%E7%A0%81.jpg" width=40%>
