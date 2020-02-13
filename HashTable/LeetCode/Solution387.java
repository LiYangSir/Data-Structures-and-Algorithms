/**
 * Author : 13152
 * Date : 2020/2/7
 * Time : 14:41
 * Version ： 1.0.0
 **/
public class Solution387 {
    public int firstUniqChar(String s) {
        int[] code = new int[26];
        for (int i = 0; i < s.length(); i++)
            code[s.charAt(i) - 'a']++;
        for (int i = 0; i < s.length(); i++)
            if (code[s.charAt(i) - 'a'] == 1)
                return i;
        return -1;
    }

    public static void main(String[] args) {
        Solution387 solution387 = new Solution387();
        System.out.println(solution387.firstUniqChar("leetcode"));
    }
}
