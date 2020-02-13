/**
 * ArrayStack
 *
 * @author LiYang
 * @date 2020/1/4
 * 和链表栈的时间复相杂度同
 **/
public class ArrayStack<E> implements Stack<E> {

    private Array<E> array;

    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayStack() {
        array = new Array<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack:");
        res.append("[");
        for (int i = 0; i < getSize(); i++) {
            res.append(array.get(i));
            if (i!=getSize()-1)
                res.append(", ");
        }
        res.append("] -> top");
        return res.toString();
    }
}
