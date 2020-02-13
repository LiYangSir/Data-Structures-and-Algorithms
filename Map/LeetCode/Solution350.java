import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class Solution350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums1) {
            if (!map.containsKey(num))
                map.put(num, 1);
            else
                map.put(num, map.get(num) + 1);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (map.containsKey(num)) {
                list.add(num);
                map.put(num, map.get(num) - 1);
                if (map.get(num) == 0)
                    map.remove(num);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public int[] intersect_v2(int[] nums1, int[] nums2) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        for (int num : nums1)
            list.add(num);
        for (int num : nums2) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == num) {
                    list2.add(list.get(i));
                    list.remove(i);
                    break;
                }
            }
        }
        int[] res = new int[list2.size()];
        for (int i = 0; i < list2.size(); i++) {
            res[i] = list2.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] num1 = new int[]{6, 2, 2, 4, 8};
        int[] num2 = new int[]{1, 2, 2, 4, 5};
        Solution350 solution350 = new Solution350();
        System.out.println(Arrays.toString(solution350.intersect_v2(num1, num2)));
    }
}
