
<h1 align=center>Java底层实现Map映射</h1>
<h3 align=right>基于链表和二分搜索树</h3>
<div align="center">
<image src="https://img.shields.io/badge/Github-LiYangSir-brightgreen">
<image src="https://img.shields.io/badge/author-teaUrn-green">
<image src="https://img.shields.io/badge/Language-Java-orange">
<image src="https://img.shields.io/badge/Version-1.0-blue">
</div>

------

[TOC]

## 1、什么是映射

&emsp;&emsp;无论是集合还是映射，都是来源于数学当中，在数学领域映射指两个元素的集之间元素相互“对应”的关系。映射也分很多种，满射，单射，一一映射等三种情况，但是在计算机领域映射只是单指一一映射。我觉得着这种关系更适合叫python中的**字典**。

## 2、映射类的实现——基于链表

&emsp;&emsp;同栈和队列相同，我们都是基于一些其他的数据结构来封装我们的类。所以我们需要涉及集映射的接口。由于我们之前已经封装好了链表底层，具体的函数方法可以查看[LinkedList链表](/LinkedList/README.md)这篇文章。

和映射一样我们仍需要设计映射的接口函数。
**程序实现：**
```java
public interface Map<K, V> {
    void add(K key, V value);
    V remove(K key);
    boolean contains(K key);
    V get(K key);
    void set(K key, V newValue);
    int getSize();
    boolean isEmpty();
}
```

### 2.1、增加元素

这里我们为了更好的获得节点信息，我们引入getNode函数。之所以这样设计是因为，在映射中添加或者删除元素，都需要先去判断键值是否存在。所以引入getNode函数保证了我们类的整洁性。
```java
private Node getNode(K key) {
    Node cur = dummyNode.next;
    while (cur.next != null) {
        if (cur.key.equals(key))
            return cur;
        cur = cur.next;
    }
    return null;
}

@Override
public void add(K key, V value) {
    Node node = getNode(key);   //获取key所对应的节点
    if (node == null) {  //不存在节点则新建节点信息
        dummyNode.next = new Node(key, value, dummyNode.next);
        size++;
    } else  //存在则进行更改操作
        node.value = value;
}
```

### 2.2、删除元素

删除元素的过程大致分为二个步骤：找到元素然后删除元素。这里我们需要注意的是，我们并不能使用getNode这个函数，因为getNode定义到了待删除元素上，实际上我们知道，删除元素我们需要定位到待删除元素的前一个位置，所以我们这里需要做特殊处理。

**程序实现：**
```java
@Override
public V remove(K key) {
    Node pre = dummyNode;
    while (pre.next != null) { //找打待删除节点的前一个节点
        if (pre.next.key.equals(key))
            break;
        pre = pre.next; 
    }
    if (pre.next != null) {
        Node delNode = pre.next;
        pre.next = delNode.next;
        delNode.next = null;
        size--;
        return delNode.value;
    }
    return null;
}
```

### 2.3、改变元素
**程序实现：**

这里有的小伙伴可能要问了，我们的增加函数不也能实现改变操作吗？直接add(key, newValue)不久可以吗？ 实际上不能这么操作。我们需要让用户明白，如果真的执行改变操作，前提是必须元素内包含元素。如果不包含此元素，则会报错。实际上，set才是真正意义上的改变操作。

```java
@Override
public void set(K key, V newValue) {
    Node node = getNode(key);
    if (node == null)
        throw new IllegalArgumentException(key + "doesn't exist");
    node.value = newValue;
}
```

### 2.4、查询操作

查询操作就比较简单了，直接使用我们之前写好的getNode函数即可。

**程序实现：**
```java
@Override
public boolean contains(K key) {
    return getNode(key) != null;
}

@Override
public V get(K key) {
    Node node = getNode(key);
    return node == null ? null : node.value;
}
```

## 3、映射类的实现——基于BST二分搜索树

这里的BSTMap的设计同BST设计很相似，具体的可以参考我[BST二分搜索树](/BST/README.md)这篇文章。BST节点存储的是一个元素，BSTMap存储的多存储一个value值，就只是多存储了一个value值。
```java
private class Node{  //内部类
    public K key;
    public V value;
    public  Node left, right;

    public Node(K key, V value){
        this.key = key;
        this.value = value;
        left = null;
        right = null;
    }
}
```
大家以增加元素为例。看下面的对比。

**BST增加元素程序实现：**
```java
public void add(E e) {
    root =  add(root, e);
}

private Node add(Node node, E e) {
    // 终止条件
    if (node == null){
        size++;
        return new Node(e);
    }
    // 开始递归
    if (e.compareTo(node.e) < 0)
        node.left = add(node.left, e);
    else if (e.compareTo(node.e) > 0)
        node.right = add(node.right, e);

    return node;
}
```

**BSTMap增加元素程序实现：**
```java
@Override
public void add(K key, V value) {
    add(root, key, value);
}

private Node add(Node node, K key, V value) {
    if (node == null){
        size++;
        return new Node(key, value);
    }
    if (key.compareTo(node.key) < 0)
        node.left = add(node.left, key, value);
    else if (key.compareTo(node.key) > 0)
        node.right = add(node.right, key, value);
    else
        node.key = key;
    return node;
}
```

上面的 e 对应着下面的 key 。就是加一个value。是不是超级简单。对于其他来说照着BST实现即可。我就不做多做陈述啦。具体的可以参考我[BST二分搜索树](/BST/README.md)这篇文章。

## 4、时间复杂度分析

||增加元素|删除元素|查询元素|
|----|---|---|---|
|链表|O(N)|O(N)|O(N)|
|二分搜索树|O(log(N))|O(log(N))|O(log(N))|

&emsp;&emsp;这里链表复杂度这么大的原因就在于getNode函数。我们需要先遍历一遍是否含有此元素，在进行操作。时间浪费在了遍历的操作上。

&emsp;&emsp;因此我们可以在表中看出来，二分搜索树的性能**远高于**链表。

## 最后

总的来说集合这种数据结构相对来说比较简单。

更多精彩内容，大家可以转到我的主页：[曲怪曲怪的主页](http://quguai.net:8090/)

或者关注我的微信公众号：**TeaUrn**

或者扫描下方二维码进行关注。里面有惊喜等你哦。

**源码地址**：可在公众号内回复 **数据结构与算法源码** 即可获得。

<img src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/%E5%85%AC%E4%BC%97%E5%8F%B7%E4%BA%8C%E7%BB%B4%E7%A0%81.jpg" width=40%>



