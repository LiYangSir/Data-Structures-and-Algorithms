/**
 * Author : 13152
 * Date : 2020/2/6
 * Time : 15:52
 * Version ： 1.0.0
 **/
import java.util.Currency;
import java.util.TreeMap;

public class Trie {

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
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    public void add_v2(String word) {
        add_v2(root, word);
    }

    private void add_v2(Node node, String word) {
         if (word.length() == 0) {
             if (!node.isWord){
             node.isWord = true;
             size++;
             }
             return;
         }
         char c = word.charAt(0);
         if (node.next.get(c) == null)
             node.next.put(c, new Node());
         add_v2(node.next.get(c), word.substring(1));
    }

    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    public boolean contains_v2(String word) {
        return contains(root, word);
    }

    private boolean contains(Node node, String word) {
        if (word.length() == 0)
            return node.isWord;
        if (node == null)
            return false;
        return contains(node.next.get(word.charAt(0)), word.substring(1));
    }

    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return true;
    }
}
