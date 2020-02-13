import java.util.Currency;
import java.util.TreeMap;

/**
 * Author : 13152
 * Date : 2020/2/7
 * Time : 11:14
 * Version ： 1.0.0
 **/
public class MapSum677 {

    private class Node{
        public int value;
        public TreeMap<Character, Node> next;

        public Node(int value) {
            this.value = value;
            next = new TreeMap<>();
        }

        public Node() {
            this(0);
        }
    }

    private Node root;
    /** Initialize your data structure here. */
    public MapSum677() {
        root = new Node();
    }

    public void insert(String word, int val) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }
        cur.value = val;
    }

    public int sum(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null)
                return 0;
            cur = cur.next.get(c);
        }
        return sum(cur);
    }

    private int sum(Node node) {
        // 不用到底的情况
        int res = node.value;
        for (char key : node.next.keySet()) {
            res += sum(node.next.get(key));
        }
        return res;
    }
}