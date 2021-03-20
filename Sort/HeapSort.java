import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HeapSort {

    public static void sort(int[] arr){
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);  // 确定初始化元素，构建一个最大堆
        }
        for (int i = 1; i < arr.length; i++) {
            swap(arr, 0, arr.length - i);
            adjustHeap(arr, 0, arr.length - i);
        }
    }
    private static void adjustHeap(int[] arr, int i, int length){
        int temp = arr[i];
        for (int j = 2 * i + 1; j < length; j = 2 * j + 1) {
            if (j + 1 < length && arr[j] < arr[j + 1]) {
                j++;
            }
            if (arr[j] > temp) {
                arr[i] = arr[j];
                i = j;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        HeapSort.sort(arr);
        Arrays.stream(arr).forEach(System.out::print);
    }
}
