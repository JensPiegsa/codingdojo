package minesweepersolver;

import java.util.*;

public class VisitQueue {
    PriorityQueue<Visit> queue = new PriorityQueue<>();

    public VisitQueue() {
    }

    public VisitQueue(List<Visit> visits) {
        visits.forEach(this::add);
    }

    public void add(Visit visit) {
        List<Visit> lowPrioDuplicates = new ArrayList<>();
        for (Visit qVisit : queue) {
            if (qVisit.getPosition().equals(visit.getPosition())
            && qVisit.compareTo(visit) >= 0) {
                lowPrioDuplicates.add(qVisit);
            }
        }
        lowPrioDuplicates.forEach(queue::remove);
        queue.add(visit);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public Visit poll() {
        return queue.poll();
    }

    public Iterable<Visit> toIterable() {
        return Collections.unmodifiableCollection(queue);
    }
}
