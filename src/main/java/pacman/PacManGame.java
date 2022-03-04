package pacman;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class PacManGame {


	private PacManBoard board;

	public PacManGame(final String board) {
		this.board = PacManBoard.parse(board);
	}

	@Override
	public String toString() {
		return board.toString();
	}

	public void move(final Direction direction) {
		board.move(direction);
	}

	public int getBoardHeight() {
		return board.getBoardHeight();
	}

	public int getBoardWidth() {
		return board.getBoardWidth();
	}

	public String getBoard() {
		return board.getBoard();
	}

	public static void main(String[] args) {
		
		// basic examples at https://nikolaydimov83.wordpress.com/2014/05/30/create-console-game-in-java/
		
		try {
			final DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory(System.out, System.in, StandardCharsets.UTF_8);
			final Terminal terminal;
			terminal = terminalFactory.createTerminal();
			terminal.enterPrivateMode();
			
			// FIXME size not adjustable
//			final TerminalSize terminalSize = terminal.getTerminalSize();
//			terminalSize.withColumns(40);
//			terminalSize.withColumns(45);
			
			terminal.clearScreen();
			
			terminal.setCursorPosition(0, 0);
			terminal.putString("Hello World!");
			terminal.flush();
			
			Thread.sleep(5000L);
			terminal.setCursorPosition(0, 0);
			terminal.putString("Hello Pac!");
			terminal.flush();
			
			final TextGraphics textGraphics = terminal.newTextGraphics();
			// TODO textGraphics.explore...
			
			// TODO how to create a "screen"? (@see terminal.newTextGraphics() javadoc)
			
			// TODO use getChar or similar measures to write automated tests
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void next() {
		move(Direction.right);
	}
}
