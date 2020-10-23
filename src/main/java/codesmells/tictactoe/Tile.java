package codesmells.tictactoe;

public class Tile {
    public int x;
    public int y;
    private char symbol;

    public Tile(int x, int y, char symbol) {
        this.x = x;
        this.y = y;
        this.setSymbol(symbol);
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}