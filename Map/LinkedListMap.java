public class LinkedListMap<K, V> implements Map<K, V> {

    private class Node{

        public Node(K key, V value, Node node) {
            this.key = key;
            this.value = value;
            this.next = node;
        }

        public Node(K key, V value) {
            this(key, value, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + ":" + value.toString();
        }

        K key;
        V value;
        Node next;
    }

    private Node dummyNode;
    private int size;

    public LinkedListMap(){
        dummyNode = new Node();
        size = 0;
    }

    private Node getNode(K key) {
        Node cur = dummyNode.next;
        while (cur.next != null) {
            if (cur.key.equals(key))
                return cur;
            cur = cur.next;
        }
        return null;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            dummyNode.next = new Node(key, value, dummyNode.next);
            size++;
        } else
            node.value = value;
    }

    @Override
    public V remove(K key) {
        Node pre = dummyNode;
        while (pre.next != null) {
            if (pre.next.key.equals(key))
                break;
            pre = pre.next;
        }
        if (pre.next != null) {
            Node delNode = pre.next;
            pre.next = delNode.next;
            delNode.next = null;
            size--;
            return delNode.value;
        }
        return null;
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
        if (node == null)
            throw new IllegalArgumentException(key + "doesn't exist");
        node.value = newValue;
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
