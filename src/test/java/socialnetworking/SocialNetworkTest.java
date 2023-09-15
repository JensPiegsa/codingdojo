package socialnetworking;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class SocialNetworkTest {

    @Test @DisplayName("read empty timeline.")
    void readEmptyTimeline() {

        final SocialNetworkClient socialNetworking = new SocialNetworkClient(null);

        final String username = "alice";
        socialNetworking.reading(username);


    }

    @Nested @DisplayName("acceptance tests")
    class AcceptanceTests {


        PrintStream originalOut;
        ByteArrayOutputStream outContent;

        @BeforeEach
        void setUp() {
            outContent = new ByteArrayOutputStream();
            originalOut = System.out;
            System.setOut(new PrintStream(outContent));
        }

        @AfterEach
        void tearDown() {
            System.setOut(originalOut);
        }

        @Test @DisplayName("read empty timeline of user")
        void emptyTimeline () {
            
            final String[] args = {"alice"};
            SocialNetworkClient.main(args);

            assertThat(outContent.toString()).isEmpty();
        }
        
        @Test @DisplayName("read non-empty timeline of user.")
        void readNonEmptyTimelineOfUser() {
            
            SocialNetworkServer.main();
            
            // Alice's client 
            String username = "Alice";
            String message = "I love the weather today";
            SocialNetworkClient.main(new String[]{username, message});
            
            // Bob's client
            final String[] args = {"Alice"};
            SocialNetworkClient.main(args);

            assertThat(outContent.toString()).isNotEmpty();
            assertThat(outContent.toString()).isEqualTo("Alice - I love the weather today (5 minutes ago)");
        }
    }
}
