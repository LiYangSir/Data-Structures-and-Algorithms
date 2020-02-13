import java.util.TreeMap;

/**
 * Author : 13152
 * Date : 2020/2/7
 * Time : 11:53
 * Version ： 1.0.0
 **/
public class MapSum_v2677 {

    private class Node{
        public boolean isWord;
        public int value;
        public TreeMap<Character, Node> next;

        public Node(int value, boolean isWord) {
            this.value = value;
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(0, false);
        }
    }

    private Node root;
    /** Initialize your data structure here. */
    public MapSum_v2677() {
        root = new Node();
    }

    public void insert(String word, int val) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                cur.next.put(c, new Node(val, false));
            else if (!cur.next.get(c).isWord)
                cur.next.get(c).value += val;
            else

            cur = cur.next.get(c);
        }
        cur.isWord = true;
    }

    public int sum(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null)
                return 0;
            cur = cur.next.get(c);
        }
        return cur.value;
    }

    public static void main(String[] args) {

    }
}
