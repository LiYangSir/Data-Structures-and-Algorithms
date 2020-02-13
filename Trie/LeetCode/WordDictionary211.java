import java.util.TreeMap;

/**
 * Author : 13152
 * Date : 2020/2/7
 * Time : 10:30
 * Version ： 1.0.0
 **/
public class WordDictionary211 {
    private class Node{
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }
    private Node root;
    /** Initialize your data structure here. */
    public WordDictionary211() {
        root = new Node();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }
        cur.isWord = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(root, word, 0);
    }

    private boolean match(Node node, String word, int index) {
        if (index == word.length())
            return node.isWord;
        if (node == null)
            return false;
        char c = word.charAt(index);
        if (c == '.') {
            for (char key:node.next.keySet())
                if (match(node.next.get(key), word, index + 1))
                    return true;
            return false;
        }
        return match(node.next.get(c), word, index + 1);
    }

    public static void main(String[] args) {
        WordDictionary211 wordDictionary211 = new WordDictionary211();
        wordDictionary211.addWord("at");
        System.out.println(wordDictionary211.search("at"));
        wordDictionary211.addWord("bat");
        System.out.println(wordDictionary211.search("bat"));
        System.out.println(wordDictionary211.search(".at"));
    }
}
