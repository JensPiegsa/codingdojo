package datamunging;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Table {

    private String[] head;
    private String[][] tableCells;

    public Table(String[] head, String[][] tableCells) {
        this.head = head;
        this.tableCells = tableCells;
    }

    @Override
    public String toString() {
        return Arrays.stream(head)
                .map(s -> s.isEmpty() ? "<NA>" : s)
                .collect(Collectors.joining(", "));
    }
}

