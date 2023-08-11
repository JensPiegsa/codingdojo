package tcp;

import static java.util.stream.Collectors.*;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jens Piegsa
 */
public class TransitionFunction<STATE, SYMBOL> {

    public static final Pattern pattern = Pattern.compile("([^:]+): +([^ ]+) +-> +(.+)");
    private final Map<Key, STATE> f;

    public TransitionFunction(final List<String> transitionLines, States<STATE> states, Alphabet<SYMBOL> alphabet) {
        f = transitionLines.stream()
                .map(line -> parse(line, states, alphabet))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

    private Map.Entry<Key, STATE> parse(String line, States<STATE> states, Alphabet<SYMBOL> alphabet) {
        
        final Matcher matcher = pattern.matcher(line);
        
        if (matcher.matches()) {
            final STATE initialState = states.parse(matcher.group(1));
            final SYMBOL inputSymbol = alphabet.parse(matcher.group(2));
            final STATE resultState = states.parse(matcher.group(3));
            // TODO how to get objects for string
            return Map.entry(new Key(initialState, inputSymbol), resultState);
        }
        throw new IllegalArgumentException("Cannot parse line " + line);
    }

    public STATE doTransition(STATE currentState, SYMBOL symbol) {
        STATE state = f.get(new Key(currentState, symbol));
        if (state == null) {
            throw new MissingTransitionException();
        }
        return state;
    }

    private class Key {
        STATE state;
        SYMBOL symbol;

        public Key(STATE state, SYMBOL symbol) {
            this.state = state;
            this.symbol = symbol;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return state.equals(key.state) && symbol.equals(key.symbol);
        }

        @Override
        public int hashCode() {
            return Objects.hash(state, symbol);
        }

        public STATE getState() {
            return state;
        }

        public SYMBOL getSymbol() {
            return symbol;
        }
    }
            
    public static <STATE, SYMBOL> TransitionFunction<STATE, SYMBOL> fromFile(final Path file, States<STATE> states, Alphabet<SYMBOL> alphabet) {
        try {
            final List<String> transitionLines = Files.readAllLines(file);
            return fromLines(transitionLines, states, alphabet);
        } catch (final IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static TransitionFunction fromLines(List<String> transitionLines, States states, Alphabet alphabet) {
        return new TransitionFunction(transitionLines, states, alphabet);
    }

    @Override
    public String toString() {
        return f.entrySet().stream()
                .map(e -> e.getKey().getState() + ": " + e.getKey().getSymbol() + " -> " + e.getValue())
                .collect(joining("\n"));
    }
}
