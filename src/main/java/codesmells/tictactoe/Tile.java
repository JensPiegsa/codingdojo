package codesmells.tictactoe;

public class Tile {
    public int x;
    public int y;
    public char symbol;

    public Tile(int x, int y, char symbol) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
    }
}