package socialnetworking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("InMemoryPostStore")
class InMemoryPostStoreTest {

    @Test @DisplayName("can persist post.")
    void canPersistPost() {
        final InMemoryPostStore inMemoryPostStore = new InMemoryPostStore();

        final ZonedDateTime postingDateTime = ZonedDateTime.parse("2023-11-03T12:00:00.000000000Z");
        final Post firstPostOfAlice = new Post("alice", "I love the weather today", postingDateTime);
        final Post postOfBob = new Post("bob", "I do not love the weather today", postingDateTime.plusMinutes(1));
        final Post secondPostOfAlice = new Post("alice", "I love the weather today !!!", postingDateTime.plusMinutes(2));

        inMemoryPostStore.persist(firstPostOfAlice);
        inMemoryPostStore.persist(secondPostOfAlice);
        inMemoryPostStore.persist(postOfBob);

        then(inMemoryPostStore.readTimeline("alice")).containsExactly(firstPostOfAlice, secondPostOfAlice);
    }
}