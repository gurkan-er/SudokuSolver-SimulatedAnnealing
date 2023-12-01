import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Sudoku {

    private List<int[]> sudoku = new ArrayList<>();

    public Sudoku(){
        // sudoku objects created with given cells
        Cell[] givenCells = Cell.getInitialCells();
        implementGivenCellsToSudoku(givenCells);
    }

    public Sudoku(Sudoku other){
        /*
         when we need to duplicate a sudoku object
         this constructor is used
         because we don't want to change the original sudoku object
         when we change the duplicate,
         so we need to copy the original sudoku object
        */
        this.sudoku = new ArrayList<>();

        for(int[] row: other.sudoku){
            this.sudoku.add(Arrays.copyOf(row, row.length));
        }
    }
    public Sudoku duplicate(){
        // when we need to duplicate a sudoku object (neighbours)
        // this method is used
        return new Sudoku(this);
    }

    private void implementGivenCellsToSudoku(Cell[] cells) {
        /*
         at the start of the program, we've some cells with values
         we need to implement these cells to the sudoku object
         then assign other cells with random values
        */

        // created 9x9 zero matrix
        int[][] temporarySudoku = new int[9][9];
        
        // assign given cells to the temporary sudoku matrix
        for(Cell cell: cells) {
            temporarySudoku[cell.getRow()][cell.getCol()] = cell.getValue();
        }
        
        // copy the temporary sudoku matrix to the sudoku object
        for(int i= 0; i < 9; i++){
            this.sudoku.add(Arrays.copyOf(temporarySudoku[i], temporarySudoku[i].length));
        }
    }

    public void fillSudokuWithRandomVariables() {
        // assign random values to sudoku object's empty cells
        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++) {
                /*
                 if the cell is not empty, then continue
                 because we don't want to change the given cells
                 we only want to change the empty cells
                */
                if (sudoku.get(row)[col] != 0) {
                    continue;
                }

                /*
                 to obtain a randomize sudoku,
                 we need to fill the empty cells with random values.
                 For simplicity, I randomize the rows
                 while making sure the value isn't in the same subgrid
                */
                int value = fillRowRandomly(sudoku, row, col);
                sudoku.get(row)[col] = value;
            }
        }
    }

    public int fillRowRandomly(List<int[]> sudoku, int row, int col){
        Random random = new Random();
        int attempts = 0;
        while(attempts < 100){
            int value = random.nextInt(9) + 1;

            if(!isInSubgrid(sudoku, row, col, value)){
                return value;
            }
            attempts++;
        }

        /*
         We couldn't be here if there isn't any empty cell
         in the row. If there is an empty cell in the row,
         must be an empty cell in the subgrid.
         So, if we couldn't find the proper value,
         we'll try again by calling same method recursively.
        */
        return fillRowRandomly(sudoku, row, col);
    }

    public int lossFunctionScore() {
        // calculate the number of errors in the sudoku object
        // the cell will count as an error if it's in the same row, col or subgrid
        int score = 0;

        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++){
                int value = sudoku.get(row)[col];

                if (isInRow(sudoku, row, col, value) ||
                        isInCol(sudoku, row, col, value) ||
                        isInSubgrid(sudoku, row, col, value))
                {
                    score++;
                }
            }
        }

        return score;
    }

    /*
     If some cells still empty, we'll specify them.
     So isInRow, isInCol and isInSubgrid must be static,
     because we'll reach them without creating a sudoku object.
    */
    public static boolean isInRow(List<int[]> sudoku, int row, int col, int value){
        for(int i = 0; i < 9; i++){
            if(i == row){
                continue;
            }
            if(sudoku.get(i)[col] == value){
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

    // for simplicity, First Approach isn't include random row and col
    // for the swap cells.
    public void swapCells() {
        int row1 = (int)(Math.random() * 9);
        int col1 = (int)(Math.random() * 9);
        int row2 = (int)(Math.random() * 9);
        int col2 = (int)(Math.random() * 9);

        int temp = sudoku.get(row1)[col1];
        sudoku.get(row1)[col1] = sudoku.get(row2)[col2];
        sudoku.get(row2)[col2] = temp;
    }

    // for the Second Approach
    public void swapCells(int row1, int col1, int row2, int col2) {
        int temp = sudoku.get(row1)[col1];
        sudoku.get(row1)[col1] = sudoku.get(row2)[col2];
        sudoku.get(row2)[col2] = temp;
    }
    public List<int[]> getSudoku() { return sudoku; }
}