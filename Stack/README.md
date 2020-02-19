<h1 align=center><a href="https://github.com/LiYangSir/Data-Structures-and-Algorithms/tree/master/Array" target="_blank">Stack 栈</a></h1>
<div align="center">
<image src="https://img.shields.io/badge/Github-LiYangSir-brightgreen">
<image src="https://img.shields.io/badge/author-teaUrn-green">
<image src="https://img.shields.io/badge/Language-Java-orange">
</div>

---------

### 1、Stack栈概述

&emsp;&emsp;栈这个数据结构有着自己的性质，也就是 **先进后出，后进先出** 的结构。最经典的就是调用函数这一块。不断向栈中加入缓存，最后执行完的函数会回调用放在栈顶的缓存。和它类似的就是队列的数据结构。队列有着**先进先出，后进后出**的结构。两者应用不同的场景。
<div align="center">
<img src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95/Stack%E6%A0%88/%E6%A0%88%E7%9A%84%E6%A6%82%E5%BF%B5.png" width = "40%"/></div>
&emsp;&emsp;这里将栈设计为Java接口，目的是实现栈的底层有很多。例如数组、链表、二叉树等等。他们都将调用这个Stack接口。

**涉及的函数方法：**

<div align="center">
<img src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95/Stack%E6%A0%88/%E6%A6%82%E5%BF%B5%E6%80%9D%E7%BB%B4%E5%9B%BE.png" width = "80%"/></div>
涉及的函数不多，所以实现起来也会比较简单。

### 2、Stack栈的实现——基于动态数组

&emsp;&emsp;至于动态数组包含什么方法，可以参考[ Array动态数组 ](/Array/README.md)这一片文章。

#### 2.1、Stack 接口
&emsp;&emsp;包含获取Stack栈内元素的个数、是否为空、进出栈操作、以及查看栈顶元素。
```java
public interface Stack<E> {
    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek(); //观察栈顶元素，但不取出
}
```
#### 2.2、基本操作
&emsp;&emsp;重载接口函数，返回底层数组下的大小和是否为空。

```java
@Override
public int getSize() {
    return array.getSize();
}

@Override
public boolean isEmpty() {
    return array.isEmpty();
}
```
#### 2.3 进出栈操作
&emsp;&emsp;进栈操作主要是围绕**先进后出，后进先出**的原则，即向数组后添加元素和在数组后取出元素。之所以在数组后取出元素，在[Array动态数组](/Array/README.md)这一片文章中的时间复杂度分析得出，向数组后添加和取出元素都是O(1)级别的复杂度。所将数组末尾作为栈顶。

```java
@Override
public void push(E e) {
    array.addLast(e);
}

@Override
public E pop() {
    return array.removeLast();
}
```
#### 2.4、查询操作
&emsp;&emsp;查询操作就是查看栈顶的元素。由于默认数组末尾为栈顶，所以查询操作就是查看数组最后一位元素是啥。

```java
@Override
public E peek() {
    return array.getLast();
}
```

### 3、Stack栈的实现——基于链表实现

&emsp;&emsp;接口和数组实现一样，这也充分体现了接口的好处。只不过底层是链表的形式存储数据。

#### 3.1、基本操作

&emsp;&emsp;重载接口函数，返回底层数组下的大小和是否为空。和数组的实现相同。

```java
@Override
public int getSize() {
    return list.getSize();
}

@Override
public boolean isEmpty() {
    return list.isEmpty();
}
```

#### 3.2 进出栈操作

&emsp;&emsp;进出栈操作和数组实现就不一样啦。虽然表面上看上去相同，实则不同。Array数组最好使用向数组末尾添加元素来降低复杂度。对于链表来说，向头部或者尾部添加和删除元素的时间复杂度相同，所以向头部或者尾部操作时间复杂度是相同的，都是O(1)级别的。链表头或者尾**都**可以作为栈顶。

```java
@Override
public void push(E e) {
    list.addFirst(e);
}

@Override
public E pop() {
    return list.removeFirst();
}
/**    或者    **/
// @Override
// public void push(E e) {
//     list.addLast(e);
// }

// @Override
// public E pop() {
//     return list.removeLast();
// }

```

#### 3.3、查询操作

&emsp;&emsp;查询操作和数组实现相同，但是链表实现的既可以以链表头作为栈顶，也可以使用链表末尾作为栈顶。

```java
@Override
public E peek() {
    return list.getLast();
}
/**    或者    **/
// @Override
// public E peek() {
//     return list.getFirst();
// }
```

### 4、时间复杂度分析

&emsp;&emsp;由于栈的结构比较简单，时间复杂度主要依靠底层的实现。根据下表可以得出，基于链表和基于数组的时间复杂度均为 O(1) 级别。
||动态数组|链表|
|:---:|:---:|:---:|
|进栈操作|O(1)|O(1)|
|出栈操作|O(1)|O(1)|
|查询操作|O(1)|O(1)|

### 5、栈的另一个实际应用——括号匹配

&emsp;&emsp;在实际开发过程中，括号匹配是我们经常遇到的问题。{ ( [ ] ) } 这种就是可以正确的。对于这种 { [ ] ) } 就是不匹配的问题。应用栈的结构就可以轻松解决这个问题。对于 { [ ( 执行进栈操作。对于 ) } ]则进行出栈操作，判断出栈的元素是否和 ) } ]的对立符号是否匹配。
以匹配 **{ ( ) }** 为例：

<div align=center>
<img src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95/Stack%E6%A0%88/%E5%8C%B9%E9%85%8D%E6%88%90%E5%8A%9F.png" width="80%">
</div>

以匹配 **{ ( ] }** 为例：

<div align=center>
<img src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95/Stack%E6%A0%88/%E5%8C%B9%E9%85%8D%E5%A4%B1%E8%B4%A5.png" width="60%">
</div>

```java
public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);  //将字符一个一个提出来
            if (c == '(' || c == '[' || c == '{')  //入栈的元素
                stack.push(c);
            else {
                if (stack.isEmpty())
                    return false;
                //观察出栈的元素是否和字符串中的字符对立。
                if (c == ')' && stack.pop() != '(') 
                    return false;
                if (c == ']' && stack.pop() != '[')
                    return false;
                if (c == '}' && stack.pop() != '{')
                    return false;
            }
        }
        return stack.isEmpty();
    }
```

更多精彩内容，大家可以转到我的主页：[主页](http://quguaiquguai.cn:8090/)
或者关注我的微信公众号：**TeaUrn**
或者扫描下方二维码进行关注。
<img src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/%E5%85%AC%E4%BC%97%E5%8F%B7%E4%BA%8C%E7%BB%B4%E7%A0%81.jpg" width=50%>



