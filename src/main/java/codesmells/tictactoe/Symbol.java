package codesmells.tictactoe;

public enum Symbol {
    EMPTY(' '), O('O'), X('X');

    char symbolChar;
    
    Symbol(char symbolChar) {
        this.symbolChar = symbolChar;
    }
}
