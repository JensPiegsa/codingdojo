package pacman;

import java.util.Arrays;

// TODO how to represent the board - options
// [x] PacManBoard
// char[][]
// enum PacManTile[][]
// class PacManPosition[][]
// List<List<Character>> rows
// List<List<PacManTile>> rows
// List<List<Character>> columns
// List<List<PacManTile>> columns
// ~~~Table<???> columns~~~
public class PacManBoard {

	public enum PacManTile {
		pacManLeft('>'),
		pacManUp('V'),
		pacManRight('<'),
		pacManDown('^'),
		empty(' '),
		dot('.')
		;

		private final char c;

		PacManTile(final char c) {
			this.c = c;
		}

		@Override
		public String toString() {
			return "" + c;
		}

		public static PacManTile forCharacter(final char c) {
			for (PacManTile pacManTile : values()) {
				if (pacManTile.c == c) {
					return pacManTile;
				}
			}
			throw new IllegalArgumentException("unknown character: " + c);
		}
	}

	private PacManTile[][] tiles;
	private final int boardWidth;
	private final int boardHeight;

	public PacManBoard(final int boardWidth, final int boardHeight, final PacManTile[][] tiles) {
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		this.tiles = tiles;
	}

	public static PacManBoard parse(final String board) {

		final String[] rows = board.split("\n");
		final int boardWidth = Arrays.stream(rows).mapToInt(String::length).max().getAsInt();
		final int boardHeight = rows.length;
		final PacManTile[][] tiles = new PacManTile[boardWidth][boardHeight];
		for (int y = 0; y < boardHeight; y++) {
			for (int x = 0; x < boardWidth; x++) {
				final char c = x < rows[y].length() ? rows[y].charAt(x) : ' ';
				final PacManTile pacManTile = PacManTile.forCharacter(c);
				tiles[x][y] = pacManTile;
			}
		}

		final PacManBoard pacManBoard = new PacManBoard(boardWidth, boardHeight, tiles);
		return pacManBoard;
	}

	public int getBoardHeight() {
		return boardHeight;
	}

	public int getBoardWidth() {
		return boardWidth;
	}

	@Override
	public String toString() {
		String s = "";
		for (int y = 0; y < boardHeight; y++) {
			for (int x = 0; x < boardWidth; x++) {
				s += tiles[x][y].toString();
			}
			s += '\n';
		}
		return s;
	}
}
