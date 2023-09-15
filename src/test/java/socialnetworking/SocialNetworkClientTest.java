package socialnetworking;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

@WireMockTest
class SocialNetworkClientTest {

    @InjectMocks SocialNetworkClient client;
    
    @Test @DisplayName("can read empty timeline from server.")
    void canReadEmptyTimelineFromServer(final WireMockRuntimeInfo wiremockRuntimeInfo) {
        
    	final String resultBodyContent = "";
        stubFor(get("/sns").withHeader("Accept", containing("text/plain")).willReturn(ok(resultBodyContent)));

        final SocialNetworkClient client = new SocialNetworkClient(wiremockRuntimeInfo.getHttpBaseUrl());
        String output = client.reading("bob");

        verify(getRequestedFor(urlEqualTo("/sns")).withHeader("Accept", containing("text/plain")));
        then(output).isEmpty();
    }

    @Test @DisplayName("can read single line from timeline from server.")
    void canReadSingleLineTimelineFromServer(final WireMockRuntimeInfo wiremockRuntimeInfo) {

        final String resultBodyContent = "Alice - I love the weather today (5 minutes ago)";
        stubFor(get("/sns").withHeader("Accept", containing("text/plain")).willReturn(ok(resultBodyContent)));

        final SocialNetworkClient client = new SocialNetworkClient(wiremockRuntimeInfo.getHttpBaseUrl());
        String output = client.reading("bob");

        verify(getRequestedFor(urlEqualTo("/sns")));
        then(output).isEqualTo("Alice - I love the weather today (5 minutes ago)");
    }
}