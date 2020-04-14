import java.util.Arrays;

public class MergeSort {

    private static Comparable[] assist;

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void sort(Comparable[] a) {
        //初始化辅助数组
        assist = new Comparable[a.length];
        int lo = 0;
        int hi = a.length - 1;
        sort(a, lo, hi);
    }
    private static void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo)
            return;
        int mid = (lo + hi) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);

        merge(a, lo, mid, hi);
    }


    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        //定义三个指针
        int i = lo;
        int p1 = lo;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= hi) {
            if (less(a[p1], a[p2]))
                assist[i++] = a[p1++];
            else
                assist[i++] = a[p2++];
        }
        while (p1 <= mid)
            assist[i++] = a[p1++];
        while (p2 <= hi)
            assist[i++] = a[p2++];

        for (int index = lo; index<= hi; index++) //可以是i
            a[index] = assist[index];
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 800000000);
        }
        long startTime = System.nanoTime();
        sort(arr);
        long endTime = System.nanoTime();
        System.out.println("时间消耗：" + (endTime - startTime) / 1000000000.0);

        System.out.println(Test.isSequence(arr));
    }
}
