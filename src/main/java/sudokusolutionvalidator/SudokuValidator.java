package sudokusolutionvalidator;

        import java.util.function.IntUnaryOperator;
        import java.util.stream.IntStream;

public class SudokuValidator {
    public static boolean check(int[][] sudoku) {

        for (int index = 0; index < 9; index++) {
            final int k = index;
            if (!isValid(col -> sudoku[k][col])
                    || !isValid(col -> sudoku[col][k])
                    || !isValid(i -> sudoku[i % 3 + k % 3 * 3][i / 3 + k / 3 * 3])) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValid(final IntUnaryOperator intUnaryOperator) {
        return IntStream.range(0, 9).map(intUnaryOperator).filter(g -> g != 0).distinct().count() == 9;
    }
}