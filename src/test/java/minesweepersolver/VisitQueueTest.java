package minesweepersolver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

class VisitQueueTest {
    @Test
    @DisplayName("added Visit can be polled.")
    void addedVisitCanBePolled() {
        VisitQueue visitQueue = new VisitQueue();
        Visit visit = new Visit(new Position(0, 0), Strategy.UNKNOWN_BORDER);
        visitQueue.add(visit);

        Visit polldedVisit = visitQueue.poll();
        assertThat(polldedVisit).isSameAs(visit);
    }

    @Test
    @DisplayName("replaces low priority visit with high priority visit.")
    void replacesLowPriorityVisitWithHighPriorityVisit() {

        VisitQueue visitQueue = new VisitQueue();
        Visit visit = new Visit(new Position(0, 0), Strategy.UNKNOWN_BORDER);
        Visit prioVisit = new Visit(new Position(0, 0), Strategy.OPEN);
        visitQueue.add(visit);
        visitQueue.add(prioVisit);

        Visit polldedVisit = visitQueue.poll();
        assertThat(polldedVisit).isSameAs(prioVisit);
        assertThat(visitQueue.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("priority visit will be polled first.")
    void priorityVisitWillBePolledFirst() {

        VisitQueue visitQueue = new VisitQueue();
        Visit visit = new Visit(new Position(1, 0), Strategy.UNKNOWN_BORDER);
        Visit prioVisit = new Visit(new Position(0, 0), Strategy.OPEN);
        visitQueue.add(visit);
        visitQueue.add(prioVisit);

        Visit polldedVisit = visitQueue.poll();
        assertThat(polldedVisit).isSameAs(prioVisit);
        polldedVisit = visitQueue.poll();
        assertThat(polldedVisit).isSameAs(visit);
    }

    @Test
    @DisplayName("adding same priority visit keeps queue unaffected.")
    void addingSamePriorityVisitKeepsQueueUnaffected() {

        VisitQueue visitQueue = new VisitQueue();
        Visit visit = new Visit(new Position(0, 0), Strategy.UNKNOWN_BORDER);
        Visit equalVisit = new Visit(new Position(0, 0), Strategy.UNKNOWN_BORDER);
        visitQueue.add(visit);
        visitQueue.add(equalVisit);

        Visit polldedVisit = visitQueue.poll();
        assertThat(polldedVisit).isEqualTo(equalVisit);
        assertThat(visitQueue.isEmpty()).isTrue();
    }

    @Test @DisplayName("remove visit on Position.")
    void removeVisitOnPosition() {
        VisitQueue visitQueue = new VisitQueue();

        visitQueue.remove(new Position(0, 0));

        then(visitQueue.toIterable()).isEmpty();

        Visit visit = new Visit(new Position(0, 0), Strategy.UNKNOWN_BORDER);
        visitQueue.add(visit);

        then(visitQueue.toIterable()).containsExactly(visit);

        visitQueue.remove(new Position(1, 0));

        then(visitQueue.toIterable()).containsExactly(visit);

        visitQueue.remove(new Position(0, 0));

        then(visitQueue.toIterable()).isEmpty();
    }

    @Test @DisplayName("can return size.")
    void canReturnSize() {
        VisitQueue visitQueue = new VisitQueue();

        int queueSizeBefore = visitQueue.size();

        Visit visitOne = new Visit(new Position(0, 0), Strategy.UNKNOWN_BORDER);
        visitQueue.add(visitOne);

        int queueSizeInBetween = visitQueue.size();

        Visit visitTwo = new Visit(new Position(1, 1), Strategy.UNKNOWN_BORDER);
        visitQueue.add(visitTwo);

        int queueSizeAfter = visitQueue.size();

        assertThat(queueSizeBefore).isEqualTo(0);
        assertThat(queueSizeInBetween).isEqualTo(1);
        assertThat(queueSizeAfter).isEqualTo(2);
    }

    @Test @DisplayName("dont add visit if higher priority visit exists.")
    void dontAddVisitIfHigherPriorityVisitExists() {
        VisitQueue visitQueue = new VisitQueue();
        Visit visit = new Visit(new Position(0, 0), Strategy.UNKNOWN_BORDER);
        Visit prioVisit = new Visit(new Position(0, 0), Strategy.OPEN);
        visitQueue.add(prioVisit);
        visitQueue.add(visit);

        Visit polldedVisit = visitQueue.poll();
        assertThat(polldedVisit).isSameAs(prioVisit);
        assertThat(visitQueue.isEmpty()).isTrue();
    }
}