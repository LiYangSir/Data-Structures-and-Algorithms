/**
 * Author : 13152
 * Date : 2020/2/9
 * Time : 10:34
 * Version ： 1.0.0
 **/
public class UnionFind_v1 implements UF{

    private int[] id;

    public UnionFind_v1(int size) {
        id = new int[size];

        for (int i = 0; i < id.length; i++)
            id[i] = i;

    }

    @Override
    public int getSize() {
        return id.length;
    }

    private int find(int p) {
        if (p < 0 || p >= id.length)
            throw new IllegalArgumentException("p is out of bound");
        return id[p];
    }

    @Override
    public boolean isConnect(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        if (isConnect(p, q))
            return;
        for (int i = 0; i < id.length; i++)
            if (id[i] == id[q])
                id[i] = id[p];
    }
}
