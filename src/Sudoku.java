// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.*;

public class Sudoku {

    private Cell[] givenCells; // bu Sudoku turunden olsa daha iyi degil mi?
    private List<int[]> sudoku = new ArrayList<>();

    public Sudoku(){
        givenCells = Cell.getInitialCells();
        implementGivenCells(givenCells);
//        Collections.shuffle(this.sudoku); // silsem ne olur?
    }

    public Sudoku(Sudoku other){
        this.sudoku = new ArrayList<>();
        for(int[] row: other.sudoku){
            this.sudoku.add(Arrays.copyOf(row, row.length));
        }
    }
    public Sudoku duplicate(){
        return new Sudoku(this);
    }

    private void implementGivenCells(Cell[] cells) {
        int[][] sudoku = new int[9][9];
        for(Cell cell: cells) {
            sudoku[cell.getRow()][cell.getCol()] = cell.value;
        }

        for(int i= 0; i < 9; i++){
            this.sudoku.add(Arrays.copyOf(sudoku[i], sudoku[i].length));
        }

    }

    // TODO main hicbir ise yaramiyor su an. Buraya yonlendirdim,
    //  ve her sey burada gerceklesiyor.
    //  Uretilen listeyi main'e cikti alip oradan devam edebilmek daha iyi olur mu?
//    public void solveSudoku(){
//        Sudoku current = new Sudoku(givenCells)
//    }
    public void generateRandomSudoku() {
        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++) {
                if (sudoku.get(row)[col] != 0) {
                    continue;
                }

                // step by step according to rows
                // if 1-9 hasn't been used in the row, then use it
                int value = fillRow(sudoku, row, col);
                sudoku.get(row)[col] = value;
            }
        }
    }

    public int fillRow(List<int[]> sudoku, int row, int col){
        Random random = new Random();
        int attempts = 0;
        while(attempts < 100){
            int value = random.nextInt(9) + 1;

            if(isThisProperLocation(sudoku, row, col, value)){
                return value;
            }
            attempts++;
        }
        // Handle the case where no valid value is found
        return sudoku.get(row)[col];
    }

    public int lossFunctionScore() {
        int score = 0;

        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++){
                int value = sudoku.get(row)[col]; //sudoku[row][col];
                if (isInRow(sudoku, row, col, value) ||
                        isInCol(sudoku, row, col, value) ||
                        isInSubgrid(sudoku, row, col, value)){
                    score++;
                }
            }
        }

        return score;
    }

    public static boolean isThisProperLocation(List<int[]> sudoku, int row, int col, int value){
//        return !isInRow(sudoku, row, col, value) &&
//                !isInCol(sudoku, row, col, value) &&
//                !isInSubgrid(sudoku, row, col, value);
        return !isInSubgrid(sudoku, row, col, value);
    }
    public static boolean isInRow(List<int[]> sudoku, int row, int col, int value){
        for(int i = 0; i < 9; i++){
            if(i == row){
                continue;
            }
            if(sudoku.get(i)[col] == value){ // sudoku[i][col]
                return true;
            }
        }
        return false;
    }

    public static boolean isInCol(List<int[]> sudoku, int row, int col, int value){
        for(int i = 0; i < 9; i++){
            if(i == col){
                continue;
            }
            if(sudoku.get(row)[i] == value){
                return true;
            }
        }
        return false;
    }

    //TODO check blocks;
    public static boolean isInSubgrid(List<int[]> sudoku, int row, int col, int value) {
        int x0 = row / 3 * 3;
        int y0 = col / 3 * 3;
        for(int i = x0; i < x0 + 3; i++){
            for(int j = y0; j < y0 + 3; j++){
                if(i == row && j == col){
                    continue;
                }
                if(sudoku.get(i)[j] == value){
                    return true;
                }
            }
        }
        return false;
    }

    public void printSudoku() {
        for (int i = 0; i < this.sudoku.size(); i++) {
            if (i % 3 == 0) {
                System.out.println(" -----------------------");
            }
            for (int j = 0; j < this.sudoku.get(i).length; j++) {
                if (j % 3 == 0) {
                    System.out.print("| ");
                }
                System.out.print(this.sudoku.get(i)[j] == 0
                        ? "_"
                        : Integer.toString(this.sudoku.get(i)[j]));

                System.out.print(' ');
            }
            System.out.println("|");
        }
        System.out.println(" -----------------------");
    }

    public void swapCells(int row1, int col1, int row2, int col2) {
        int temp = sudoku.get(row1)[col1];
        sudoku.get(row1)[col1] = sudoku.get(row2)[col2];
        sudoku.get(row2)[col2] = temp;
    }
    public List<int[]> getSudoku() {
        return sudoku;
    }
}