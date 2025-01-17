package minesweepersolver;

public class Game {
    static String gameMap;
    static int numberOfMines;
    private static Board currentBoard;
    private static Board fullyUncoveredBoard;

    public static void newGame(String gameMap) {
        Game.gameMap = gameMap;
        currentBoard = new Board(gameMap);
    }

    public static void read(String shownGameMap) {
        Game.numberOfMines = gameMap.length() - gameMap.replace("x", "").length();
        fullyUncoveredBoard = new Board(shownGameMap);
    }

    public static int getMinesN() {
        return numberOfMines;
    }

    public static int open(int row, int column) {
        if (currentBoard.hasMine(row, column)) {
            throw new IllegalStateException("Mine hit at [" + row + ", " + column + "]");
        } else {
            return fullyUncoveredBoard.get(row, column);
        }
    }
}
