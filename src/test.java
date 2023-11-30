public class test {
    public static int lossFunctionScore(int[][] sudoku) {
        int score = 0;

        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++){
                int value = sudoku[row][col];
                if (check(sudoku, row, col, value)){
                    score++;
                }
                if (checkCol(sudoku, row, col, value)){
                    score++;
                }
            }
        }

        return score;
    }

    public static boolean check(int[][] sudoku, int row, int col, int value){
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

    public static void main(String[] args) {
        int[][] sudoku = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        printSudoku(sudoku);
        System.out.println(lossFunctionScore(sudoku));

        int[][] sudoku2 = new int[9][9];

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                sudoku2[i][j] = i*9 + j;
            }
        }
        printSudoku(sudoku2);
        System.out.println(lossFunctionScore(sudoku2));
    }

    public static void printSudoku(int[][] sudoku) {
        for (int i = 0; i < sudoku.length; i++) {
            if (i % 3 == 0) {
                System.out.println(" -----------------------");
            }
            for (int j = 0; j < sudoku[i].length; j++) {
                if (j % 3 == 0) {
                    System.out.print("| ");
                }
                System.out.print(sudoku[i][j] == 0
                        ? "0"
                        : Integer.toString(sudoku[i][j]));

                System.out.print(' ');
            }
            System.out.println("|");
        }
        System.out.println(" -----------------------");
    }
}
