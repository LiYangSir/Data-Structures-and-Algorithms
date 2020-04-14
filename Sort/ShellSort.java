import java.util.Arrays;
//交换方法
public class ShellSort {
    public static void shellSort(int[] arr) {
        int temp = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) { //gap步长
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    public static void shellSort2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[i];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        arr[j] = arr[j - gap];//移动
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        int[] arr = new int[8000];
        for (int i = 0; i < 8000; i++) {
            arr[i] = (int)(Math.random() * 800000000);
        }
        long startTime = System.nanoTime();
        shellSort2(arr);
        long endTime = System.nanoTime();
        System.out.println("时间消耗：" + (endTime - startTime) / 1000000000.0);

        int[] arr2 = {3, 9, -1, 10, -2, 6, 0, -10};
        shellSort2(arr2);
        System.out.println(Arrays.toString(arr2));
    }
}
