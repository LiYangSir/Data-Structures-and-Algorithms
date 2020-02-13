import java.util.TreeSet;

public class Solution804 {

    public int uniqueMorseRepresentations(String[] words) {

        String[] code = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        TreeSet<String> set = new TreeSet<>();
        for (String word : words) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                res.append(code[word.charAt(i) - 'a']);
            }
            set.add(res.toString());
        }
        return set.size();
    }

    public static void main(String[] args) {
        Solution804 solution804 = new Solution804();
    }
}
