/**
 * Author : 13152
 * Date : 2020/2/6
 * Time : 10:11
 * Version ： 1.0.0
 **/
public class NumArray {
    private int[] data;
    public NumArray(int[] nums) {
        data = new int[nums.length];
        System.arraycopy(nums, 0, data, 0, nums.length);
    }

    public int sumRange(int i, int j) {
        int res = 0;
        for(int m = i; m <= j; m++)
            res += data[m];
        return res;
    }
}