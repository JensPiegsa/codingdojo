package tcp;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

@DisplayName("A TransitionFunction")
class TransitionFunctionTest {

    @Test
    void canBeReadFromFile() throws URISyntaxException {
        final Path file = Paths.get(TransitionFunctionTest.class.getResource("tcp.transitions").toURI());
        assertThat(file).isRegularFile();
        final States<TcpState> states = States.from(TcpState.class);
        final Alphabet<TcpSymbol> alphabet = Alphabet.from(TcpSymbol.class);
        
        final TransitionFunction<TcpState, TcpSymbol> function = TransitionFunction.fromFile(file, states, alphabet);

        then(function.toString()).contains("CLOSED: APP_PASSIVE_OPEN -> LISTEN");
    }


}