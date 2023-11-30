public class Main {
    static double coolingRate = 0.95;
    static double temperature = 1;
    static double Tmin = 0.0001;
    static int numIterations = 1000;
    public static void main(String[] args) {
        Sudoku currentSudoku = new Sudoku();
        currentSudoku.generateRandomSudoku();

        Sudoku best = currentSudoku.duplicate();
        int bestScore = best.lossFunctionScore();

        int firstScore = currentSudoku.lossFunctionScore();
        int iterationNUmber = 0;

        while(temperature > Tmin && bestScore != 0){
            for(int i = 0; i < numIterations; i++){
                Sudoku neighbour = currentSudoku.duplicate();

                int row1 = (int)(Math.random() * 9);
                int col1 = (int)(Math.random() * 9);
                int row2 = (int)(Math.random() * 9);
                int col2 = (int)(Math.random() * 9);
                neighbour.swapCells(row1, col1, row2, col2);

                int newScore = neighbour.lossFunctionScore();
                int oldScore = currentSudoku.lossFunctionScore();
                int delta = newScore - oldScore;

                if (Math.random() < Tools.probability(oldScore, newScore, temperature)){
                    currentSudoku = neighbour.duplicate();
                }

                if (oldScore < bestScore){
                    best = neighbour.duplicate();
                    bestScore = newScore;
                }
                /*
                if(delta <= 0){
                    currentSudoku = neighbour.duplicate();
                    best = neighbour.duplicate();
                    bestScore = newScore;

                } else {
                    if(Math.random() >= Tools.probability(delta, temperature)){
                        neighbour.swapCells(row2, col2, row1, col1);
                    } else{
                        currentSudoku = neighbour.duplicate();
                    }
                }
                */
                iterationNUmber++;
                System.out.print(iterationNUmber % 1000 == 0
                        ? "Iteration: " + iterationNUmber + " - "
                        + "Fault score: " + bestScore + "\n"
                        : "");

            }
            temperature *= coolingRate;
        }

        best.printSudoku();
        System.out.println("Temperature: " + temperature);
        System.out.println("Fault Score of final Solution: " + best.lossFunctionScore());
        System.out.println("Found in " + iterationNUmber + ". iteration ");
        System.out.println("Improvement(initial - final): " + (firstScore - best.lossFunctionScore()));
        Tools.uncorrectCells(best);
    }
}
