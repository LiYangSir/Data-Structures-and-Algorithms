import java.util.ArrayList;

/**
 * Author : 13152
 * Date : 2020/2/6
 * Time : 17:03
 * Version ： 1.0.0
 **/
public class Trie_Main {
    public static void main(String[] args){
        Trie trie = new Trie();
        String[] words = {"you","love","me","da","df","fd"};
        for (String word : words) {
            trie.add_v2(word);
        }

        System.out.println(trie.getSize());
        System.out.println(trie.contains_v2("sd"));
    }
}
