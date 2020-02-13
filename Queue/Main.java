import java.util.Random;

/**
 * Main
 *
 * @author LiYang
 * @date 2020/1/5
 **/
public class Main {
    private static double testQueue(Queue<Integer> q, int opCount) {
        long startTime = System.nanoTime();  // 纳秒
        Random random = new Random();
        for (int i = 0; i < opCount; i++)
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        for (int i = 0; i < opCount; i++)
            q.dequeue();
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1.0e9;
    }

    public static void main(String[] args) {
        int opCount = 100000;
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double timeArray = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue time: " + timeArray + " s");  // 0.4385833s

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double timeLoop = testQueue(loopQueue, opCount);
        System.out.println("LoopQueue time: " + timeLoop + " s"); // 0.016342s

        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        double timeLinkList = testQueue(linkedListQueue, opCount);
        System.out.println("LinkListQueue time: " + timeLinkList + " s"); // 0.0122989s
    }
}