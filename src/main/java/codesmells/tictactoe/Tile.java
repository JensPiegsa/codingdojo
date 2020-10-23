package codesmells.tictactoe;

public class Tile {
    public int x;
    public int y;
    public Symbol symbol;

    public Tile(int x, int y, Symbol symbol) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
    }
}