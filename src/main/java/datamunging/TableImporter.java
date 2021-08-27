package datamunging;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TableImporter {

    public Table importData(final String tableContent) {
        return importData(tableContent, null);
    }

    public Table importData(final String tableContent, final ColumnBounds customColumnBounds) {
        final String header = extractHeader(tableContent);
        final ColumnBounds columnBounds = customColumnBounds == null ? ColumnBounds.measure(header) : ColumnBounds.measure(header).merge(customColumnBounds);
        
        final String[] columnNames = createColumnNames(header, columnBounds);
        
        final String tableBodyContent = extractTableBodyContent(tableContent);
        final String[][] tableCells = createCellContent(tableBodyContent, columnBounds);

        final Table table = new Table(columnNames, tableCells);
        return table;
    }

    private String extractHeader(final String tableContent) {
        final int firstNewLine = tableContent.indexOf('\n');
        return firstNewLine == -1 ? tableContent : tableContent.substring(0, firstNewLine);
    }

    private String extractTableBodyContent(final String tableContent) {
        final int firstNewLine = tableContent.indexOf('\n');
        return firstNewLine == -1 ? null : tableContent.substring(firstNewLine + 1);
    }

    private String[] createColumnNames(final String header, final ColumnBounds columnBounds) {
        final String[] columnNames = columnBounds.cut(header);
        return Arrays.stream(columnNames)
                .map(String::trim)
                .toArray(String[]::new);
    }

    private String[][] createCellContent(final String tableBodyContent, final ColumnBounds columnBounds) {

        final ArrayList<Object> d = new ArrayList<>();
        final String[] bodyLines = tableBodyContent == null 
                ? new String[0]
                : tableBodyContent.split("(\n\r)|(\r)|(\n)");
        for (final String bodyLine : bodyLines) {
            final String[] lineData = columnBounds.cut(bodyLine);
            d.add(Arrays.stream(lineData)
                    .map(String::trim)
                    .toArray(String[]::new));
        }

        return d.toArray(new String[d.size()][]);
    }


}
