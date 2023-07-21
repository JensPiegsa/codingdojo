package xogame;

public class XoGame {

    private final BoardCell[][] board = {
            {BoardCell.empty, BoardCell.empty, BoardCell.empty},
            {BoardCell.empty, BoardCell.empty, BoardCell.empty},
            {BoardCell.empty, BoardCell.empty, BoardCell.empty}};
    
    private BoardCell currentPlayer = BoardCell.X;
    
    public void playerPlace(final XoPosition position) {
        board[position.getY()][position.getX()] = currentPlayer;
        currentPlayer = currentPlayer.opponent();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                builder.append(board[x][y]);
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
