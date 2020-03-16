import java.util.Random;

/**
 * Author : 13152
 * Date : 2020/2/9
 * Time : 17:42
 * Version ： 1.0.0
 **/
public class UnionFind_Main {
    public static double test(UF uf, int m) {
        int size = uf.getSize();
        Random random = new Random();

        long startTime = System.nanoTime();

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a, b);
        }

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnect(a, b);
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }
    public static void main(String[] args) {
        int size = 1000000;
        int m = 1000000;

        UnionFind_v3 uf3 = new UnionFind_v3(size);
        System.out.println("UnionFind_v3 : " + test(uf3, m) + " s");

        UnionFind_v4 uf4 = new UnionFind_v4(size);
        System.out.println("UnionFind_v4 : " + test(uf4, m) + " s");

        UnionFind_v5 uf5 = new UnionFind_v5(size);
        System.out.println("UnionFind_v5 : " + test(uf5, m) + " s");

    }
}
