import java.util.Arrays;

public class QuickSort {

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void sort(Comparable[] a) {
        int lo = 0;
        int hi = a.length - 1;
        sort(a, lo, hi);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        //安全校验
        if (hi <= lo)
            return;

        //对数组进行分组
        int partition = partition(a, lo, hi);//返回分组分界值所在索引，位置变换后的索引
        //左子组有序
        sort(a, lo, partition - 1);
        //右子组有序
        sort(a, partition + 1, hi);
    }

    public static int partition(Comparable[] a, int lo, int hi) {
        int left = lo;
        int right = hi + 1;
        Comparable key = a[lo];

        while (true) {
            while (less(key, a[--right])) {
                if (right == lo)
                    break;
            }
            while (!less(key, a[++left])) {
                if (left == hi)
                    break;
            }
            if (left >= right)
                break;
            else
                exch(a, left, right);
        }
        exch(a, lo, right);
        return right;
    }

    public static void main(String[] args) {
//        Integer[] arr = new Integer[8000000];
//        for (int i = 0; i < 8000000; i++) {
//            arr[i] = (int)(Math.random() * 800000000);
//        }
//        long startTime = System.nanoTime();
//        sort(arr);
//        /*bubbleShow(arr);*/
//        long endTime = System.nanoTime();
//        System.out.println("时间消耗：" + (endTime - startTime) / 1000000000.0);

        Integer[] a = {6, 5, 6, 8, 9, 1};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
