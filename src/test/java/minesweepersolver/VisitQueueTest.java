package minesweepersolver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class VisitQueueTest {
    @Test
    @DisplayName("added Visit can be polled.")
    void addedVisitCanBePolled() {
        VisitQueue visitQueue = new VisitQueue();
        Visit visit = new Visit(new Position(0, 0), Strategy.KNOWN_BORDER);
        visitQueue.add(visit);

        Visit polldedVisit = visitQueue.poll();
        assertThat(polldedVisit).isSameAs(visit);
    }

    @Test
    @DisplayName("replaces low priority visit with high priority visit.")
    void replacesLowPriorityVisitWithHighPriorityVisit() {

        VisitQueue visitQueue = new VisitQueue();
        Visit visit = new Visit(new Position(0, 0), Strategy.KNOWN_BORDER);
        Visit prioVisit = new Visit(new Position(0, 0), Strategy.SATURATED);
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
        Visit visit = new Visit(new Position(1, 0), Strategy.KNOWN_BORDER);
        Visit prioVisit = new Visit(new Position(0, 0), Strategy.SATURATED);
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
        Visit visit = new Visit(new Position(0, 0), Strategy.KNOWN_BORDER);
        Visit equalVisit = new Visit(new Position(0, 0), Strategy.KNOWN_BORDER);
        visitQueue.add(visit);
        visitQueue.add(equalVisit);

        Visit polldedVisit = visitQueue.poll();
        assertThat(polldedVisit).isEqualTo(equalVisit);
        assertThat(visitQueue.isEmpty()).isTrue();
    }
}