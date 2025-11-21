package threadedcounting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class ThreadedCountingTest {

    List<Integer> list1 = IntStream.iterate(1, i -> i <= 100, i -> i + 3).boxed().toList();
    List<Integer> list2 = IntStream.iterate(2, i -> i <= 100, i -> i + 3).boxed().toList();
    List<Integer> list3 = IntStream.iterate(3, i -> i <= 100, i -> i + 3).boxed().toList();

    @Test
    @DisplayName("can count")
    void canCount() throws InterruptedException {

        Counter counter = new Counter();

        ThreadedCounting.countInThreads(counter);

        List<Integer> numbers = counter.getNumbers();
        assertThat(numbers).isEqualTo(
                IntStream.rangeClosed(1, 100)
                .boxed()
                .toList()
        );
    }
    // TODO write property based test (Jquik)

    @Test @DisplayName("every thread can count its numbers correctly in isolation (global order ignored).")
    void everyThreadCanCountItsNumber() throws InterruptedException {
        Counter counter = new Counter();

        ThreadedCounting.countInThreads(counter);

        List<Long> threadIds = counter.getThreadIds();
        List<Integer> numbersOne = counter.getNumbersInSameThreadAs(threadIds.get(0));
        List<Integer> numbersTwo = counter.getNumbersInSameThreadAs(threadIds.get(1));
        List<Integer> numbersThree = counter.getNumbersInSameThreadAs(threadIds.get(2));

        Set<List<Integer>> threadNumbers = Set.of(numbersOne, numbersTwo, numbersThree);

        assertThat(threadNumbers).containsExactlyInAnyOrder(list1, list2, list3);
    }

    @Test
    @DisplayName("can count in three threads according to the required pattern.")
    void canCountInThreeThreadsAccordingToTheRequiredPattern() throws InterruptedException {

        System.out.println(list1);
        System.out.println();
        System.out.println(list2);
        System.out.println();
        System.out.println(list3);

        Counter counter = new Counter();

        ThreadedCounting.countInThreads(counter);

        List<Long> threadIds = counter.getThreadIds();
        assertThat(threadIds).hasSize(3);

        Set<List<Integer>> threadNumbers = new LinkedHashSet<>();
        threadNumbers.add(counter.getNumbersInSameThreadAs(threadIds.get(0)));
        threadNumbers.add(counter.getNumbersInSameThreadAs(threadIds.get(1)));
        threadNumbers.add(counter.getNumbersInSameThreadAs(threadIds.get(2)));

        assertThat(threadNumbers).containsExactlyInAnyOrder(list1, list2, list3);
    }
}