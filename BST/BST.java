import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Queue;
/**
 * BST
 *
 * @author LiYang
 * @date 2020/1/13
 **/
public class BST<E extends Comparable> {
    private class Node{  //内部类
        public E e;
        public  Node left, right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;


    public BST(){
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(E e) {
        root =  add(root, e);
    }

    private Node add(Node node, E e) {
        // 终止条件
        if (node == null){
            size++;
            return new Node(e);
        }
        // 开始递归
        if (e.compareTo(node.e) < 0)
            node.left = add(node.left, e);
        else if (e.compareTo(node.e) > 0)
            node.right = add(node.right, e);

        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null)
            return false;

        if (e.compareTo(node.e) < 0)
            return contains(node.left, e);
        else if (e.compareTo(node.e) > 0)
            return contains(node.right, e);
        else
            return true;
    }
    /**  前序遍历  **/
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null)
            return;
        System.out.println(node.e); // 在前面
        preOrder(node.left);
        preOrder(node.right);
    }
    /**   中序遍历   **/
    public void inOrder() {
        inOrder(root);
    }

    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);
            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }
    }

    private void inOrder(Node node) {
        if (node == null)
            return;
        inOrder(node.left);
        System.out.println(node.e);  // 在中间 三角形
        inOrder(node.right);

    }

    public void lastOrder() {
        lastOrder(root);
    }

    private void lastOrder(Node node) {
        if (node == null)
            return;
        lastOrder(node.left);
        lastOrder(node.right);
        System.out.println(node.e);
    }

    public void levelOrder() {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node cur = q.remove();
            System.out.println(cur.e);
            if (cur.left != null)
                q.add(cur.left);
            if (cur.right != null)
                q.add(cur.right);
        }
    }

    public void removeMinimum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");
        removeMinimum(root);
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

    public void removeMaximum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");
        removeMaximum(root);
    }

    private Node removeMaximum(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMaximum(node.right);
        return node;
    }

    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null)
            return null;

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
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
            size++;
            node.left = node.right = null;
            size--;
            return successor;

        }

    }

    public void minimum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");
        System.out.println(minimum(root).e);
    }

    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    public void maximum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");
        System.out.println(maximum(root).e);
    }

    private Node maximum(Node node) {
        if (node.right == null)
            return node;
        return minimum(node.right);
    }

        @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.e + "null\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);

    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
         for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<Integer>();
        bst.add(10);
        bst.add(3);
        bst.add(5);
        bst.add(2);
        bst.add(12);
        bst.add(15);
        bst.add(11);

        System.out.println(bst.contains(1));

        bst.preOrder();
        System.out.println();

        bst.preOrderNR();
        System.out.println("inOrder");

        bst.inOrder();
        System.out.println();

        bst.lastOrder();
        System.out.println();

        bst.levelOrder();

        System.out.println();
        bst.removeMinimum();

        bst.levelOrder();

        System.out.println();
        bst.minimum();
    }
}