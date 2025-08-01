package minesweepersolver;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Stream;

import static minesweepersolver.Board.COVERED_CHAR;
import static minesweepersolver.Board.MINE;

public class MineSweeper {

    private final Board board;
    private final int nMines;
    private int markedMines;
    PriorityQueue<Visit> queue;


    public MineSweeper(final String board, final int nMines) {
        this.board = new Board(board);
        this.nMines = nMines;
        this.markedMines = this.board.countMarkedMines();
    }
    
    public String solve() {
        do {
            boolean isSolved = tryToSolveEndgame();
            if (isSolved) {
                return board.toString();
            }

            earlyGameVisit();
            if (board.isSolved()) {
                return board.toString();
            }
            if (isQueueEmpty()) {
                return "?";
            }
        } while (true);
    }

    private boolean isQueueEmpty() {
        return queue == null || queue.isEmpty();
    }

    boolean tryToSolveEndgame() {
        if (!board.toString().contains(COVERED_CHAR)) {
            return true;
        }

        int remainingHiddenMineCount = getRemainingHiddenMineCount();

        if (remainingHiddenMineCount == 0) {
            for(int row = 0; row < board.getRows(); row++) {
                for (int col = 0; col < board.getColumns(); col++) {
                    if (board.getCellValue(row, col) == Board.COVERED) {
                        Position position = new Position(row, col);
                        open(position);
                    }
                }
            }
            return true;
        }

        int remainingCoveredCellCount = getRemainingCoveredCellCount();

        if (remainingHiddenMineCount == remainingCoveredCellCount) {
            // alles als Mine markieren...
            for(int row = 0; row < board.getRows(); row++) {
                for (int col = 0; col < board.getColumns(); col++) {
                    if (board.getCellValue(row, col) == Board.COVERED) {
                        board.set(row, col, Board.MINE);
                    }
                }
            }
            return true;
        }
        return false;
    }

    boolean earlyGameVisit() {
        if (queue == null) {
            queue = initQueue();
        }
        // local strategies
        if (!queue.isEmpty()) {
            Visit visit = queue.poll();

            Position position = visit.getPosition();
            boolean isCovered = board.isCovered(position);
            // TODO are we on a covered field or on an uncovered field? -> canUncoverNeighbors()
            if (isCovered) {
                int priority = 10;
                board.getSaturatedNeighbours(position)
                        .forEach(neighbour -> {
                            int cellValue = board.getCellValue(neighbour);

                            queue.add(new Visit(neighbour, priority));});
                    // set the externally retrieved number
                    open(position);
                    // TODO add neighbors to queue or update priority, if already in queue
                    return true;



                // TODO: all other solution strategies

            /*
            Ideen:
            - Eindeutige Mine markieren
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

            } else {
                int numberOfNeighboringMines = board.getCellValue(position);
                int countedMines = board.countMinesAroundFor(position);

                if (numberOfNeighboringMines == countedMines) {
                    board.getUnmarkedCoveredCells(position).forEach(this::open); // TODO queue each + right neighbours
                }

                int numberOfCoveredNeighbours = board.countCoveredNeighbours(position);
                if (numberOfNeighboringMines - countedMines == numberOfCoveredNeighbours) {
                    board.getUnmarkedCoveredCells(position).forEach(p -> board.set(p, MINE));
                    // TODO add neighbours of each mine - actual position - known mines

                }
                // can uncover neighbors?

                // TODO take into account: multiple numbers put constraints on common covered neighbors

            }
            // TODO after uncovering: put neighbors to queue

        }
        return false;
    }

    private void open(Position position) {
        int cellValue = Game.open(position.row(), position.col());
        Stream<Position> neighbours = position.getNeighbours(board.getBounds());
        neighbours.filter(board::isCovered)
                .forEach(neighbour -> queue.add(new Visit(neighbour, 2)));
        board.set(position, cellValue);
    }

    public int getRemainingCoveredCellCount() {
        return board.getCoveredCells();
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
                    int priority = board.getCellValue(position) == 0 ? 0 : 1;
                    visits.add(new Visit(position, priority));
                }
            }
        }

        return visits;
    }

    public boolean isSolved() {
        return board.isSolved();
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

    public int getCellValue(int row, int column) {
        return board[row][column];
    }

    public int getCellValue(Position position) {
        return getCellValue(position.row(), position.col());
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

    public int countValuesAroundFor(int row, int col, int value) {
        return (int) new Position(row, col).getNeighbours(bounds)
                .filter(p -> getCellValue(p) == value)
                .count();
    }

    public boolean hasNeighbourValue(Position position, int value) {
        return position.getNeighbours(bounds)
                .anyMatch(p -> getCellValue(p) == value);
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
        return position.getNeighbours(bounds).anyMatch(this::isSafe);
    }

    private boolean isSafe(Position position) {
        return !isCovered(position) && !hasMine(position);
    }

    public boolean hasUnknownNeighbour(Position position) {
        return position.getNeighbours(bounds).anyMatch(this::isCovered);
    }

    public boolean hasMine(Position position) {
        return hasMine(position.row(), position.col());
    }

    public boolean hasMine(final int row, final int column) {
        return board[row][column] == MINE;
    }

    boolean isUnknownBorder(Position position) {
        return isCovered(position)
               && hasKnownNeighbourNumber(position);
    }

    boolean isKnownBorder(Position position) {
        return isSafe(position) && hasUnknownNeighbour(position);
    }

    public boolean isSolved() {
        return getCoveredCells() == 0;
    }

    public boolean isCovered(Position position) {
        return getCellValue(position) == COVERED;
    }

    public int countMinesAroundFor(Position position) {
        return countValuesAroundFor(position.row(), position.col(), MINE);
    }

    public Stream<Position> getUnmarkedCoveredCells(Position position) {
        return position.getNeighbours(bounds).filter(this::isCovered);
    }

    /**
     * without mines
     */
    public int countCoveredNeighbours(Position position) {
        return countValuesAroundFor(position.row(), position.col(), COVERED);
    }

    public Bounds getBounds() {
        return bounds;
    }

    public Stream<Position> getSaturatedNeighbours(Position position) {
        return position.getNeighbours(bounds)
                .filter(this::isSaturated);
    }

    private boolean isSaturated(Position position) {
        if (isCovered(position)) {
            return false;
        }
        if (isZero(position)) {
            return true;
        }
        int cellValue = getCellValue(position);
        return cellValue == countMinesAroundFor(position);
    }

    public boolean isZero(Position pos) {
        return getCellValue(pos) == 0;
    }
}