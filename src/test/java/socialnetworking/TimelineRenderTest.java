package socialnetworking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TimelineRenderTest {
    @Test
    @DisplayName("can render given postings")
    void canRenderGivenPostings() {
        final LocalDateTime localDateTime = LocalDateTime.parse("2023-11-03T12:00:00.000000000");
        List<Post> posts = List.of(new Post("alice", "I love the weather today", localDateTime));
        final Clock clock = Clock.fixed(localDateTime.minusMinutes(2).toInstant(ZoneOffset.UTC), ZoneId.of("UTC"));
        TimelineRender timelineRender = new TimelineRender(clock);

        assertEquals("I love the weather today (2 minutes ago)", timelineRender.render(posts));
    }
    
    @Test @DisplayName("can calculate time exactly.")
    void canCalculateTimeExactly() {
        final ZonedDateTime zonedDateTime = ZonedDateTime.parse("2023-11-03T12:00:00.000000000");
        List<Post> posts = List.of(new Post("alice", "I love the weather today", zonedDateTime));
        final Clock clock = Clock.fixed(zonedDateTime.minusMinutes(3).toInstant(ZoneOffset.UTC), ZoneId.of("UTC"));
        TimelineRender timelineRender = new TimelineRender(clock);

        assertEquals("I love the weather today (3 minutes ago)", timelineRender.render(posts));
    }
}