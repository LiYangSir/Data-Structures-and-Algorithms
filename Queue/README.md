<h1 align=center>Queue 队列</h1>
<div align="center">
<image src="https://img.shields.io/badge/Github-LiYangSir-brightgreen">
<image src="https://img.shields.io/badge/author-teaUrn-green">
<image src="https://img.shields.io/badge/Language-Java-orange">
<image src="https://img.shields.io/badge/Version-1.0-blue">
</div>

-----

## 1、什么是队列

同 Stack 一样，Queue队列也是一种线性结构，底层的实现几乎完全相同，只是套的“皮”不相同。这种数据结构有自己的独特的特性
Stack -> **先进后出，后进先出**
队列 -> **先进先出，后进后出**
从字面意思也不难理解，队列队列就是排队的含义。
<div align=center>
<img src=https://markdown-liyang.oss-cn-beijing.aliyuncs.com/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95/4.Queue%E9%98%9F%E5%88%97/%E7%BB%93%E6%9E%84.png width=30%>
</div>
根据队列的性质我们就可以设计队列的接口函数。

**接口函数程序：**
```java
public interface Queue<E> {
    int getSize();
    boolean isEmpty();
    void enqueue(E e);  //出队
    E dequeue();  //入队
    E getFront();  //查看队首元素
}
```
基于Queue接口函数我们就可以实现不同底层的队列结构，这本文中主要涉及**动态数组、链表**来实现队列结构。最后根据动态数组的实现改写，实现**循环队列**数据结构。

## 2、Queue数据结构实现——基于动态数组
主要基于动态数组进行封装，拘役动态数组包含什么函数，打开可以参考[动态数组](/Array/README.md)这一篇文章。

### 2.1、基本函数实现

```java
@Override
public int getSize() {   //获取队列数据大小
    return array.getSize();
}

public int getCapacity() {   //获取队列实际容量
    return array.getCapacity();
}

@Override
public boolean isEmpty() {  // 判断队列是否为空
    return array.isEmpty();
}
```

### 2.2 进出队列函数

对于底层为动态链表的队列，根据时间复杂度分析我们知道，在数组后面增加元素的时间复杂度最低O(1)级别。但是对于进出队列必须同时对头尾进行操作，所以无论以数组头作为队首还是以数组尾作为队首进出队列综合时间复杂度相同。

```java
/**     版本一   **/
@Override
public void enqueue(E e) {
    array.addLast(e);
}

@Override
public E dequeue() {
    return array.removeFirst();
}
```

```java
/**     版本二   **/
@Override
public void enqueue(E e) {
    array.addFirst(e);
}

@Override
public E dequeue() {
    return array.removeLast();
```
**写在后面：** 导致进出队列时间复杂度变高是由于向数组头添加或者删除元素。改进的思路就是制作循环队列，也就没有了删除或者增加元素导致的数组拷贝操作。后面循环队列会详细讲解。

### 2.3 查询操作
查询操作就是查看对首的元素。
```java
@Override
public E getFront() {
    if (isEmpty())
        throw new IllegalArgumentException("Cannot getFront from an empty queue");
    return head.e;
}
```

## 3、Queue数据结构实现——基于链表函数

和动态数组不同，底层没有掉用自己写的链表类，而是完全从零实现队列结构。

### 3.1、基本函数实现

```java
@Override
public int getSize() {
    return size;
}

@Override
public boolean isEmpty() {
    return size == 0;
}
```

### 3.2 进出队列函数

这里以链表头最为队首，链表尾作为队尾。我们知道，我们要是访问队尾节点需要遍历整个链表，这就大大增加了时间复杂度。对于队列结构，我们主要是对头尾进行操作，所以我们引入tail标记。用来标记队尾节点。这样我们就可以直接访问到队尾元素。head代表链表头，tail代表链表尾。**出队操作对应链表删除头结点，入队操作代表在链表尾增加元素。** 

这里也可以使用我们之前实现的 LinkedList 链表类， 其中的 removeFirst() 和 addLast() 函数。这里就是提示作者我们实现的方式有很多，不要仅局限于一种。但是我们也看出来制作封装链表库有多么重要，两个函数就是是实现的队列这种数据结构。很多数据结构都是可以基于我们实现的类进一步封装，将变得非常简单。

```java
@Override
public void enqueue(E e) {
    if (tail == null){
        tail = new Node(e);
        head = tail;               //第一元素
    }else{
        tail.next = new Node(e);  //增加元素导致tail标记向后移动一位
        tail = tail.next;
    }
    size++;                      //维护size
}

@Override
public E dequeue() {
    if (isEmpty())
        throw new IllegalArgumentException("Cannot dequeue from an empty queue");
    Node retNode = head;  //删除元素导致head标记向后移动一位
    head = head.next;
    retNode.next = null; // 断开连接，被Java自动回收机制回收
    if (head == null)
        tail = null;
    size--;            // 维护size
    return retNode.e;
}
```

### 3.3 查询操作
查询操作就变得十分简单，直接返回队首节点的元素值。
```java
@Override
public E getFront() {
    if (isEmpty())
        throw new IllegalArgumentException("Cannot getFront from an empty queue");
    return head.e;
}
```
## 4、LoopQueue循环队列的实现

顾名思义，这里的数组是一个循环数组。这里的循环并不是真的循环，而且一种狭义的循环。我们遍历数组末尾的下一元素的时候，自动将索引回到数组头的位置。其实就是遍历的时候，自增变为<div align=center><b>index = (index + 1) % size;</b></div>这样也就实现了索引自动循环。
这里同上面不同的是，出队操作并不需要数组重新回到索引为0的位置，而是保持不变。入队操作就是直接向数组后面添加元素，如果到达末尾，直接转向头进行添加。这里我们引入head和tail标记。
**入队操作步骤：**
向数组尾添加元素，索引位置为 
<div align=center><b>tail = (tail + 1) % size</b></div>

我们并不关注图中标记为 **蓝色的节点** 。因为我们实际的元素就是 **head -> tail** 其他的是啥我们并不关注。
<div align=center>
<img src=https://markdown-liyang.oss-cn-beijing.aliyuncs.com/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95/4.Queue%E9%98%9F%E5%88%97/%E5%85%A5%E9%98%9F.png width=90% alt=入队操作>
</div>
<br/>

**出队操作步骤：**
向数组头添加元素，索引位置为 
<div align=center><b>head = (head - 1) % size</b></div>
<div align=center>
<img src=https://markdown-liyang.oss-cn-beijing.aliyuncs.com/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95/4.Queue%E9%98%9F%E5%88%97/%E5%87%BA%E9%98%9F.png width=90% alt=出队操作>
</div>

**写在前面：** 我们不难注意到，**数组满**的时候正是head与tail相邻的时候，tail在前。为了维护循环性，我们表示为
<p align=center><b>tail == (head + 1) % size</b></p>

### 4.1、基本函数实现

```java
public int getCapacity() {  //判断容器是否为空
    return data.length - 1;
}

@Override
public int getSize() {  //获取队列实际数据含量
    return size;
}

@Override
public boolean isEmpty() {  //当头和尾重合即为空
    return head == tail;
}
```

### 4.2、进出队列函数

在前面我们已经写了，最主要的就是索引的维护。实现循环的操作，也就是取余操作。
```java
@Override
public void enqueue(E e) {
    if ((tail + 1) % data.length == head)  // 判断数组中是否已经存满
        resize(getCapacity() * 2);

    data[tail] = e;
    tail = (tail + 1) % data.length;  //tail向后自增
    size++;  // 维护size
}
```

```java
@Override
public E dequeue() {
    if (isEmpty()) {
        throw new IllegalArgumentException("Cannot dequeue from an empty queue");
    }
    E temp = data[head];
    data[head] = null;
    head = (head + 1) % data.length; //头节点向后移动一位
    size--;  // 维护size
    if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {  // 防止时间复杂度震荡并保持整除
        resize(getCapacity() / 2);
    }
    return temp;
}
```
对于resize函数我们就是将原来的数组拷贝至比原数组大以一倍的数组中。遍历方式就是从 head 遍历到 tail 。为了维护安全性，我们将resize函数设置为私有。
```java
private void resize(int newCapacity) {
    E[] newData = (E[]) new Object[newCapacity + 1];
    for (int i = 0; i < size; i++)
        newData[i] = data[(i + head) % data.length];
    head = 0;
    tail = size;
    data = newData;
}
```

### 4.3、查询操作
查询操作没有什么特别的直接返回head节点的元素。
```java
@Override
public E getFront() {
    if (isEmpty()) {
        throw new IllegalArgumentException("Cannot getFront from an empty queue");
    }
    return data[head];
}
```

## 5、时间复杂度分析
|   |入队操作|出队操作|查询操作|
|:---:|:---:|:---:|:---:|
|数组队列|O(1)|O(N)|O(1)|
|链表队列|O(1)|O(1)|O(1)|
|循环队列|O(1)|O(1)|O(1)|
实际上，循环队列是对数组队列的一个优化，减小了数组队列中在头部进行入队或者出队造成的复杂度。

## 最后
更多精彩内容，大家可以转到我的主页：[曲怪曲怪的主页](http://quguaiquguai.cn:8090/)
或者关注我的微信公众号：**TeaUrn**
或者扫描下方二维码进行关注。里面有惊喜等你哦。
源码地址：可在公众号内回复 **数据结构与算法源码** 即可获得。
<img src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/%E5%85%AC%E4%BC%97%E5%8F%B7%E4%BA%8C%E7%BB%B4%E7%A0%81.jpg" width=40%>
