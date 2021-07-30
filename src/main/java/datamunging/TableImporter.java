package datamunging;

import java.util.List;

public class TableImporter {

    public Table importData(String tableContent) {
        return importData(tableContent, null);
    }

    public Table importData(final String tableContent, final ColumnBounds customColumnBounds) {
        String header = extractHeader(tableContent);
        final ColumnBounds columnBounds = ColumnBounds.measure(header);
        final String[] columnNames = columnBounds.cut(header);
        Table table = new Table(columnNames,null);
        return table;
    }

    private String extractHeader(final String tableContent) {
        return tableContent;
    }


}
