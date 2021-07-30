package datamunging;

public class Table {

    private String[] head;
    private String[][] tableCells;

    public Table(String[] head, String[][] tableCells) {
        this.head = head;
        this.tableCells = tableCells;
    }

    @Override
    public String toString() {
        return String.join(", ", head);
    }
}

