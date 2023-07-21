package xogame;

public enum BoardCell {
    
    X ("❌") {
        @Override
        public BoardCell opponent() {
            return BoardCell.O;
        }
    },
    O ("⭕") {
        @Override
        public BoardCell opponent() {
            return BoardCell.X;
        }
    },
    empty("🟪"){
        @Override
        public BoardCell opponent() {
            return BoardCell.empty;
        }
    };

    private String s;

    BoardCell(String s) {
        this.s = s;
    }

    public abstract BoardCell opponent();

    @Override
    public String toString() {
        return s;
    }
}
