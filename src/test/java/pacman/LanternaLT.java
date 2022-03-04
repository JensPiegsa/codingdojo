package pacman;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.StandardCharsets;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Lanterna Text Graphics (learning test)")
public class LanternaLT {

	@SuppressWarnings("resource")
	@Test @DisplayName("test")
	void test() throws Exception {

		final DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory(System.out, System.in, StandardCharsets.UTF_8);
		final Terminal terminal = terminalFactory.createTerminal();
		terminal.enterPrivateMode();

		terminal.clearScreen();

		terminal.setCursorPosition(0, 0);
		terminal.putString("Hello World!");
		terminal.flush();

		Thread.sleep(50L);
		terminal.setCursorPosition(0, 0);
		terminal.putString("Hello Pac!");
		terminal.flush();

		final TextGraphics textGraphics = terminal.newTextGraphics();
		
		assertThat(textGraphics.getCharacter(0,0).getCharacterString()).isEqualTo("H");
		assertThat(textGraphics.getCharacter(8,0).getCharacterString()).isEqualTo("c");
		assertThat(textGraphics.getCharacter(10,0).getCharacterString()).isEqualTo("d");
		
		Thread.sleep(50L);
	}
}
