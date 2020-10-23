package codesmells.tictactoe;

public class Game {
    public static final char EMPTY_SYMBOL = ' ';

    private char lastSymbol = EMPTY_SYMBOL;
    private Board board = new Board();

    public void Play(char symbol, int x, int y) throws Exception {
        //if first move
        if (lastSymbol == EMPTY_SYMBOL) {
            //if player is X
            if (symbol == 'O') {
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
        //if the positions in first row are taken
        if (board.isPositionOccupied(0, 0) &&
                board.isPositionOccupied(0, 1) &&
                board.isPositionOccupied(0, 2)) {
            //if first row is full with same symbol
            if (board.getSymbol(0, 0) ==
                    board.getSymbol(0, 1) &&
                    board.getSymbol(0, 2) == board.getSymbol(0, 1)) {
                return board.getSymbol(0, 0);
            }
        }

        //if the positions in first row are taken
        if (board.isPositionOccupied(1, 0) &&
                board.isPositionOccupied(1, 1) &&
                board.isPositionOccupied(1, 2)) {
            //if middle row is full with same symbol
            if (board.getSymbol(1, 0) ==
                    board.getSymbol(1, 1) &&
                    board.getSymbol(1, 2) ==
                            board.getSymbol(1, 1)) {
                return board.getSymbol(1, 0);
            }
        }

        //if the positions in first row are taken
        if (board.isPositionOccupied(2, 0) &&
                board.isPositionOccupied(2, 1) &&
                board.isPositionOccupied(2, 2)) {
            //if middle row is full with same symbol
            if (board.getSymbol(2, 0) ==
                    board.getSymbol(2, 1) &&
                    board.getSymbol(2, 2) ==
                            board.getSymbol(2, 1)) {
                return board.getSymbol(2, 0);
            }
        }

        return EMPTY_SYMBOL;
    }

}

