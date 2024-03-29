package tcp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runners.JUnit4;

// TODO: Replace examples and use TDD development by writing your own tests

public class SolutionTest {

    @Test
    public void SampleTests() {
        assertEquals("CLOSE_WAIT",   TCP.traverseStates(new String[] {"APP_ACTIVE_OPEN","RCV_SYN_ACK","RCV_FIN"}));
        assertEquals("ESTABLISHED",  TCP.traverseStates(new String[] {"APP_PASSIVE_OPEN", "RCV_SYN","RCV_ACK"}));
        assertEquals("LAST_ACK",     TCP.traverseStates(new String[] {"APP_ACTIVE_OPEN","RCV_SYN_ACK","RCV_FIN","APP_CLOSE"}));
        assertEquals("SYN_SENT",     TCP.traverseStates(new String[] {"APP_ACTIVE_OPEN"}));
        assertEquals("ERROR",        TCP.traverseStates(new String[] {"APP_PASSIVE_OPEN","RCV_SYN","RCV_ACK","APP_CLOSE","APP_SEND"}));
    }
}