package threadedcounting;

import java.util.ArrayList;
import java.util.List;

public class ThreadedCounting {

    private static int threadCount = 3;

    public static void countInThreads(Counter counter) throws InterruptedException {

        List<Thread> threads = new ArrayList<Thread>();
        for (int i = 1; i <= threadCount; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                for (int j = finalI; j <= 100; j+= threadCount) {
                    counter.count(j);
                }
            }, "Thread" + i);
            threads.add(thread);
            thread.start();
        }
        Thread.sleep(500);
    }
}
