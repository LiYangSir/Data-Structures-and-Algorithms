import org.w3c.dom.Node;

public class BSTMap<K extends Comparable<K>, V extends Comparable<V>> implements Map<K, V>  {

    private class Node{  //内部类
        public K key;
        public V value;
        public  Node left, right;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return key.toString() + ":" + value.toString();
        }
    }
    private Node root;
    private int size;


    public BSTMap(){
        root = null;
        size = 0;
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

    @Override
    public void add(K key, V value) {
        add(root, key, value);
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
        return node;
    }

    @Override
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


    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if (node != null)
            node.value = newValue;
        else
            throw new IllegalArgumentException(key + "doesn't exist");
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
