import java.util.Arrays;
import java.util.Random;

public class SimulatedAnnealingTSP {
    static double coolingRate = 0.99999999;
    static double initialTemperature = 100000;
    public static void main(String[] args) {

        int[][] distanceMatrix = {
                {0, 2, 9, 10},
                {1, 0, 6, 4},
                {15, 7, 0, 8},
                {6, 3, 12, 0}
        };

        int[] initialSolution = {0, 1, 2, 3}; // Initial solution: visit cities in order

        int[] finalSolution = simulatedAnnealingTSP(distanceMatrix, initialSolution);

        System.out.println("Final Solution: " + Arrays.toString(finalSolution));
        System.out.println("Total Distance: " + calculateTotalDistance(distanceMatrix, finalSolution));
    }

    public static int[] simulatedAnnealingTSP(int[][] distanceMatrix, int[] currentSolution) {
        int[] bestSolution = Arrays.copyOf(currentSolution, currentSolution.length);
        double currentEnergy = calculateTotalDistance(distanceMatrix, currentSolution);
        double bestEnergy = currentEnergy;

        Random random = new Random();

        double temperature = initialTemperature;

        while (temperature > 1) {
            int[] newSolution = generateNeighbor(currentSolution);
            double newEnergy = calculateTotalDistance(distanceMatrix, newSolution);

            if (acceptanceProbability(currentEnergy, newEnergy, temperature) > random.nextDouble()) {
                currentSolution = Arrays.copyOf(newSolution, newSolution.length);
                currentEnergy = newEnergy;
            }

            if (currentEnergy < bestEnergy) {
                bestSolution = Arrays.copyOf(currentSolution, currentSolution.length);
                bestEnergy = currentEnergy;
            }

            temperature *= coolingRate;
        }

        return bestSolution;
    }

    public static int[] generateNeighbor(int[] currentSolution) {
        // Swap two random cities to generate a neighbor
        Random random = new Random();
        int[] neighbor = Arrays.copyOf(currentSolution, currentSolution.length);

        int index1 = random.nextInt(currentSolution.length);
        int index2 = random.nextInt(currentSolution.length);

        int temp = neighbor[index1];
        neighbor[index1] = neighbor[index2];
        neighbor[index2] = temp;

        return neighbor;
    }

    public static double acceptanceProbability(double currentEnergy, double newEnergy, double temperature) {
        if (newEnergy < currentEnergy) {
            return 1.0;
        }
        return Math.exp((currentEnergy - newEnergy) / temperature);
    }

    public static double calculateTotalDistance(int[][] distanceMatrix, int[] solution) {
        double totalDistance = 0;
        for (int i = 0; i < solution.length - 1; i++) {
            totalDistance += distanceMatrix[solution[i]][solution[i + 1]];
        }
        // Return to the starting city
        totalDistance += distanceMatrix[solution[solution.length - 1]][solution[0]];
        return totalDistance;
    }
}