import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class Solution349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num:nums1)
            set.add(num);
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (set.contains(num)) {
                set.remove(num);
                list.add(num);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
    public static void main(String[] args) {
        int[] num1 = new int[]{ 6, 2, 2, 7, 8 };
        int[] num2 = new int[]{ 1, 2, 2, 4, 5 };
        Solution349 solution349 = new Solution349();
        System.out.println(Arrays.toString(solution349.intersection(num1, num2)));
    }
}
