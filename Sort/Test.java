public class Test {
    public static boolean isSequence(Comparable[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1])>0)
                return false;
        }
        return true;
    }
}
