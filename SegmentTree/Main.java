/**
 * Author : 13152
 * Date : 2020/2/5
 * Time : 18:10
 * Version ： 1.0.0
 **/
public class Main {
    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, Integer::sum);
        System.out.println(segmentTree);
        System.out.println();
        System.out.println(segmentTree.query(0, 2));
    }
}
