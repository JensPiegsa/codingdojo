package tcp;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Jens Piegsa
 */
public class Alphabet<T> {
    
    private final List<T> allSymbols;
    private final Map<String, T> symbolsByString;

    public Alphabet(final List<T> allSymbols) {
        this.allSymbols = allSymbols;
        symbolsByString = allSymbols.stream()
                .collect(toMap(Objects::toString, identity()));
    }

    public static <E extends Enum<E>> Alphabet<E> from(final EnumSet<E> symbols) {
        return new Alphabet<>(symbols.stream().toList());
    }

    public static <E extends Enum<E>> Alphabet<E> from(final Class<E> enumClass) {
        return from(EnumSet.allOf(enumClass));
    }

    @Override
    public String toString() {
        final String symbols = allSymbols.stream()
                .map(Objects::toString)
                .collect(joining(", "));
        return "{" + symbols + "}";
    }

    public T parse(final String string) {
        if (!symbolsByString.containsKey(string)) {
            System.err.println("Unknown symbol " + string);
        }
        return symbolsByString.get(string);
    }
}
