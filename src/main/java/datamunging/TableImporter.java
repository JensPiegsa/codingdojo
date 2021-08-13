package datamunging;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TableImporter {

    public Table importData(String tableContent) {
        return importData(tableContent, null);
    }

    public Table importData(final String tableContent, final ColumnBounds customColumnBounds) {
        String header = extractHeader(tableContent);
        final ColumnBounds columnBounds = customColumnBounds == null ? ColumnBounds.measure(header) : ColumnBounds.measure(header).merge(customColumnBounds);
        final String[] columnNames = createColumnNames(header, columnBounds);
        Table table = new Table(columnNames,null);
        return table;
    }

    private String[] createColumnNames(String header, ColumnBounds columnBounds) {
        final String[] columnNames = columnBounds.cut(header);
        return Arrays.stream(columnNames)
                .map(String::trim)
                .toArray(String[]::new);
    }

    private String extractHeader(final String tableContent) {
        int firstNewLine = tableContent.indexOf('\n');
        return firstNewLine == -1 ? tableContent : tableContent.substring(0, firstNewLine);
    }


}
