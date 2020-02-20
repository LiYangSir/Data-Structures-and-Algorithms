<a href="https://github.com/LiYangSir/Data-Structures-and-Algorithms/tree/master/Array" target="_blank"><h1 align=center>Array 动态数组</h1></a>

<div align="center">
<image src="https://img.shields.io/badge/Github源码-点击题目-brightgreen">
<image src="https://img.shields.io/badge/author-teaUrn-green">
<image src="https://img.shields.io/badge/Language-Java-orange">
</div>

-----

## 1、Array概述

&emsp;&emsp;同数组不用，数组的大小在定义时已经确定，而在实际过程中数组需要根据数据量的大小自动更改数组大小。底层实现仍是数组，只是将数组进行封装，可以实现自适应的数组Array。

**涉及的所有函数方法：**

<div align="center">
<img src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95/Array%E6%95%B0%E7%BB%84/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%95%B0%E7%BB%84.png" width = "50%"/></div>

## 2、Array数组实现思路

&emsp;&emsp;数组无非涉及增、查、改、删四种操作，查和改操作与平常数组操作相同。**难点**就在于增加元素和删除元素上。对于一般数组当索引index + 1 超过数组大小是就会报错。为了可以根据数据大小改变数组大小，引入capacity（容器）的概念。其中capacity >= size。如图
<div align=center>
<img alt= "自定义数组" src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95/Array%E6%95%B0%E7%BB%84/%E5%88%9D%E5%A7%8B%E5%8C%96.png" width="65%"/></div>

**注：** 实际上capacity才是真正数组的大小，size只是capacity中存储数据的多少。
下面分别说一下增删改查的的实现思路

### 2.1、增加元素
&emsp;&emsp;如果增加后 size <= capacity 则直接将数据添加到数组中，如果增加后 size > capacity 则需要对数组进行扩容操作。扩容的方式也就是扩大为原来的2倍。

**下面以在索引index=2，元素未5为例：** 主要分为有无扩容的请况

**1）增加元素-未扩容：**
1. 复制元素向后移动
2. 插入元素到索引位置 
<div align=center><img src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95/Array%E6%95%B0%E7%BB%84/%E5%A2%9E%E5%8A%A0-%E6%9C%AA%E6%89%A9%E5%AE%B9.png" width="98%" div alt="增加元素-未扩容"></div>
**2）增加元素-扩容：**
1. 扩容为原来的2倍，将原数据复制到新数组
2. 将索引之后的元素向后移一位
3. 插入元素
<div align=center><img src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95/Array%E6%95%B0%E7%BB%84/%E5%A2%9E%E5%8A%A0-%E6%89%A9%E5%AE%B9.png" width="95%" div alt="增加元素-未扩容"></div>
&emsp;&emsp;扩容的方式就是开辟一块原capacity的大小2倍的空间，然后将原数组的数据拷贝到新开辟的数组当中。
在头部和尾部增加元素对应 index = 0 和 index = size - 1

**程序实现：**
```java
public void add(int index, E e) {
    if (size == data.length)
        resize(2 * data.length);  //开辟新空间

    if (index < 0 || index > size)
        throw new IllegalArgumentException("addLast failed, Required index < 0 || index > size");

    System.arraycopy(data, index, data, index + 1, size - index); // 向后移动一位
    data[index] = e;  // 增加元素
    size ++;  // 维护size
}

// 私有函数  开辟新空间
private void resize(int newCapacity) {
    E[] newData = (E[]) new Object[newCapacity];
    System.arraycopy(data, 0, newData, 0, size);  // 复制数组数据到新数组
    data = newData;
}
```

### 2.2、删除元素
&emsp;&emsp;增加元素对应扩容，那么删除元素对应缩容，当删除元素后 size < capacity / 2的时候进行缩容。否则直接让对应位置等于 null 即可。
**下面以删除索引为1的元素为例 ：** 主要分为有无缩容的请况
**1）删除元素-未缩容的情况 ：**
1. 将要删除元素后面的数据向前移一位
2. 删除数据中最后一位的元素，索引为size(删除后size已经减 1)

<div align=center><img src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95/Array%E6%95%B0%E7%BB%84/%E5%88%A0%E9%99%A4-%E6%9C%AA%E6%89%A9%E5%AE%B9.png" width="100%" div alt="删除元素-未缩容"></div>
**2）删除元素-缩容的情况 ：**
1. 将要删除元素后面的数据向前移一位
2. 删除数据中最后一位的元素，索引为size(删除后size已经减 1)
3. 进行缩容，将原数据复制到原数组大小一半的新数组中
  
<div align=center><img src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95/Array%E6%95%B0%E7%BB%84/%E5%88%A0%E9%99%A4-%E7%BC%A9%E5%AE%B9.png" width="70%" div alt="删除元素-缩容"></div>
**程序实现 ：**
```java
public E remove(int index) {
    if (index < 0 || index >= size) {  //越界判断
        throw new IllegalArgumentException("delete failed, Required index < 0 || index >= size");
    }
    E temp = data[index];
    System.arraycopy(data, index+1, data, index, size-index-1); //向前移动一位
    size--;    //维护size的大小
    data[size] = null;  // 清空对应位置的数据
    if (size == data.length / 2 && data.length / 2 != 0)
        resize(data.length / 2);  //进行缩容操作
    return temp;  //返回被删除的元素
}
```
### 2.3、改变元素
改变元素不涉及扩容或者缩容，没有特别的地方，直接修改即可。
**下面以set(2, 5)为例 ：**
<div align=center><img src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95/Array%E6%95%B0%E7%BB%84/%E6%94%B9.png" width="65%" div alt="改变元素"></div>
**代码实现：**
```java
void set(int index, E e) {
    if (index < 0 || index >= size) {
        throw new IllegalArgumentException("get failed, Required index < 0 || index >= size");
    }
    data[index] = e;
}
```

### 2.4、查找元素
&emsp;&emsp;查找元素分为三种，查找是否有这个元素，查找这个元素所在位置，根据索引返回元素这三种。
查找是否有这个元素和查找这个元素所在位置代码实现相似，一个找到了返回 true,一个找到了返回 index。
**1）查找是否有这个元素**
```java
public boolean contains(E e){
    for (int i = 0; i < size; i++) {
        if (data[i].equals(e))
            return true;
    }
    return false;
}
```
**2）查找这个元素所在位置**
```java
public int find(E e) {
    for (int i = 0; i < size; i++) {
        if (data[i].equals(e)) 
            return i;
    }
    return -1;
}
```

## 3、时间复杂度分析
**1）增加元素的复杂度**
&emsp;&emsp;最好的情况是向最后的位置添加元素复杂度为 O(1) ，最坏的情况是向头添加元素，因为需要数据整体向后移动,复杂度为 O(N) ，根据均摊复杂度得出复杂度为 O(N/2) = O(N)，也就是说增加元素的复杂度为 O(N)。但是这仅仅是考虑没有扩容的情况产生。但是对于扩容的也是遍历一边数组进行复制的操作，所以复杂度也为 O(N)。
综上所述增加操作的时间复杂度为 O(N)

|方法|复杂度|
|:---:|:---:|
|addFirst(e)|O(1)|
|addLast(e)|O(N)|
|add(index, e)|O(N/2) = O(N)|
|resize(newCapacity)|O(N)|

**2）删除元素的复杂度**
&emsp;&emsp;删除元素的时间复杂度同增加元素完全相同。一个扩容一个缩容，实现机理相似。也就是说删除操作的时间复杂度也是 O(N) 级别。

|方法|复杂度|
|:---:|:---:|
|removeFirst()|O(1)|
|removeLast()|O(N)|
|remove(index)|O(N/2) = O(N)|
|resize(newCapacity)|O(N)|

**3）修改元素复杂度**
&emsp;&emsp;修改操作利用了数组的优势，支持随机访问。也就是可以在 O(1)的复杂度的情况下完成操作。
|方法|复杂度|
|:---:|:---:|
|set(index, e)|O(1)|

**3）修改元素复杂度**
&emsp;&emsp;修改操作对应三种情况，对于支持索引查找的get方法复杂为 O(1)。对于contain方法和find方法两者类似，需要遍历数组，最好的情况是在最开始就找到想要查找元素，最坏的情况是想要查找的元素在末尾，根据均摊复杂度，两者的复杂度为 O(N)。
|方法|复杂度|
|:---:|:---:|
|get(index)|O(1)|
|contain(e)|O(N)|
|find(e)|O(N)|

## 4、解决时间复杂度震荡的办法

&emsp;&emsp;对于时间复杂度的震荡发生在扩容和缩容的边界。addLast()导致扩容，removeLast()导致缩容，在边界反复添加和删除操作会导致时间复杂度震荡。导致时间复杂度一直维持在O(N)级别。

<div align=center><img src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95/Array%E6%95%B0%E7%BB%84/%E6%97%B6%E9%97%B4%E5%A4%8D%E6%9D%82%E5%BA%A6%E9%9C%87%E8%8D%A1.png" width="100%" div alt="改变元素"></div>
&emsp;&emsp;产生的原因就是因为我们在 removeLst 的时候过于 Eager (激进)，导致与 addLast() 的扩容操作相冲突，其实我们避开 / 2 和 * 2 就可以避免震荡问题，因此我在代码中将 removeLast 的 resize 触发改为 / 4.

```java
public E remove(int index) {
    if (index < 0 || index >= size) {
        throw new IllegalArgumentException("delete failed, Required index < 0 || index >= size");
    }
    E temp = data[index];
    System.arraycopy(data, index+1, data, index, size-index-1);
    size--;
    data[size] = null;
    if (size == data.length / 4 && data.length / 2 != 0)  //解决复杂度震荡问题
        resize(data.length / 2);
    return temp;
}
```

## 5、Array具体实现函数

### 公有方法

```java
getSize()    //获取数组大小
getCapacity() //获取数组容器
isEmpty()     //判断是否为空
addFirst(E e) // 向数组头添加元素
addLast(E e)      //向数组后添加元素
add(int index, E e)  //向index索引处增加元素
get(int index)  // 获取索引index处元素
set(int index, E e) // 修改索引index处为e
remove(int index)  // 删除元素,返回删除的元素
removeFirst()   //删除第一个元素,
removeLast()   //删除最后一个元素
removeElement(E e)   //删除某一个元素
removeAllElement(E e)   //删除所有元素e
contains(E e)  //判断数组是否包含元素e
find(E e)  // 查找元素e所在位置
toString()   //打印函数
```
### 私有方法

```java
resize()  //重新定义数组大小
```

## 最后
更多精彩内容欢迎大家关注我的微信公众号：**TeaUrn**
如果有**问题**或者**疑问**可以私信我或者微信上私信我。
也欢迎大家来我的**主页**: [曲怪曲怪](http://quguaiquguai.cn:8090/) 
我在这里等你哦~~
