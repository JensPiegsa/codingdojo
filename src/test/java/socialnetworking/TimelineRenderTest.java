package socialnetworking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TimelineRenderTest {

    // TODO switch for precise/approximate
    // TODO switch for relative/absolute
    // TODO threshold for relative/absolute time

    @Test @DisplayName("can calculate time exactly.")
    void canCalculateTimeExactly() {
        final ZonedDateTime postingDateTime = ZonedDateTime.parse("2023-11-03T12:00:00.000000000Z");
        List<Post> posts = List.of(new Post("alice", "I love the weather today", postingDateTime));
        final Clock now = Clock.fixed(postingDateTime.plusMinutes(3).toInstant(), ZoneId.of("UTC"));
        TimelineRender timelineRender = new TimelineRender(now);

        String result = timelineRender.render(posts);

        assertThat(result).isEqualTo("I love the weather today (3 minutes ago)");
    }
}