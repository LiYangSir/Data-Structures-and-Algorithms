public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++){
            for (int j = 0; j < arr.length - i - 1; j++)
                if (arr[j] > arr[j+1]){
                    flag = true;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            if (!flag)
                break;
            else
                flag = false; //重置flag
        }
    }


    public static void bubbleShow(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    public static void main(String[] args) {
//        int[] arr = {3, 9, -1, 10, -2};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 800000000);
        }
        long startTime = System.nanoTime();
        bubbleSort(arr);
        /*bubbleShow(arr);*/
        long endTime = System.nanoTime();
        System.out.println("时间消耗：" + (endTime - startTime) / 1000000000.0);
    }
}
