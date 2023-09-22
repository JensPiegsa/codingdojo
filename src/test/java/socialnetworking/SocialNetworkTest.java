package socialnetworking;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

public class SocialNetworkTest {

    private SocialNetworkServer socialNetworkServer;

    @Nested @DisplayName("acceptance tests")
    class AcceptanceTests {


        PrintStream originalOut;
        ByteArrayOutputStream outContent;

        @BeforeEach
        void setUp() {
            outContent = new ByteArrayOutputStream();
            originalOut = System.out;
            System.setOut(new PrintStream(outContent, true, StandardCharsets.UTF_8));

            socialNetworkServer = new SocialNetworkServer();
            socialNetworkServer.start();
        }

        @AfterEach
        void tearDown() {
            System.setOut(originalOut);
            socialNetworkServer.stopInstantly();
        }

        @Test @DisplayName("read empty timeline of user.")
        void emptyTimeline () {
            
            final String[] args = {"alice"};
            SocialNetworkClient.main(args);

            assertThat(outContent.toString()).isBlank();
        }
        
        @Test @DisplayName("read non-empty timeline of user.")
        void readNonEmptyTimelineOfUser() {
            
            // Alice's client 
            final String username = "Alice";
            SocialNetworkClient.main(new String[]{username, "->", "I", "love", "weather", "today"});
            
            // Bob's client
            final String[] args = {"Alice"};
            SocialNetworkClient.main(args);

            assertThat(outContent.toString()).isNotEmpty();
            assertThat(outContent.toString()).isEqualToNormalizingNewlines("Alice - I love the weather today (5 minutes ago)\n");
        }
    }
}
