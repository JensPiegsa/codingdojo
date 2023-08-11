package tcp;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;

public class TCP {
    
    public static String traverseStates(String[] events) {
        
        try {

            final States<TcpState> states = States.from(TcpState.class);
            final Alphabet<TcpSymbol> alphabet = Alphabet.from(TcpSymbol.class);

            final Path file = Paths.get(TransitionFunction.class.getResource("tcp.transitions").toURI());
            final TransitionFunction<TcpState, TcpSymbol> transitionFunction = TransitionFunction.fromFile(file, states, alphabet);
            final TcpState initialState = TcpState.CLOSED;
            final States<TcpState> acceptStates = null;

            final var dfa = new DFA<>(states, alphabet, transitionFunction, initialState, acceptStates);
            final TcpState finalState = dfa.process(InputSymbols.from(events, alphabet));
            return finalState.toString();
        } catch (IllegalArgumentException e) {
            return "ERROR";
        } catch (final URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}