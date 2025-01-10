package minesweepersolver;

public class Game {
    static String gameMap;
    static int numberOfMines;

    public static void newGame(String gameMap) {
        Game.gameMap = gameMap;
        Game.numberOfMines = gameMap.length() - gameMap.replace("x", "").length();
    }

    public static void read(String shownGameMap) {

    }

    public static int getMinesN() {
        return numberOfMines;
    }
}
