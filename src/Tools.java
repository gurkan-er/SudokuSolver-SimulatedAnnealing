import java.util.List;

public class Tools {
    private static final int K = 1;
    public static double probability(double oldScore, double newScore, double temperature) {
        if (newScore < oldScore) {
            return 1.0;
        }
        return Math.exp((oldScore - newScore) / (K * temperature));
    }

    // If some cells still empty, we'll specify them.
    public static void incorrectCells(Sudoku incorrectSudoku) {
        List<int[]> sudoku = incorrectSudoku.getSudoku();
        int count = 0;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9 ; col++) {
                int value = sudoku.get(row)[col];
                if (Sudoku.isInRow(sudoku, row, col, value) ||
                        Sudoku.isInCol(sudoku, row, col, value) ||
                        Sudoku.isInSubgrid(sudoku, row, col, value))
                {
                    System.out.println(++count + ". " + new Cell(row, col, value));
                }
            }
        }
    }
}
