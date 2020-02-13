/**
 * Solution
 *
 * @author LiYang
 * @date 2020/1/4
 **/
import java.security.KeyStore;
import java.util.Stack;
public class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{')
                stack.push(c);
            else {
                if (stack.isEmpty())
                    return false;
                if (c == ')' && stack.pop() != '(')
                    return false;
                if (c == ']' && stack.pop() != '[')
                    return false;
                if (c == '}' && stack.pop() != '{')
                    return false;
            }
        }
        return stack.isEmpty();
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isValid("a)("));
    }
}
