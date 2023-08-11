package tcp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 * @author Jens Piegsa
 */
public class InputSymbols<SYMBOL> {
    
    private List<SYMBOL> symbols;
    private final Alphabet<SYMBOL> alphabet;

    public InputSymbols(List<SYMBOL> symbols, Alphabet<SYMBOL> alphabet) {
        this.symbols = symbols;
        this.alphabet = alphabet;
    }

    public static <SYMBOL> InputSymbols<SYMBOL> from(final String[] events, Alphabet<SYMBOL> alphabet) {
        
        List<SYMBOL> symbols = Arrays.stream(events)
                .map(alphabet::parse)
                .collect(toList());

        return new InputSymbols<>(symbols, alphabet);
    }

    public List<SYMBOL> getSymbols() {
        return Collections.unmodifiableList(symbols);
    }
}
