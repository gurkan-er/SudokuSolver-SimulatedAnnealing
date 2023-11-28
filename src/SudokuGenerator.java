// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.*;

public class SudokuGenerator {
    List<Cell> filledCells = new ArrayList<>();

    public static void main(String[] args) {

        int[][] sudoku = new int[9][9];
        SudokuGenerator sudokuGenerator = new SudokuGenerator();
        sudokuGenerator.generateGivenSudoku(sudoku);
        sudokuGenerator.printSudoku(sudoku);

        sudokuGenerator.generateRandomSudoku(sudoku);
        sudokuGenerator.printSudoku(sudoku);

        System.out.println(lossFunctionScore(sudoku));
    }

    public void generateGivenSudoku(int[][] sudoku) {
        Cell[] initialCells = {
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

        for(Cell cell: initialCells) {
            sudoku[cell.row][cell.col] = cell.value;
            filledCells.add(cell);
        }
    }

    public void generateRandomSudoku(int[][] sudoku) {
        Random random = new Random();

        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++) {
                int value = random.nextInt(9) + 1;
                if(sudoku[row][col] == 0) {
                    sudoku[row][col] = value;
                }
            }
        }
    }

    public static int lossFunctionScore(int[][] sudoku) {
        int score = 0;

        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++){
                int value = sudoku[row][col];
                if (checkRow(sudoku, row, col, value)){
                    score++;
                }
                if (checkCol(sudoku, row, col, value)){
                    score++;
                }
            }
        }

        return score;
    }

    public static boolean checkRow(int[][] sudoku, int row, int col, int value){
        for(int i = row+1; i < 9; i++){
            if(sudoku[i][col] == value){
                return true;
            }
        }
        return false;
    }

    public static boolean checkCol(int[][] sudoku, int row, int col, int value){
        for(int i = col+1; i < 9; i++){
            if(sudoku[row][i] == value){
                return true;
            }
        }
        return false;
    }

    //TODO check blocks;

    // duzgun sudoku uretmeye dair
    /*
    public void generateCorrectSudoku(int[][] sudoku) {
        Random random = new Random();

        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++) {
                if(sudoku[row][col] == 0) {
                    int value = findValue(sudoku, row, col);

                    sudoku[row][col] = value;
                }
            }
        }
    }

    private int findValue(int[][] sudoku, int row, int col) {
        int value = 0;
        for(int i = 1; i <= 9; i++) {
            if(checkSudoku(sudoku, row, col, i)) {
                value = i;
                break;
            }
        }
        return value;
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

    public static boolean isSudokuBuildCorrectly(int[][] sudoku) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++){
                if(!isSudokuBuildCorrectly(sudoku, 0, 0)){
                    System.out.println("false");
                }
            }
        }
        System.out.println(COUNT);
        return true;
    }
    public static boolean isSudokuBuildCorrectly(int[][] sudoku, int x, int y){
        COUNT++;
        int value = sudoku[x][y];
        for(int i = 0; i < 9; i++){
            if(sudoku[x][i] == value && i != y){
                return false;
            }
            if(sudoku[i][y] == value && i != x){
                return false;
            }
        }
        int x0 = x / 3 * 3;
        int y0 = y / 3 * 3;
        for(int i = x0; i < x0 + 3; i++){
            for(int j = y0; j < y0 + 3; j++){
                if(sudoku[i][j] == value && i != x && j != y){
                    return false;
                }
            }
        }
        return true;
    }

    */
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