/**
 * Author : 13152
 * Date : 2020/2/9
 * Time : 17:53
 * Version ： 5.0.0
 * 路径压缩（降低高度）
 * 在查找的过程中，让节点指向它父亲的父亲（爷爷）从而达到降低高度
 **/
public class UnionFind_v5 implements UF {

    private int[] parent;  // 指向父亲的节点
    private int[] rank;

    public UnionFind_v5(int size) {
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
            parent[id] = parent[parent[id]];
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
