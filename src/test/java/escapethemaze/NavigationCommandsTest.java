package escapethemaze;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

class NavigationCommandsTest {

    @Test @DisplayName("provides list of characters.")
    void providesListOfCharacters() {
        NavigationCommands commands = new NavigationCommands()
                .add(NavigationCommand.FORWARD)
                .add(NavigationCommand.TURN_RIGHT)
                .add(NavigationCommand.TURN_BACK)
                .add(NavigationCommand.TURN_LEFT);

        List<Character> actual = commands.chars();

        then(actual).containsExactly('F', 'R', 'B', 'L');
    }

}