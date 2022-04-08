package pacman;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("A PacManTerminal")
@ExtendWith(MockitoExtension.class)
class PacManTerminalTest {

	@Mock Terminal terminal;
	@InjectMocks PacManTerminal pacManTerminal;

	@Captor	ArgumentCaptor<String> lineArg;
	@Captor	ArgumentCaptor<Integer> xArg;
	@Captor	ArgumentCaptor<Integer> yArg;

	@Test @DisplayName("sends split multi-line string to expected position on terminal.")
	void sendsSplitMultiLineStringToExpectedPositionOnTerminal() throws IOException {
		
		final var multiline = """
				123
				456
				""";

		pacManTerminal.printMultilineTextAtUpperLeftCorner(multiline);

		verify(terminal, times(2)).setCursorPosition(xArg.capture(), yArg.capture());
		then(xArg.getAllValues()).containsExactly(0, 0);
		then(yArg.getAllValues()).containsExactly(0, 1);
		verify(terminal, times(2)).putString(lineArg.capture());
		then(lineArg.getAllValues()).containsExactly("123", "456");
	};
}