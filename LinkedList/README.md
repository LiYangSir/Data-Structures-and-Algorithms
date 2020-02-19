<h1 align=center><a href="https://github.com/LiYangSir/Data-Structures-and-Algorithms/tree/master/Array" target="_blank"> LinkedList 链表</a></h1>
<div align="center">
<image src="https://img.shields.io/badge/Github-LiYangSir-brightgreen">
<image src="https://img.shields.io/badge/author-teaUrn-green">
<image src="https://img.shields.io/badge/Language-Java-orange">
<image src="https://img.shields.io/badge/version-1.0-blue">
</div>

---------

## 1、什么是链表

链表，使用“链子”将数据组合起来，这里的**链子**指的就是引用或者指针。链子存储在哪里呢？节点（Node）中，我们把节点封装在类中。即

```java
class Node{
    E e;
    Node next;
}
```
也就形成了这样的数据结构
<div align=center>
<img src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95/LinkedList/%E5%8E%9F%E7%90%86.png" width="60%">
</div>

### 1.1、同数组的区别

上面就是链表，同动态数组不同，链表才算是真正的**动态**。

动态数组
+ 优点：具备随机访问能力，直接根据地址寻址一步完成
+ 缺点：不具备真正的动态性，底层依然依靠固定容量的数组

链表
+ 优点：具有真正的动态性，完全根据用户的数据建立节点
+ 缺点：缺少了随机访问能力，不适合应对查询元素频繁的场景

### 1.2、节点的实现
由于设计链表类的时候，节点作为链表存储数据的核心。那么节点类就要作为链表类的子类，所以具体的Java实现如下：

```java
public class LinkedList<E> {
    private class Node{  //
        public Node(E e, Node node) {
            this.e = e;
            this.next = node;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }

        E e;
        Node next;
    }
}
```

## 2、链表的方法实现

几乎所有的数据结构的实现都避免不了增删改查四中基本操作，下面分别实现一下。

**写在前面：** 为了更优化添加元素或者删除中对头结点的另外操作，这里我引入虚拟头结点（dummyNode）。

### 2.1、增加元素

增加元素最基本的操作就是向某一个“索引”位置添加元素。

**步骤：**
1. 找到要插入位置的前一个节点pre
1. 新增节点，并指向pre节点的下一个节点
2. 将pre节点指向新的节点

<div align=center>
<img src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95/LinkedList/%E6%B7%BB%E5%8A%A0%E5%85%83%E7%B4%A0.png" width=80%>
</div>
由于我们需要索引到待添加元素的前一个位置，所以我们起始的节点放在虚拟头结点上。这样就可以循环index找到待添加位置的前一个位置。

**程序实现**
```java
public void add(int index, E e) {
    if (index < 0 || index > size)  //越界判断
        throw new IllegalArgumentException("add is error, index need index < 0 || index > size ");
    table pre = dummyHead;  //起始从虚头开始
    for (int i = 0; i < index; ++i)   //找到插入位置的前一个节点
        pre = pre.next;      
    pre.next = new table(e, pre.next);
    size++;
}
```

### 2.1、删除元素

**步骤：**
1. 找到索引位置的前一个节点pre
2. pre节点指向pre节点的下一节点的下一个节点
3. 断开待删除节点与其下一个节点的连接，删除的节点会被内存管理机制自动销毁

<div align=center>
<img src=https://markdown-liyang.oss-cn-beijing.aliyuncs.com/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95/LinkedList/%E5%88%A0%E9%99%A4%E5%85%83%E7%B4%A0.png width=70%>
</div>
和添加元素相同，我们需要先找到待删除位置的前一个位置，所以依然以虚拟头节点作为起始节点。

**程序实现：**
```java
public E remove(int index){
    if (index < 0 || index > size)
        throw new IllegalArgumentException("set is error, index need index < 0 || index > size ");
    table pre = dummyHead;
    for (int i = 0; i < index; ++i)  //找到待删除节点的前一个节点
        pre = pre.next;
    size--;
    table retNode = pre.next; //保存待删除节点，后面还要返回
    pre.next = retNode.next;
    retNode.next = null;  //断开连接
    return retNode.e;
}
```
**写在后面：**
在某些场景中，我们并不需要保留这个节点，为了使代码更加简洁易懂我们可以做以下处理。

这种可以应用在LeetCode中，因为在代码执行完成以后，所有都会被销毁。

```java
public void remove(int index){
    if (index < 0 || index > size)
        throw new IllegalArgumentException("set is error, index need index < 0 || index > size ");
    table pre = dummyHead;
    for (int i = 0; i < index; ++i)
        pre = pre.next;
    size--;
    pre.next = pre.next.next;  //直接删除
}
```

### 2.3、改变元素

改变元素就比较简单，直接找到位置进行更改元素就可以。同增加元素和删除元素不同，改变元素不需找到前一个位置，所以起始位置就是虚拟头节点的下一个位置。

**程序实现：**
```java
public void set(int index, E e) {
    if (index < 0 || index > size)
        throw new IllegalArgumentException("set is error, index need index < 0 || index > size ");
    table pre = dummyHead.next;  //起始位置
    for (int i = 0; i < index; ++i)  //找到元素
        pre = pre.next;
    pre.e = e;   //改变元素
}
```

### 2.4、查询元素

和其他数据结构相同，包含三种查询。
1. find(E e) 查询元素e所在位置
2. contains(E e) 查询是否包含元素，同find代码逻辑相同
3. get(int index) 查询index位置上的元素

```java
/**    是否包含元素    **/
public boolean contains(E e) {
    table currentTable = dummyHead.next;
    while (currentTable != null) {
        if (currentTable.e == e)
            return true;
        currentTable = currentTable.next;
    }
    return false;
}
/**    查找元素所在位置    **/
public int find(E e) {
    int index = 0;
    table currentTable = dummyHead.next;
    while (currentTable != null) {
        if (currentTable.e == e)
            return index;
        index++;
        currentTable = currentTable.next;
    }
    return -1;
}
/**      查询index位置的元素    **/
    public E get(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("get is error, index need index < 0 || index > size ");
        table pre = dummyHead.next;
        for (int i = 0; i < index; ++i)
            pre = pre.next;
        return pre.e;
    }
```

## 3、时间复杂度分析

对于链表来说，最大的优势在于其**动态性**，所以在头部进行的所有操作时间复杂度最低O(1)级别，其他位置的操作均与索引位置位置有关（因为要先找到要操作的位置）。由此特性，我们可以清楚的看出，**用链表实现栈**的数据结构再合适不过啦。

## 最后

更多精彩内容，大家可以转到我的主页：[曲怪曲怪](http://quguaiquguai.cn:8090/)

关注我的微信公众号：**TeaUrn**
或者扫描下方二维码进行关注。我在那里等你哦！
<img src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/%E5%85%AC%E4%BC%97%E5%8F%B7%E4%BA%8C%E7%BB%B4%E7%A0%81.jpg" width=40%>