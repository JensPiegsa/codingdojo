package tcp;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Jens Piegsa
 */
public class States<T> {

    private List<T> allStates;
    private final Map<String, T> statesByString;

    public States(final List<T> allStates) {
        this.allStates = allStates;
        statesByString = allStates.stream()
                .collect(toMap(Objects::toString, identity()));
    }

    public static <E extends Enum<E>> States<E> from(final EnumSet<E> states) {
        return new States<>(states.stream().toList());
    }

    public static <E extends Enum<E>> States<E> from(Class<E> enumClass) {
        return from(EnumSet.allOf(enumClass));
    }

    @Override
    public String toString() {
        final String states = allStates.stream()
                .map(Objects::toString)
                .collect(joining(", "));
        return "{" + states + "}";
    }

    public T parse(final String string) {
        if (!statesByString.containsKey(string)) {
            System.err.println("Unknown state " + string);
        }
        return statesByString.get(string);
    }
}
