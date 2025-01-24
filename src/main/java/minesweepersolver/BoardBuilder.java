package minesweepersolver;

public class BoardBuilder {
    public static final int MINE = -1;
    private int[][] board;
    private int[][] uncoveredAreas;

    public BoardBuilder() {}


    public BoardBuilder ofSize(int rows, int columns) {
        board = new int[rows][columns];
        uncoveredAreas = new int[rows][columns];
        return this;
    }

    public BoardBuilder withMineAt(int row, int column) {
        if (board[row][column] == MINE) throw new IllegalArgumentException("Double mine");

        board[row][column] = MINE;

        // TODO increase neighbor count by one

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
