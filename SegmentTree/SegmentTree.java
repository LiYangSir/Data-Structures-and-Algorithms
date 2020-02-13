import java.util.Arrays;
import java.util.Objects;

/**
 * Author : 13152
 * Date : 2020/2/5
 * Time : 16:54
 * Version ： 1.0.0
 **/
public class SegmentTree<E> {
    private E[] tree;
    private E[] data;
    private Merger<E> merger;
    public SegmentTree(E[] arr, Merger<E> merger) {

        this.merger = merger;

        data = (E[])new Object[arr.length];
        System.arraycopy(arr, 0, data, 0, arr.length);

        tree = (E[])new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);

    }

    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftChild(treeIndex), l, mid);
        buildSegmentTree(rightChild(treeIndex), mid + 1, r);
        tree[treeIndex] = merger.merge(tree[leftChild(treeIndex)], tree[rightChild(treeIndex)]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal");
        return data[index];
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length)
            throw new IllegalArgumentException("index is illegal");

        return query(0,0,data.length - 1, queryL, queryR);
    }

    private E query(int treeIndex, int l, int r, int queryL, int queryR) {  // 递归有回朔的过程
        if (l == queryL && r == queryR)
            return tree[treeIndex];
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (queryL >= mid + 1)
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        else if (queryR <= mid)
            return query(leftTreeIndex, l, mid, queryL, queryR);
        E left = query(leftTreeIndex, l, mid, queryL, mid);
        E right = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(left, right);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal");
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r){
            tree[treeIndex] = e;
            return;
        }
        int mid = l + (l - r) / 2;
        if (index > mid)
            set(rightChild(treeIndex), mid + 1, r, index, e);
        else
            set(leftChild(treeIndex), l, mid, index, e);
        tree[treeIndex] = merger.merge(tree[leftChild(treeIndex)], tree[rightChild(treeIndex)]);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");
            if (i != tree.length - 1)
                res.append(", ");
        }
        return res.toString();
    }
}
