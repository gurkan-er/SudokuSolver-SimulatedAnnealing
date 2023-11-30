import java.util.List;

public class Tools {
    private static int K = 1;
    public static double probability(double oldScore, double newScore, double temperature) {
        if (newScore < oldScore) {
            return 1.0;
        }
        return Math.exp((oldScore - newScore) / (K * temperature));
    }

    // TODO score hesaplamayi buraya tasi

    // uncorrect cells
    public static Cell uncorrectCells(Sudoku sudoku) {
        List<int[]> theSudoku = sudoku.getSudoku();
        int count = 0;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9 ; col++) {
                int value = theSudoku.get(row)[col];
                if (Sudoku.isInRow(theSudoku, row, col, value) ||
                        Sudoku.isInCol(theSudoku, row, col, value) ||
                        Sudoku.isInSubgrid(theSudoku, row, col, value)) {
                    System.out.println(++count + ". " + new Cell(row, col, value));
                }
            }
        }
        return null;
    }
}
