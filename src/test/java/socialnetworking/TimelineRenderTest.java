package socialnetworking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TimelineRenderTest {
    @Test
    @DisplayName("can render given postings")
    void canRenderGivenPostings() {
        final LocalDateTime localDateTime = LocalDateTime.parse("2023-11-03T12:00:00.000000000");
        List<Post> posts = List.of(new Post("alice", "I love the weather today", localDateTime));
        TimelineRender timelineRender = new TimelineRender();

        assertEquals("I love the weather today (2 minutes ago)", timelineRender.render(posts));
    }
}