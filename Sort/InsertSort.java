public class InsertSort {
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int insertIndex = i;
            while (insertIndex - 1 >= 0 && arr[insertIndex - 1] > insertVal) {
                arr[insertIndex] = arr[insertIndex - 1];
                insertIndex--;
            }
            if (insertIndex != i)
                arr[insertIndex] = insertVal;
        }
    }

    public static int arrangeCoins(int n) {
        int left = 1, right = 65535;
        while (left < right) {
            int mid = (left + right) / 2;
            int midVal = mid % 2 == 0 ? mid / 2 * (mid + 1) : (mid + 1) / 2 * mid;
            if (midVal > n)
                right = mid;
            else if (midVal < n)
                left = mid + 1;
            else
                return mid;
        }
        return left - 1;
    }

    public static void main(String[] args) {
//        int[] arr = new int[80000];
//        for (int i = 0; i < 80000; i++) {
//            arr[i] = (int)(Math.random() * 800000000);
//        }
//        long startTime = System.nanoTime();
//        insertSort(arr);
//        long endTime = System.nanoTime();
//        System.out.println("时间消耗：" + (endTime - startTime) / 1000000000.0);
        System.out.println(arrangeCoins(1804289383));
    }
}
