import java.util.ArrayList;
import java.util.Collections;

public class RandomSearch {
    private static int iterations;
    private static Matrix matrix;
    private static double[] distances ;
    private static int matrixSize;

    public RandomSearch(int iterations, Matrix matrix){
        this.iterations = iterations;
        this.matrix = matrix;
        matrixSize  = matrix.GetMatrixSize();

    }
    public static double Iterate(){
        //iterates though a number of random routes, then returns the minimum distance from it
        distances = new double [iterations];
        String[] routes = new String[iterations];
        ArrayList<Integer> introute = new ArrayList<Integer>();
        for (int i = 0; i < matrixSize; i ++){
            introute.add(i);
        }
        for (int index = 0 ; index < iterations; index ++){
            Collections.shuffle(introute);
            routes[index] = introute.toString();
            distances[index] = matrix.GetCostOfRoute(introute);
        }
        double minDistance = distances[0];
        int count  = 0 ;
        for (double distance : distances){

            if(minDistance > distance){
                System.out.println("new minimum found. Distance: "+distance+ " \nRoute: " +routes[count]  );
                System.out.println();
                minDistance = distance;
            }
            count ++;
        }
        return minDistance;
    }
    public static double[] GetDistances(){
        //returns the distances array after the iteration of them
        return distances;
    }

}
