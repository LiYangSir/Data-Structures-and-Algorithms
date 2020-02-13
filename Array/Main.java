
public class Main {
    public static void main(String[] args) {
        Array<Integer> array = new Array<>();
        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }
        System.out.println(array);

        array.add(1, 100);
        System.out.println(array);

        array.remove(3);
        System.out.println(array);

//        array.addFirst(9);
//        array.addFirst(9);
//        array.addFirst(9);
//        System.out.println(array);
//
//        array.removeElement(9);
//        System.out.println(array);
//
//        array.removeAllElement(9);
//        System.out.println(array);


    }
}
