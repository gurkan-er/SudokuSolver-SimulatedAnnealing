public class Main {
    public static void main(String[] args) {
        // create sudoku with given cells
        Sudoku initialSudoku = new Sudoku();

        // fill initialSudoku's empty cells with random numbers
        initialSudoku.fillSudokuWithRandomVariables();
        initialSudoku.printSudoku();

        int numberOfError_initialSudoku = initialSudoku.lossFunctionScore();
        System.out.println("Initial Fault Score: " + numberOfError_initialSudoku + "\n");

        // create simulated annealing solver
        SimulatedAnnealing solver = new SimulatedAnnealing(initialSudoku,
                1, 0.0001,
                0.90, 1000);

        /*
         both approaches are simulated annealing,
         but they slightly differ in their implementation
         First Approach's more understandable and more compact
        */
        solver.run_FirstApproach();
//        solver.run_SecondApproach();

        print(solver);
    }

    public static void print(SimulatedAnnealing solver){
        Sudoku bestSudoku = solver.getBestSudoku();
        int numberOfError_bestSudoku = solver.getNumberOfError_bestSudoku();

        System.out.println("\nFault Score of final Solution: " + numberOfError_bestSudoku);
        System.out.println("Found in " + solver.getIterationNumber() + ". iteration ");

        System.out.println("\nFinal Solution:");
        bestSudoku.printSudoku();

        if (numberOfError_bestSudoku != 0){
            Tools.incorrectCells(bestSudoku);
        }
    }
}
