package minesweepersolver;

import java.util.Arrays;
import java.util.PriorityQueue;

import static minesweepersolver.Board.COVERED_CHAR;

public class MineSweeper {

    private final Board board;
    private final int nMines;
    private int markedMines;
    private PriorityQueue<Visit> queue;


    public MineSweeper(final String board, final int nMines) {
        this.board = new Board(board);
        this.nMines = nMines;
        this.markedMines = this.board.countMarkedMines();
    }
    
    public String solve() {
        do {
            endgameStep(board);
            if (board.isSolved()) {
                return board.toString();
            }

            earlyGameStep(board);
            if (isQueueEmpty()) {
                return "?";
            }
        } while (true);
    }

    private boolean isQueueEmpty() {
        return queue == null || queue.isEmpty();
    }

    boolean endgameStep(Board board) {
        if (!board.toString().contains(COVERED_CHAR)) {
            return false;
        }

        int remainingHiddenMineCount = getRemainingHiddenMineCount();

        if (remainingHiddenMineCount == 0) {
            for(int row = 0; row < board.getRows(); row++) {
                for (int col = 0; col < board.getColumns(); col++) {
                    if (board.get(row, col) == Board.COVERED) {
                        int cellValue = Game.open(row, col);
                        board.set(row, col, cellValue);
                    }
                }
            }
            return false;
        }

        int remainingCoveredCellCount = getRemainingCoveredCellCount();

        if (remainingHiddenMineCount == remainingCoveredCellCount) {
            // alles als Mine markieren...
            for(int row = 0; row < board.getRows(); row++) {
                for (int col = 0; col < board.getColumns(); col++) {
                    if (board.get(row, col) == Board.COVERED) {
                        board.set(row, col, Board.MINE);
                    }
                }
            }
            return false;
        }
        return true;
    }

    boolean earlyGameStep(Board board) {
        if (queue == null) {
            queue = initQueue();
        }
        // local strategies
        if (!queue.isEmpty()) {
            Visit visit = queue.poll();

            Position position = visit.getPosition();
            // TODO are we on a covered field or on an uncovered field? -> canUncoverNeighbors()
            if (canUncoverCell(board, position)) {
                // set the externally retrieved number
                int cellValue = Game.open(position.row(), position.col());
                board.set(position, cellValue);
                // TODO add neighbors to queue or update priority, if already in queue
                return true;
            }

        }
        return false;
    }

    public int getRemainingCoveredCellCount() {
        return board.getCoveredCells();
    }


    private boolean canUncoverCell(Board board, Position pos) {
        int cell = board.get(pos);
        if (cell == Board.COVERED) {
            int cols = board.getColumns() - 1;
            int rows = board.getRows() - 1;

            Bounds bounds = new Bounds(0, 0, cols, rows);
            pos.getNeighbours(bounds); // ???

            // Nachbar 0 -> Feld sicher
            if (board.hasNeighbourValue(pos, 0)) {
                return true;
            }

            // TODO: all other solution strategies

            /*
            Ideen:
            - Eindeutige Mine markieren
            - Zahl gleich Anzahl bekannter benachbarter Minen (isSaturated) <- NEXT TODO
            - Zahl kleiner Anzahl bekannter benachbarter Minen
            - Optimierungsmöglichkeiten für große minenlose zusammenhängende Flächen
            - atLeastOneSaturatedNeighbor
            - Suche nicht-saturierter Zahl deren fehlende Minen der Anzahl unaufgedeckter Nachbarn entspricht
                -> alle unaufgedeckten Nachbarn als Minen markieren
            - Markieren aller verdeckten Felder als Mine,
              sobald die Anzahl der verdeckten Felder der Anzahl der verbliebenen Minen entspricht

            - Zusätzliche Datenstruktur zur Speicherung der Anzahl der nicht saturierten Nachbarn
            - Queue zur Speicherung der nächsten zu bearbeitenden Felder
            (satisfiedNumberWithCoveredFieldsList & satisfiedNumberWithKnownNeighborsList)

     *   0 0 0 0 0
     *   0 1 1 1 0
     *   0 1 * 1 0
     *   0 1 1 1 0
     *   0 0 0 0 0
             */
        }
        return false;
    }

    public int getRemainingHiddenMineCount() {
        return nMines - board.countMarkedMines();
    }

    PriorityQueue<Visit> initQueue() {
        PriorityQueue<Visit> visits = new PriorityQueue<>();

        for(int row = 0; row < board.getRows(); row++) {
            for(int col = 0; col < board.getColumns(); col++) {

                Position position = new Position(row, col);

                if (board.isUnknownBorder(position)) {
                    visits.add(new Visit(position, 2));
                } else if (board.isKnownBorder(position)) {
                    int priority = board.get(position) == 0 ? 0 : 1;
                    visits.add(new Visit(position, priority));
                }
            }
        }

        return visits;
    }

}

class NotSolvableException extends Exception {

}

class Board {

    public static final String COVERED_CHAR = "?";
    public static final int COVERED = 9;
    private static final String MINE_CHAR = "x";
    public static int MINE = -1;
    private Bounds bounds;

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

        bounds = new Bounds(0, 0, board[0].length-1, board.length-1);
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

    public int get(Position position) {
        return get(position.row(), position.col());
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

    public void set(Position position, int value) {
        set(position.row(), position.col(), value);
    }

    public void set(int row, int col, int cellValue) {
        board[row][col] = cellValue;
    }

    public int countMinesAroundFor(int row, int col) {
        return countValuesAroundFor(row, col, MINE);
    }

    public int countValuesAroundFor(int row, int col, int value) {
        return (int) new Position(row, col).getNeighbours(bounds)
                .filter(p -> get(p) == value)
                .count();
    }

    private boolean isMine(int row, int col) {
        return board[row][col] == MINE;
    }

    public boolean hasNeighbourValue(Position position, int value) {
        return position.getNeighbours(bounds)
                .anyMatch(p -> get(p) == value);
    }

    public int countMarkedMines() {
        int count = 0;
        for (int[] rows : board) {
            for (int cell : rows) {
                if (cell == MINE) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getCoveredCells() {
        int count = 0;
        for (int[] rows : board) {
            for (int cell : rows) {
                if (cell == COVERED) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean hasKnownNeighbourNumber(Position position) {
        return position.getNeighbours(bounds).anyMatch(p -> get(p) != COVERED && get(p) != MINE);
    }

    public boolean hasUnknownNeighbour(Position position) {
        return position.getNeighbours(bounds).anyMatch(p -> get(p) == COVERED);
    }

    public boolean hasMine(Position position) {
        return hasMine(position.row(), position.col());
    }

    boolean isUnknownBorder(Position position) {
        return get(position) == COVERED
               && hasKnownNeighbourNumber(position);
    }

    boolean isKnownBorder(Position position) {
        return !hasMine(position)
                && get(position) != COVERED
                && hasUnknownNeighbour(position);
    }

    public boolean isSolved() {
        return getCoveredCells() == 0;
    }
}