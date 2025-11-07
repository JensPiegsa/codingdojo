package threadedcounting;

public class ThreadedCounting {

    private static int numberOfThreads = 3;

    public static void countInThreads(Counter counter) {
        for (int i = 1; i <= 100; i++) {
            counter.count(i);
        }
    }
}
