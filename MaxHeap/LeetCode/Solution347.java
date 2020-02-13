import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.*;
import java.util.PriorityQueue;
/**
 * Author : 13152
 * Date : 2020/2/5
 * Time : 10:03
 * Version ： 1.0.0
 **/
public class Solution347 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int num : nums) {
            if (treeMap.containsKey(num))
                treeMap.put(num, treeMap.get(num) + 1);
            else
                treeMap.put(num, 1);
        }
        PriorityQueue<Integer> pg = new PriorityQueue<>((a, b)->treeMap.get(a) - treeMap.get(b));
        for (int key : treeMap.keySet()) {
            if (pg.size() < k) {
                pg.add(key);
            }else if (treeMap.get(key) > treeMap.get(pg.peek())){
                pg.poll();
                pg.add(key);
            }
        }
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            list.add(pg.poll());
        }
        return list;
    }
}
