/**
 * Author : 13152
 * Date : 2020/2/9
 * Time : 17:15
 * Version ： 3.0.0
 * 基于size进行优化
 * 让父亲节点下更少的元素去指向父亲节点更多的元素，降低树的高度
 **/
public class UnionFind_v3 implements UF{

    private int[] parent;  // 指向父亲的节点
    private int[] sz;

    public UnionFind_v3(int size) {
        parent = new int[size];
        sz = new int[size];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            sz[i] = 1;
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

        if (sz[pRoot] < sz[qRoot]){
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
        else{
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }

    }
}
