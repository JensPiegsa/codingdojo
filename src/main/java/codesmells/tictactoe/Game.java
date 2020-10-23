package codesmells.tictactoe;

public class Game {
    public static final char EMPTY_SYMBOL = ' ';
    public static final char O_SYMBOL = 'O';

    private char lastSymbol = EMPTY_SYMBOL;
    private Board board = new Board();

    public void Play(char symbol, int x, int y) throws Exception {
        //if first move
        if (lastSymbol == EMPTY_SYMBOL) {
            //if player is X
            if (symbol == O_SYMBOL) {
                throw new Exception("Invalid first player");
            }
        }
        //if not first move but player repeated
        else if (symbol == lastSymbol) {
            throw new Exception("Invalid next player");
        }
        //if not first move but play on an already played tile
        else if (board.isPositionOccupied(x, y)) {
            throw new Exception("Invalid position");
        }

        // update game state
        lastSymbol = symbol;
        board.AddTileAt(symbol, x, y);
    }

    public char getWinner() {
        if (isRowOccupied(0)) return board.getSymbol(0, 0);
        if (isRowOccupied(1)) return board.getSymbol(1, 0);
        if (isRowOccupied(2)) return board.getSymbol(2, 0);
        return EMPTY_SYMBOL;
    }

    private boolean isRowOccupied(int columnIndex) {
        return isColumnOccupied(columnIndex) && doesColumnHaveSameSymbol(columnIndex);
    }

    private boolean doesColumnHaveSameSymbol(int columnIndex) {
        return board.getSymbol(columnIndex, 0) ==
                board.getSymbol(columnIndex, 1) &&
                board.getSymbol(columnIndex, 2) == board.getSymbol(columnIndex, 1);
    }

    private boolean isColumnOccupied(int columnIndex) {
        return board.isPositionOccupied(columnIndex, 0) &&
                board.isPositionOccupied(columnIndex, 1) &&
                board.isPositionOccupied(columnIndex, 2);
    }
}