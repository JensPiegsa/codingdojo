package socialnetworking;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

@WireMockTest
class SocialNetworkClientTest {

    SocialNetworkClient client;
    
    @BeforeEach
    void setUp(final WireMockRuntimeInfo wiremockRuntimeInfo) {
        client = new SocialNetworkClient(wiremockRuntimeInfo.getHttpBaseUrl());
    }
    
    @Test @DisplayName("can read empty timeline from server.")
    void canReadEmptyTimelineFromServer() {
        
    	final String resultBodyContent = "";
        stubFor(get("/sns").withHeader("Accept", containing("text/plain")).willReturn(ok(resultBodyContent)));

        String output = client.reading("bob");

        verify(getRequestedFor(urlEqualTo("/sns")).withHeader("Accept", containing("text/plain")));
        then(output).isEmpty();
    }

    @Test @DisplayName("can read single line from timeline from server.")
    void canReadSingleLineTimelineFromServer(final WireMockRuntimeInfo wiremockRuntimeInfo) {

        final String resultBodyContent = "Alice - I love the weather today (5 minutes ago)";
        stubFor(get("/sns").withHeader("Accept", containing("text/plain")).willReturn(ok(resultBodyContent)));

        String output = client.reading("bob");

        verify(getRequestedFor(urlEqualTo("/sns")));
        then(output).isEqualTo("Alice - I love the weather today (5 minutes ago)");
    }
    
    @Test @DisplayName("can post message.")
    void canPostMessage() {
        
    	client.post("Alice", "I love the weather today");

        verify(postRequestedFor(urlEqualTo("/sns")).withRequestBody(equalTo("Alice -> I love the weather today")));
    }
}