package minesweepersolver;

import static minesweepersolver.Board.COVERED;

@SuppressWarnings("ClassEscapesDefinedScope")
public class BoardBuilder {
    public static final int MINE = -1;
    private final int rows;
    private final int columns;
    private int[][] board;
    private int[][] uncoveredAreas;

    private BoardBuilder(int rows, int columns, boolean covered) {
        this.rows = rows;
        this.columns = columns;
        board = new int[rows][columns];
        uncoveredAreas = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                uncoveredAreas[row][column] = covered ? 0 : 1;
            }
        }
    }


    public static BoardBuilder ofSizeCovered(int rows, int columns) {
        return new BoardBuilder(rows, columns, true);
    }

    public static BoardBuilder ofSizeUncovered(int rows, int columns) {
        return new BoardBuilder(rows, columns, false);
    }

    public BoardBuilder withMineAt(int row, int column) {
        if (board[row][column] == MINE) throw new IllegalArgumentException("Double mine");

        board[row][column] = MINE;

        // top row
        if (row > 0    && column > 0       && board[row - 1][column - 1] != MINE) board[row - 1][column - 1]++;
        if (row > 0                        && board[row - 1][column] != MINE)     board[row - 1][column]++;
        if (row > 0    && column < columns && board[row - 1][column + 1] != MINE) board[row - 1][column + 1]++;
        // middle row
        if (              column > 0       && board[row    ][column - 1] != MINE) board[row    ][column - 1]++;
        if (              column < columns && board[row    ][column + 1] != MINE) board[row    ][column + 1]++;
        // bottom row
        if (row < rows && column > 0       && board[row + 1][column - 1] != MINE) board[row + 1][column - 1]++;
        if (row < rows                     && board[row + 1][column] != MINE)     board[row + 1][column]++;
        if (row < rows && column < columns && board[row + 1][column + 1] != MINE) board[row + 1][column + 1]++;


        return this;
    }

    public BoardBuilder uncoveredAt(int row, int column) {
        uncoveredAreas[row][column] = 1;
        return this;
    }

    public BoardBuilder coveredAt(int row, int column) {
        uncoveredAreas[row][column] = 0;
        return this;
    }

    public Board getCovered() {
        int[][] coveredAreas = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                coveredAreas[row][column] = (uncoveredAreas[row][column] == 1) ? board[row][column] : COVERED;
            }
        }
        return new Board(coveredAreas);
    }


    public Board getUncovered() {
        return new Board(board);
    }
}
