import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class EvolutionaryAlgorithm {
    //this class is the main class for the evolutionary algorithm
    //it is responsible for the initialisation of the population and the selection of the best routes
    private  int populationSize;
    private  int iterations;
    private  Matrix matrix;
    private  int[][] population;
    private  int matrixSize;
    private static double[] distances;
    private static int[] bestRoute;
    private static double bestDistance;

    public EvolutionaryAlgorithm(int populationSize, int iterations, Matrix matrix) {
        this.populationSize = populationSize;
        this.iterations = iterations;
        this.matrix = matrix;
        matrixSize = matrix.GetMatrixSize();
        distances = new double[populationSize];
    }
    public  void InitialisePopulation() {
        //fills population with random routes
        population = new int[populationSize][matrixSize];
        for (int i = 0; i < populationSize; i++) {
            population[i] = GetRandomRoute(matrixSize);
            distances[i] = matrix.GetCostOfRoute(ArrayToList(population[i]));


        }
        bestRoute = GetBestRoute(population);
    }

    public  int[] GetRandomRoute(int matrixSize) {
        //returns a random route
        ArrayList<Integer> introute = new ArrayList<Integer>();
        for (int i = 0; i < matrixSize; i++) {
            introute.add(i);
        }
        Collections.shuffle(introute);
        int[] route = new int[matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            route[i] = introute.get(i);
        }
        return route;
    }

    public  ArrayList<Integer> ArrayToList(int[] array) {
        //converts an array to an arraylist
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }
    // get fitness of a route
    public  double GetFitness(int[] route) {
        return 1 / matrix.GetCostOfRoute(ArrayToList(route));
    }


    public  double[] NormaliseFitness(double[] fitness) {
        //normalises the fitness of the routes
        double[] normalisedFitness = new double[fitness.length];
        double totalFitness = 0;
        for (int i = 0; i < fitness.length; i++) {
            totalFitness += fitness[i];
        }
        for (int i = 0; i < fitness.length; i++) {
            normalisedFitness[i] = fitness[i] / totalFitness;
        }
        return normalisedFitness;
        //fitness is normalised to be represented as a probability
    }
    public  int tournamentSelection(double[] normalisedFitness) {
        double random = Math.random();
        double cumulativeProbability = 0;
        for (int i = 0; i < normalisedFitness.length; i++) {
            cumulativeProbability += normalisedFitness[i];
            if (random < cumulativeProbability) {
                return i;
            }
        }
        return 0;
    }
    public  int[][] NewGeneration(int[][] population, double[] normalisedFitness) {
        int[][] newPopulation = new int[populationSize][matrixSize];
        for (int i = 0; i < populationSize; i++) {
            int parent1 = tournamentSelection(normalisedFitness);
            int parent2 = tournamentSelection(normalisedFitness);
            int[] child = Crossover(population[parent1], population[parent2]);

            newPopulation[i] = child;
        }
        return newPopulation;
    }

    public  int[] Crossover(int[] parent1, int[] parent2) {
        int[] child = new int[matrixSize];
        int start = (int) (Math.random() * matrixSize);
        int end = (int) (Math.random() * matrixSize);
        int[] p1Select = new int[parent1.length];
        int[] p2Select = new int[parent2.length];
        for (int i = 0 ; i < parent1.length ; i ++){
            if(i >= start && i <= end) {
               p1Select[i] = parent1[i];
            } else{
                p2Select[i] = parent2[i];
            }

        }

        for (int i = 0; i < parent1.length; i ++){
            if(i >= start && i < end) {
                child[i] = p1Select[i];
            }
            else {
                child[i] = p2Select[i];
            }
        }





        return child;
    }
    //best route from the population
    public  int[] GetBestRoute(int[][] population) {
        int[] bestRoute = new int[matrixSize];
        double bestDistance = Double.MAX_VALUE;
        for (int i = 0; i < populationSize; i++) {
            double distance = matrix.GetCostOfRoute(ArrayToList(population[i]));
            if (distance < bestDistance) {
                bestDistance = distance;
                bestRoute = population[i];
            }
        }
        return bestRoute;
    }

    public   void runGenerations(){
        //runs the algorithm for a number of generations
        for (int i = 0; i < iterations; i++) {
            double[] fitness = new double[populationSize];
            for (int j = 0; j < populationSize; j++) {
                fitness[j] = GetFitness(population[j]);
            }
            double[] normalisedFitness = NormaliseFitness(fitness);
            population = NewGeneration(population, normalisedFitness);
            if(matrix.GetCostOfRoute(ArrayToList(GetBestRoute(population))) >= 74.1087) {
                bestRoute = GetBestRoute(population);
                double distance = matrix.GetCostOfRoute(ArrayToList(bestRoute));
                System.out.println("Best distance: " + distance);
            }
        }

    }
    //gets the best route from the population
    public  int[] GetBestRoute(){
        return GetBestRoute(population);
    }

}
