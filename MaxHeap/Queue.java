/**
 * Author : 13152
 * Date : 2020/2/5
 * Time : 9:42
 * Version ： 1.0.0
 **/
public interface Queue<E> {
    int getSize();
    boolean isEmpty();
    E dequeue();
    void enqueue(E e);
    E getFront();
}
