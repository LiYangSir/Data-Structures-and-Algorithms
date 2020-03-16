/**
 * LoopQueue
 *
 * @author LiYang
 * @date 2020/1/5
 **/
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    private int size;  // 后期可以消除

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        // 不让队列因为front和tail相等为空
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);  // 直接初始化
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        if ((tail + 1) % data.length == front)  // tail在front后面
            resize(getCapacity() * 2);

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++)
            newData[i] = data[(i + front) % data.length];
        front = 0;
        tail = size;
        data = newData;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");
        }
        E temp = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return temp;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot getFront from an empty queue");
        }
        return data[front];
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("LoopQueue size = %d, capacity = %d\n", size, getCapacity()));
        res.append("front[");

        for (int i = 0; i < size; i++) {
            res.append(data[(i + front) % data.length]);
            if (i != size - 1)
                res.append(", ");
        }
        // 迭代器型

        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if (i != tail - 1)
                res.append(", ");
        }
        res.append("]tail");
        return res.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<>();
        for (int i = 0; i < 10; i++)
            queue.enqueue(i);
        System.out.println(queue);
        queue.enqueue(5);
        System.out.println(queue);
        System.out.println(queue.dequeue());
        System.out.println(queue);
        System.out.println(queue.getFront());
        System.out.println(queue);
        for (int i = 0; i < 8; i++) {
            System.out.println(queue.dequeue());
            System.out.println(queue);
        }
        System.out.println(queue);
    }
}
