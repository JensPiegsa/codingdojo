package minesweepersolver;

import java.util.Arrays;

import static minesweepersolver.Board.COVERED_CHAR;

public class MineSweeper {

    private final Board board;

    public MineSweeper(final String board, final int nMines) {
        this.board = new Board(board);
    }
    
    public String solve() {
        try {
            boolean didStep;
            do {
                // Call solver step
                didStep = solverStep(board);
            } while (didStep);

            return board.toString();
        } catch (NotSolvableException e) {
            return "?";
        }
    }

    private boolean solverStep(Board board) throws NotSolvableException {
        if (!board.toString().contains(COVERED_CHAR)) {
            return false;
        }
        
        for(int row = 0; row < board.getRows(); row++) {
            for(int col = 0; col < board.getColumns(); col++) {
                if (tryToUncoverCell(board, row, col)) {
                    return true;
                }
            }
        }

        throw new NotSolvableException();
    }

    private static boolean tryToUncoverCell(Board board, int row, int col) {
        int cell = board.get(row, col);
        if (cell == Board.COVERED) {
            int cols = board.getColumns() - 1;
            int rows = board.getRows() - 1;

            if (
                // top row
                   ((row > 0    && col > 0    && board.get(row-1,col-1) == 0))
                || ((row > 0                  && board.get(row-1,col) == 0))
                || ((row > 0    && col < cols && board.get(row-1,col+1) == 0))
                // middle rows
                || ((              col > 0    && board.get(row,col-1) == 0))
                || ((              col < cols && board.get(row,col+1) == 0))
                // bottom rows
                || ((row < rows && col > 0    && board.get(row+1,col-1) == 0))
                || ((row < rows               && board.get(row+1,col) == 0))
                || ((row < rows && col < cols && board.get(row+1,col+1) == 0))
            ) {

                board.set(row, col, board.countMinesAroundFor(row, col));
                return true;
            }

            // TODO: all other solution strategies
        }
        return false;
    }

}

class NotSolvableException extends Exception {

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
    public Board(int[][] board) {this.board = board;}

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

    public int getRows() {
        return board.length;
    }

    public int getColumns() {
        return board[0].length;
    }

    public void set(int row, int col, int cellValue) {
        board[row][col] = cellValue;
    }

    public int countMinesAroundFor(int row, int col) {
        int cols = getColumns() - 1;
        int rows = getRows() - 1;

        // top row
        return
          ((row > 0    && col > 0    && isMine(row-1,col-1) ) ? 1 : 0)
        + ((row > 0                  && isMine(row-1,col))        ? 1 : 0)
        + ((row > 0    && col < cols && isMine(row-1,col+1))  ? 1 : 0)
        // middle rows
        + ((              col > 0    && isMine(row,col-1))         ? 1 : 0)
        + ((              col < cols && isMine(row,col+1))         ? 1 : 0)
        // bottom rows
        + ((row < rows && col > 0    && isMine(row+1,col-1))  ? 1 : 0)
        + ((row < rows               && isMine(row+1,col))        ? 1 : 0)
        + ((row < rows && col < cols && isMine(row+1,col+1))  ? 1 : 0);

    }

    private boolean isMine(int row, int col) {
        return board[row][col] == MINE;
    }
}