/**
 * Author : 13152
 * Date : 2020/2/9
 * Time : 17:30
 * Version ： 4.0.0
 * 基于rank（高度）进行优化
 * 高度低的去指向高度高的父亲节点
 **/

public class UnionFind_v4 implements UF{

    private int[] parent;  // 指向父亲的节点
    private int[] rank;

    public UnionFind_v4(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    private int findParent(int id) {
        if (id < 0 || id >= parent.length)
            throw new IllegalArgumentException("id is out of bound");
        while (parent[id] != id) {
            id = parent[id];
        }
        return id;
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnect(int p, int q) {
        return findParent(p) == findParent(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = findParent(p);
        int qRoot = findParent(q);

        if (isConnect(p, q))
            return;

        if (rank[pRoot] < rank[qRoot])
            parent[pRoot] = qRoot;
        else if (rank[pRoot] > rank[qRoot])
            parent[qRoot] = pRoot;
        else{
            parent[qRoot] = pRoot;
            rank[pRoot]++;
        }

    }
}
