package datamunging;

public class TableImporter {

    public Table importData(String tableContent) {
        return new Table(tableContent);
    }

    public Table importData(final String tableContent, final ColumnBounds customColumnBounds) {
        return null;
    }
}
