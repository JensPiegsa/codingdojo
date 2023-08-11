package tcp;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Jens Piegsa
 */
@DisplayName("A TCP")
class TCPTest {
    
    @Test @DisplayName("unknown input result in error.")
    void unknownInputResultInError() {
    	
        assertThat(TCP.traverseStates(new String[]{"unknown"})).isEqualTo("ERROR");
    }

    @Test @DisplayName("app active open input results in SYN_SENT state")
    void test2(){
        assertThat(TCP.traverseStates(new String[]{"APP_ACTIVE_OPEN"})).isEqualTo("SYN_SENT");
    }

    @Test @DisplayName("establish connection.")
    void establishConnection() {
    	final String[] input = {"APP_PASSIVE_OPEN", "APP_SEND", "RCV_SYN_ACK"};
        assertThat(TCP.traverseStates(input)).isEqualTo("ESTABLISHED");
    }
}