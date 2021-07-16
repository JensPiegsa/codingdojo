package datamunging;

public class TableImporter {
    public Table importData(String tableContent) {
        return new Table(tableContent);
    }
}
