package minesweepersolver;

public class BoardBuilder {
    public static final int MINE = -1;
    private final int rows;
    private final int columns;
    private int[][] board;
    private int[][] uncoveredAreas;

    private BoardBuilder(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        board = new int[rows][columns];
        uncoveredAreas = new int[rows][columns];
    }


    public static BoardBuilder ofSize(int rows, int columns) {
        return new BoardBuilder(rows, columns);
    }

    public BoardBuilder withMineAt(int row, int column) {
        if (board[row][column] == MINE) throw new IllegalArgumentException("Double mine");

        board[row][column] = MINE;

        // top row
        if (row > 0 && column > 0       && board[row - 1][column - 1] != MINE) board[row - 1][column - 1]++;
        if (row > 0                     && board[row - 1][column] != MINE)     board[row - 1][column]++;
        if (row > 0 && column < columns && board[row - 1][column - 1] != MINE) board[row - 1][column - 1]++;

        return this;
    }

    public BoardBuilder uncoveredAt(int row, int column) {
        uncoveredAreas[row][column] = 1;
        return this;
    }

    public Board getCovered() {
        return null;
    }


    public Board getUncovered() {

        return null;
    }
}
