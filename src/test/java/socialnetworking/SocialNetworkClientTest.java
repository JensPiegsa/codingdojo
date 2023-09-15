package socialnetworking;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;


//@ExtendWith(WireMockExtension.class)
@WireMockTest
class SocialNetworkClientTest {

    @InjectMocks SocialNetworkClient client;
    
    @Test @DisplayName("can read empty timeline from server.")
    void canReadEmptyTimelineFromServer(WireMockRuntimeInfo wiremockRuntimeInfo) {
        
    	final String resultBodyContent = "";
        
        // TODO mock/assert the answer
        stubFor(get("/sns").willReturn(ok(resultBodyContent)));


        // TODO invoke the client
        SocialNetworkClient client = new SocialNetworkClient(wiremockRuntimeInfo.getHttpBaseUrl());
        client.reading("bob");

        WireMock.verify(getRequestedFor(urlEqualTo("/sns")));
    }
}