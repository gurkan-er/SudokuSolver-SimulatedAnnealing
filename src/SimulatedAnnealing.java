
public class SimulatedAnnealing {
    private final Sudoku initialSudoku;
    private Sudoku bestSudoku;
    private double temperature;
    private final double temperatureMinimum;
    private final double coolingRate;
    private final int maxIterations;
    private int iterationNumber = 0;

    public SimulatedAnnealing(Sudoku initialSudoku,
                              double temperature, double temperatureMinimum,
                              double coolingRate, int maxIterations)
    {
        this.initialSudoku = initialSudoku;
        this.bestSudoku = initialSudoku.duplicate();

        this.temperature = temperature;
        this.temperatureMinimum = temperatureMinimum;
        this.coolingRate = coolingRate;

        this.maxIterations = maxIterations;
    }

    public void run_FirstApproach() {
        Sudoku currentSudoku = this.initialSudoku.duplicate();

        int numberOfError_bestSudoku = bestSudoku.lossFunctionScore();
        // First Approach
        /*
        if we control numberOfError_bestSudoku in while,
        probability function will be more effective but
        I want to get exact iteration Number.
        */
        while(temperature > temperatureMinimum){
            for(int i = 0; i < maxIterations && numberOfError_bestSudoku != 0; i++){
                Sudoku neighbour = currentSudoku.duplicate();
                neighbour.swapCells();

                int numberOfError_neighbour = neighbour.lossFunctionScore();
                int numberOfError_currentSudoku = currentSudoku.lossFunctionScore();

                if (Math.random() < Tools.probability(numberOfError_currentSudoku, numberOfError_neighbour, temperature)){
                    currentSudoku = neighbour.duplicate();
                }
                if (numberOfError_currentSudoku < numberOfError_bestSudoku){
                    bestSudoku = neighbour.duplicate();
                    numberOfError_bestSudoku = numberOfError_neighbour;
                }

                iterationNumber++;
                System.out.print(iterationNumber % 1000 == 0
                        ? "Iteration: " + iterationNumber + " - "
                        + "Fault score: " + numberOfError_bestSudoku + "\n"
                        : "");

            }
            temperature *= coolingRate;
        }
    }

    public void run_SecondApproach() {
        Sudoku currentSudoku = this.initialSudoku.duplicate();

        int numberOfError_bestSudoku = bestSudoku.lossFunctionScore();
        // Second Approach
        /*
        if we control numberOfError_bestSudoku in while,
        probability function will be more effective but
        I want to get exact iteration Number.
        */
        while(temperature > temperatureMinimum){
            for(int i = 0; i < maxIterations && numberOfError_bestSudoku != 0; i++){
                Sudoku neighbour = currentSudoku.duplicate();

                int row1 = (int)(Math.random() * 9);
                int col1 = (int)(Math.random() * 9);
                int row2 = (int)(Math.random() * 9);
                int col2 = (int)(Math.random() * 9);
                neighbour.swapCells(row1, col1, row2, col2);

                int numberOfError_neighbour = neighbour.lossFunctionScore();
                int numberOfError_currentSudoku = currentSudoku.lossFunctionScore();

                if(numberOfError_neighbour < numberOfError_currentSudoku){
                    currentSudoku = neighbour.duplicate();
                    bestSudoku = neighbour.duplicate();
                    numberOfError_bestSudoku = numberOfError_neighbour;

                } else {
                    if(Math.random() < Tools.probability(numberOfError_currentSudoku, numberOfError_neighbour, temperature)){
                        currentSudoku = neighbour.duplicate();
                    } else{
                        neighbour.swapCells(row2, col2, row1, col1);
                    }
                }

                iterationNumber++;
                System.out.print(iterationNumber % 1000 == 0
                        ? "Iteration: " + iterationNumber + " - "
                        + "Fault score: " + numberOfError_bestSudoku + "\n"
                        : "");

            }
            temperature *= coolingRate;
        }
    }

    public Sudoku getBestSudoku() { return bestSudoku; }
    public int getNumberOfError_bestSudoku() { return bestSudoku.lossFunctionScore(); }
    public int getIterationNumber() { return iterationNumber; }
}