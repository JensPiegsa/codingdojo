package minesweepersolver;

import java.util.Arrays;

public class MineSweeper {

    private final Board board;

    public MineSweeper(final String board, final int nMines) {
        this.board = new Board(board);
    }
    
    public String solve() {
        if (board.isValid()) {
            return board.toString();
        }
        return "?";
    }

}

class Board {

    public static final String COVERED_CHAR = "?";
    public static final int COVERED = 9;
    private static final String MINE_CHAR = "x";
    private int MINE = -1;

    private int[][] board;

    public Board(String board) {
        parse(board);
    }

    private void parse(String boardStr) {
        String[] rows = boardStr.strip().split("\n");

        board = new int[rows.length][];

        for (int row = 0; row < rows.length; row++) {
            String[] cells = rows[row].split(" ");
            board[row] = new int[cells.length];
            for (int cell = 0; cell < cells.length; cell++) {
                String ch = cells[cell];
                if (ch.equals(MINE_CHAR)) {
                    board[row][cell] = MINE;
                } else if (ch.equals(COVERED_CHAR)) {
                    board[row][cell] = COVERED;
                } else {
                    board[row][cell] = Integer.parseInt(ch);
                }
            }
        }
    }

    public boolean isValid() {
        boolean allQuestionMark = true;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] != COVERED) allQuestionMark = false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int row = 0; row < board.length; row++) {
            for (int cell = 0; cell < board[row].length; cell++) {
                if (board[row][cell] == COVERED) {
                    stringBuilder.append(COVERED_CHAR);
                } else if (board[row][cell] == MINE) {
                    stringBuilder.append(MINE_CHAR);
                } else {
                    stringBuilder.append(board[row][cell]);
                }
                if (cell < board[row].length - 1) {
                    stringBuilder.append(" ");
                }
            }
            if (row < board.length - 1) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public boolean hasMine(final int row, final int column) {
        return board[row][column] == MINE;
    }

    public int get(int row, int column) {
        return board[row][column];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board otherBoard = (Board) o;
        return Arrays.deepEquals(board, otherBoard.board);
    }
}