package codesmells.tictactoe;

public class Game {
    public static final char EMPTY_SYMBOL = ' ';
    private char _lastSymbol = EMPTY_SYMBOL;
    private Board _board = new Board();

    public void Play(char symbol, int x, int y) throws Exception {
        //if first move
        if (_lastSymbol == EMPTY_SYMBOL) {
            //if player is X
            if (symbol == 'O') {
                throw new Exception("Invalid first player");
            }
        }
        //if not first move but player repeated
        else if (symbol == _lastSymbol) {
            throw new Exception("Invalid next player");
        }
        //if not first move but play on an already played tile
        else if (getSymbol(x, y) != EMPTY_SYMBOL) {
            throw new Exception("Invalid position");
        }

        // update game state
        _lastSymbol = symbol;
        _board.AddTileAt(symbol, x, y);
    }

    public char getWinner() {
        //if the positions in first row are taken
        if (getSymbol(0, 0) != EMPTY_SYMBOL &&
                getSymbol(0, 1) != EMPTY_SYMBOL &&
                getSymbol(0, 2) != EMPTY_SYMBOL) {
            //if first row is full with same symbol
            if (getSymbol(0, 0) ==
                    getSymbol(0, 1) &&
                    getSymbol(0, 2) == getSymbol(0, 1)) {
                return getSymbol(0, 0);
            }
        }

        //if the positions in first row are taken
        if (getSymbol(1, 0) != EMPTY_SYMBOL &&
                getSymbol(1, 1) != EMPTY_SYMBOL &&
                getSymbol(1, 2) != EMPTY_SYMBOL) {
            //if middle row is full with same symbol
            if (getSymbol(1, 0) ==
                    getSymbol(1, 1) &&
                    getSymbol(1, 2) ==
                            getSymbol(1, 1)) {
                return getSymbol(1, 0);
            }
        }

        //if the positions in first row are taken
        if (getSymbol(2, 0) != EMPTY_SYMBOL &&
                getSymbol(2, 1) != EMPTY_SYMBOL &&
                getSymbol(2, 2) != EMPTY_SYMBOL) {
            //if middle row is full with same symbol
            if (getSymbol(2, 0) ==
                    getSymbol(2, 1) &&
                    getSymbol(2, 2) ==
                            getSymbol(2, 1)) {
                return getSymbol(2, 0);
            }
        }

        return EMPTY_SYMBOL;
    }

    private char getSymbol(int x, int y) {
        return _board.getSymbolAt(x, y).Symbol;
    }
}

