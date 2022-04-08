package pacman;

import java.io.IOException;
import com.googlecode.lanterna.terminal.Terminal;

public class PacManTerminal implements PacManOutput {

	Terminal terminal;

	@Override
	public void printMultilineTextAtUpperLeftCorner(final String string) {
		try {
			int lineCounter = 0;
			for (final String line : string.split("\n")) {
				terminal.setCursorPosition(0, lineCounter);
				terminal.putString(line);
				lineCounter++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
