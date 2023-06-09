package escapethemaze;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NavigationCommands {

    private final List<NavigationCommand> commands = new ArrayList<>();
    
    public NavigationCommands add(final NavigationCommand navigationCommand) {
        commands.add(navigationCommand);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NavigationCommands that = (NavigationCommands) o;
        return Objects.equals(commands, that.commands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commands);
    }

    @Override
    public String toString() {
        return "NavigationCommands{" +
                "commands=" + commands +
                '}';
    }

    public List<Character> chars() {
        return null;
    }
}
