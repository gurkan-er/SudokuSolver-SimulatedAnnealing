// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.*;

public class SudokuGenerator {
    public static void main(String[] args) {

        int[][] sudoku = new int[9][9];
        SudokuGenerator sudokuGenerator = new SudokuGenerator();
//        sudokuGenerator.generateSudoku(sudoku);
        sudokuGenerator.generateGivenSudoku(sudoku);
        sudokuGenerator.printSudoku(sudoku);

    }

    public void generateGivenSudoku(int[][] sudoku) {
        Cell[] filledCells = {
                new Cell(0, 2, 6),
                new Cell(1, 1, 8),
                new Cell(1, 4, 5),
                new Cell(1, 5, 4),
                new Cell(1, 6, 2),
                new Cell(2, 1, 4),
                new Cell(2, 4, 9),
                new Cell(2, 7, 7),
                new Cell(3, 2, 7),
                new Cell(3, 3, 9),
                new Cell(3, 6, 3),
                new Cell(4, 4, 8),
                new Cell(4, 6, 4),
                new Cell(5, 0, 6),
                new Cell(5, 6, 1),
                new Cell(6, 0, 2),
                new Cell(6, 2, 3),
                new Cell(6, 8, 1),
                new Cell(7, 3, 5),
                new Cell(7, 7, 4),
                new Cell(8, 2, 8),
                new Cell(8, 3, 3),
                new Cell(8, 6, 5),
                new Cell(8, 8, 2)
        };

        for(Cell cell: filledCells) {
            sudoku[cell.row][cell.col] = cell.value;
        }

    }

    public void generateSudoku(int[][] sudoku) {
        Random random = new Random();
        int i = 0;
        while (i < 9) {
            int x = random.nextInt(9);
            int y = random.nextInt(9);
            int value = random.nextInt(9) + 1;
            if (sudoku[x][y] == 0 && checkSudoku(sudoku, x, y, value)) {
                sudoku[x][y] = value;
                i++;
            }
        }
    }

    private boolean checkSudoku(int[][] sudoku, int x, int y, int value) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[x][i] == value || sudoku[i][y] == value) {
                return false;
            }
        }
        int x0 = x / 3 * 3;
        int y0 = y / 3 * 3;
        for (int i = x0; i < x0 + 3; i++) {
            for (int j = y0; j < y0 + 3; j++) {
                if (sudoku[i][j] == value) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printSudoku(int[][] sudoku) {
        for (int i = 0; i < sudoku.length; i++) {
            if (i % 3 == 0) {
                System.out.println(" -----------------------");
            }
            for (int j = 0; j < sudoku[i].length; j++) {
                if (j % 3 == 0) {
                    System.out.print("| ");
                }
                System.out.print(sudoku[i][j] == 0
                        ? " "
                        : Integer.toString(sudoku[i][j]));

                System.out.print(' ');
            }
            System.out.println("|");
        }
        System.out.println(" -----------------------");
    }

    static class Cell {
        int row;
        int col;
        int value;
        Cell(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }
}