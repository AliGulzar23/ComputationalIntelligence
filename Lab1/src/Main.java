import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Random ;
import java.util.Vector;

public class Main {


    private static final int iterations = 10000;
    private static Matrix matrix;
    private static final int antennaCount = 3;
    public static void main(String[] args) {

    SetupMatrix();
    int[] route = new int[] {2, 1, 3, 7, 14, 4, 10, 8, 9, 6, 5, 13, 12, 11, 15, 0};
    //{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}
    //CustomRoute( route);
  //  randomSearch();
    // LocalSearch();
     //AntennaArray();
        evo(50,10000);

    }

    private static void evo(int populationSize, int generations) {
        EvolutionaryAlgorithm evo = new EvolutionaryAlgorithm(populationSize,generations,matrix);
        evo.InitialisePopulation();
        evo.runGenerations();
    }

    private static void AntennaArray() {
        AntennaArray aa = new AntennaArray(antennaCount,90);
        // System.out.println(aa.evaluate();
        Swarm swarm = new Swarm(antennaCount,iterations,aa);
        swarm.run();
    }

    private static void CustomRoute(int[] route) {

        ArrayList<Integer> introute = new ArrayList<Integer>();
        for(int i : route){
            introute.add(i);
        }
         System.out.println(matrix.GetCostOfRoute(introute));
    }

    private static void LocalSearch() {
        LocalSearch ls =new LocalSearch(matrix);
        int[] intRoute =     new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        //randomise the route

        ArrayList<Integer> route = new ArrayList<Integer>();
        for (int i : intRoute){
            route.add(i);
        }
        Collections.shuffle(route);
        intRoute = new int[route.size()];
        for (int i = 0; i < route.size(); i ++){
            intRoute[i] = route.get(i);
        }
        ls.iterate(intRoute,iterations);
    }

    private static void SetupMatrix() {
        matrix  = new Matrix();
        matrix.ReadFile("src/ulysses16(1).csv");
    }

    private static void randomSearch() {
        //makes a matrix to hold all values of the cities
        RandomSearch randomSearch = new RandomSearch(iterations, matrix);
        randomSearch.Iterate();
    }


}