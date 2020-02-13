/**
 * Author : 13152
 * Date : 2020/2/9
 * Time : 10:55
 * Version ： 2.0.0
 **/
public class UnionFind_v2 implements UF {

    private int[] parent;  // 指向父亲的节点

    public UnionFind_v2(int size) {
        parent = new int[size];
        for (int i = 0; i < parent.length; i++)
            parent[i] = i;

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
        if (isConnect(p, q))
            return;
        int parent_p = findParent(p);
        parent[parent_p] = findParent(q);
    }
}
