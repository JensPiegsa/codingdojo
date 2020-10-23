package codesmells.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Tile> plays = new ArrayList<>();

    public Board() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile(i,j, Game.EMPTY_SYMBOL);
                plays.add(tile);
            }
        }
    }

    public Tile getTileAt(int x, int y) {
        for (Tile t : plays) {
            if (t.x == x && t.y == y) {
                return t;
            }
        }
        return null;
    }

    public void AddTileAt(char symbol, int x, int y) {
        getTileAt(x, y).setSymbol(symbol);
    }

    char getSymbol(int x, int y) {
        return getTileAt(x, y).getSymbol();
    }

    boolean isPositionOccupied(int x, int y) {
        return getSymbol(x, y) != Game.EMPTY_SYMBOL;
    }
}