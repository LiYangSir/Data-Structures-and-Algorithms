import java.util.concurrent.BlockingDeque;

public class RBTree<K extends Comparable<K>, V extends Comparable<V>>{

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node{  //内部类
        public K key;
        public V value;
        public  Node left, right;
        public boolean color;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;   // 新建的节点永远是融合节点,绝对平衡
        }

        @Override
        public String toString() {
            return key.toString() + ":" + value.toString();
        }
    }
    private Node root;
    private int size;


    public RBTree(){
        root = null;
        size = 0;
    }

    private boolean isRed(Node node) {
        if (node == null)
            return BLACK;
        return node.color;
    }
    private Node getNode(K key) {
        return getNode(root, key);
    }

    private Node getNode(Node node, K key) {
        if (node == null)
            return null;

        if (node.key.compareTo(key) < 0)
            return getNode(node.left, key);
        else if (node.key.compareTo(key) > 0)
            return getNode(node.right, key);
        else
            return node;
    }

    private Node leftRotate(Node node) {
        Node x = node.right;

        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    private Node rightRotate(Node node) {
        Node x = node.left;

        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;
        return x;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK;
    }

    private Node add(Node node, K key, V value) {
        if (node == null){
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else
            node.key = key;

        // 非互斥行为

        if (isRed(node.right) && !isRed(node.left))
            node = leftRotate(node);
        if (isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);
        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        return node;
    }


    public V remove(K key) {
        return remove(root, key).value;
    }

    private Node remove(Node node, K key) {
        if (node == null)
            return null;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            Node successor = minimum(node.right);
            successor.right = removeMinimum(node.right);
            successor.right = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    private Node removeMinimum(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMinimum(node.left);
        return node;
    }

    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }


    public boolean contains(K key) {
        return getNode(key) != null;
    }

    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue) {
        Node node = getNode(key);
        if (node != null)
            node.value = newValue;
        else
            throw new IllegalArgumentException(key + "doesn't exist");
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}

