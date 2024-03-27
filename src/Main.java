public class Main {
    // create sudoku with given cells
    private static final Sudoku givenSudoku = new Sudoku();

    public static void main(String[] args) {

        // fill givenSudoku's empty cells with random numbers
        givenSudoku.fillSudokuWithRandomVariables();
        printGivenSudoku();

        // create simulated annealing solver
        SimulatedAnnealing solver = new SimulatedAnnealing(givenSudoku,
                1, 0.0001,
                0.90, 1000);

        /*
         both approaches are simulated annealing,
         but they've slightly different implementation
         First Approach's more understandable and more compact
        */
        run(solver);
        printSolvedSudoku(solver);
    }

    public static void run(SimulatedAnnealing solver) {
        long start = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            solver.run_FirstApproach();
//            solver.run_SecondApproach();
        }
        long finish = System.nanoTime();

        System.out.println("Elapsed time: " + (finish - start) / 1000000 + " ms");
    }

    public static void printGivenSudoku() {
        int numberOfError_givenSudoku = givenSudoku.lossFunctionScore();
        System.out.println("Initial Fault Score: " + numberOfError_givenSudoku);

        givenSudoku.printSudoku();
    }

    public static void printSolvedSudoku(SimulatedAnnealing solver) {
        Sudoku bestSudoku = solver.getBestSudoku();
        int numberOfError_bestSudoku = solver.getNumberOfError_bestSudoku();

        System.out.println("\nFault Score of final Solution: " + numberOfError_bestSudoku);
        System.out.println("Found in " + solver.getIterationNumber() + ". iteration ");

        System.out.println("\nFinal Solution:");
        bestSudoku.printSudoku();

        if (numberOfError_bestSudoku != 0) {
            Tools.incorrectCells(bestSudoku);
        }
    }
}
