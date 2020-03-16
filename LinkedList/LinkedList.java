/**
 * LinkedList
 *
 * @author LiYang
 * @date 2020/1/7
 * 增删改查的时间复杂度均为O(n)
 * 比较适合增加删除链表头的元素以及查找链表的元素
 **/
public class LinkedList<E> {
    private class table{
        public table(E e, table note) {
            this.e = e;
            this.next = note;
        }

        public table(E e) {
            this(e, null);
        }

        public table() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }

        E e;
        table next;
    }

    private int size;
    private table dummyHead;

    public LinkedList(){
        dummyHead = new table(null, null);
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("add is error, index need index < 0 || index > size ");
        table pre = dummyHead;
        for (int i = 0; i < index; ++i)
            pre = pre.next;
        pre.next = new table(e, pre.next);
        size++;
    }

    public void addLast(E e) {
        add(size, e);
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public E get(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("get is error, index need index < 0 || index > size ");
        table pre = dummyHead.next;
        for (int i = 0; i < index; ++i)
            pre = pre.next;
        return pre.e;
    }

    public void set(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("set is error, index need index < 0 || index > size ");
        table pre = dummyHead.next;
        for (int i = 0; i < index; ++i)
            pre = pre.next;
        pre.e = e;
    }

    public boolean contains(E e) {
        table currentTable = dummyHead.next;
        while (currentTable != null) {
            if (currentTable.e == e)
                return true;
            currentTable = currentTable.next;
        }
        return false;
    }

    public int find(E e) {
        int index = 0;
        table currentTable = dummyHead.next;
        while (currentTable != null) {
            if (currentTable.e == e)
                return index;
            index++;
            currentTable = currentTable.next;
        }
        return -1;
    }

    public E remove(int index){
        if (index < 0 || index > size)
            throw new IllegalArgumentException("set is error, index need index < 0 || index > size ");
        table pre = dummyHead;
        for (int i = 0; i < index; ++i)
            pre = pre.next;
        size--;
        table retNode = pre.next;
        pre.next = retNode.next;
        retNode.next = null;
        return retNode.e;
    }

    public void removeElement(E e) {
        table pre = dummyHead;
        for (int i = 0; i < size; i++) {
            if (pre.next.e == e) {
                size--;
                table retNode = pre.next;
                pre.next = retNode.next;
                retNode.next = null;
                return;
            }
            pre = pre.next;
        }
    }
    public E removeFirst() {
        return remove(0);
    }

    public E removeLast(){
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (table cur = dummyHead.next; cur != null; cur = cur.next) {
            String str = cur.e + "->";
            res.append(str);
        }
        res.append("Null");
        return res.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(11);
        linkedList.addLast(12);
        linkedList.addLast(13);
        linkedList.addLast(14);
        System.out.println(linkedList);

        linkedList.remove(1);
        System.out.println(linkedList);

        System.out.println(linkedList.find(13));

        System.out.println(linkedList.get(2));


    }
}
