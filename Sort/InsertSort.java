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

    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 800000000);
        }
        long startTime = System.nanoTime();
        insertSort(arr);
        long endTime = System.nanoTime();
        System.out.println("时间消耗：" + (endTime - startTime) / 1000000000.0);
    }
}
