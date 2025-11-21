package threadedcounting;

import java.util.*;

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class Counter {
    private static final System.Logger log = System.getLogger("Counter");

    private final List<Integer> numbers = new ArrayList<>();
    private final Map<Long, List<Integer>> numbersByThreadId = new LinkedHashMap<>();

    public void count(int number) {
        numbers.add(number);
        long threadId = Thread.currentThread().threadId();
        numbersByThreadId.computeIfAbsent(threadId, ignored -> new ArrayList<>(List.of(number)));

        log.log(System.Logger.Level.INFO, number);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public List<Long> getThreadIds() {
        return numbersByThreadId.keySet()
                .stream()
                .toList();
    }

    public List<Integer> getNumbersInSameThreadAs(long threadId) {
        return numbersByThreadId.getOrDefault(threadId, new ArrayList<>());
    }
}
