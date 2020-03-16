import java.util.Random;

/**
 * Author : 13152
 * Date : 2020/1/31
 * Time : 10:45
 * Version ： 1.0.0
 **/
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap(E[] arr) {
        data = new Array<E>(arr);
        for (int i = parent(arr.length - 1); i >= 0; i--)
            siftDown(i);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    private int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("index isn't zero");
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int index) {
        while (index > 0 && data.get(parent(index)).compareTo(data.get(index)) < 0) {
            data.swap(parent(index), index);
            index = (index - 1) / 2;
        }
    }

    public E findMax() {
        if (isEmpty())
            throw new IllegalArgumentException("asdsa");
        return data.get(0);
    }

    public E extractMax() {
        E ret = findMax();
        data.swap(0, size() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    private void siftDown(int index) {
        while (leftChild(index) < size()) {
            int j = leftChild(index);
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0)
                j++;
            if (data.get(index).compareTo(data.get(j)) >= 0)
                break;
            else
                data.swap(index, j);
            index = j;
        }
    }

    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

    public static void main(String[] args) {
        int n = 100000;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
        }

        for (int i = 1; i < n; i++)
            if (arr[i-1] < arr[i])
                throw new IllegalArgumentException("Error");
            System.out.println("Yes");
        }
}
