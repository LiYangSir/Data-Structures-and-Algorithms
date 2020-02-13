/**
 * @author LiYang
 * @param <E>
 * @DATE 2020/1/2
 */
public class Array<E> {
    private E[] data;
    private int size;

    public Array(int capacity) {
        data = (E[])new Object[capacity];
        size = 0;
    }

    public Array() {
        this(10);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addLast(E e) {
        add(size, e);
    }

    public void addFirst(E e) {
        add(0, e);
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }
    public void add(int index, E e) {
        if (size == data.length) {
            resize(2 * data.length);
        }

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("addLast failed, Required index < 0 || index > size");
        }

        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = e;
        size ++;
    }

    E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("get failed, Required index < 0 || index >= size");
        }
        return data[index];
    }

    void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("get failed, Required index < 0 || index >= size");
        }
        data[index] = e;
    }

    /**
     * 通过降容降低时间复杂度
     * @param index:索引值
     * @return:删除的元素
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("delete failed, Required index < 0 || index >= size");
        }
        E temp = data[index];
        System.arraycopy(data, index+1, data, index, size-index-1);
        size--;
        data[size] = null;
        if (size == data.length / 4 && data.length / 2 != 0)
            resize(data.length / 2);
        return temp; //返回被删除的元素
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size-1);
    }

    public void removeElement(E e){
        int index = find(e);
        if (index != -1)
            remove(index);
    }

    public void removeAllElement(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                remove(i);
                i--;
            }
        }
    }
    public boolean contains(E e){
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return true;
        }
        return false;
    }
    //如果不存在则返回 -1
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) //使用值比较
                return i;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array size = %d, capacity = %d\n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i !=size - 1)
                res.append(", ");
        }
        res.append("]");
        return res.toString();
    }
}
