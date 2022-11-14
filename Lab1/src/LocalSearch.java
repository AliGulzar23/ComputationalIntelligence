import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LocalSearch {

   private static  Matrix matrix;
    public LocalSearch(Matrix matrix){
        this.matrix = matrix;

    }

    //two opt swap
    public static int[] swap(int[] route, int i, int k) {
        //two opt swap method
     //   System.out.println(Arrays.toString(route));
     //   System.out.println("swapping " + i + " with " + k);
        int[] newRoute = new int[route.length];
        for (int c = 0; c <= i - 1; c++) {
            newRoute[c] = route[c];
        }
        int dec = 0;
        for (int c = i; c <= k; c++) {
            newRoute[c] = route[k - dec];
            dec++;
        }
        for (int c = k + 1; c < route.length; c++) {
            newRoute[c] = route[c];
        }
        //System.out.println(matrix.GetCostOfRoute(listToArray(newRoute)));
        return newRoute;
    }
    public static int[] swapAll(int[] route, int i) {
        //gets a neigtbourhood and find the best route
        double bestDistance = matrix.GetCostOfRoute(listToArray(route));
        int[] bestRoute = route;
        int[] newRoute = new int[route.length];
        for (int c = 0; c < route.length; ++c) {
            newRoute[c] = route[c];
        }
        for (int k = 0; k < route.length; k++) {
            if (k != i) {
                newRoute = swap(newRoute, i, k);
                double newDistance = matrix.GetCostOfRoute(listToArray(newRoute));
                if (newDistance < bestDistance) {
                    bestDistance = newDistance;
                    bestRoute = newRoute;
                //    System.out.println("new best distance: " + bestDistance);
             //       System.out.println(Arrays.toString(bestRoute));
                }

            }
        }
        return bestRoute;
    }

public static int[] swapAllInRoute(int[] route){
        //neighbourhood step function
        double bestDistance = matrix.GetCostOfRoute(listToArray(route));
        int[] bestRoute = route;
        int[] newRoute = new int[route.length];
        for (int c = 0; c < route.length; ++c) {
            newRoute[c] = route[c];
        }
        for (int i = 0; i < route.length; i++) {
            newRoute = swapAll(newRoute, i);
            double newDistance = matrix.GetCostOfRoute(listToArray(newRoute));
            if (newDistance < bestDistance) {
                bestDistance = newDistance;
                bestRoute = newRoute;
              //  System.out.println("new best distance: " + bestDistance);
              //  System.out.println(Arrays.toString(bestRoute));
            }
        }
        return bestRoute;
    }
    public void iterate(int[] route, int iterations){
        int[] bestRoute = route;
        double bestDistance = matrix.GetCostOfRoute(listToArray(route));

        for (int i = 0; i < iterations; i++){

           int[] tempRoute =  swapAllInRoute(route);
              double tempDistance = matrix.GetCostOfRoute(listToArray(tempRoute));
              if (tempDistance < bestDistance && tempDistance >=74.1087){
                  bestDistance = tempDistance;
                  bestRoute = tempRoute;
                  //System.out.println("new best distance: " + bestDistance);
                 // System.out.println(Arrays.toString(bestRoute));
              }
            //randomise the route
            ArrayList arrayList = listToArray(route);
            Collections.shuffle(arrayList);
            int[] routeList = arrayToList(arrayList);
            route = routeList;
        }
        System.out.println("best distance: " + bestDistance);
        System.out.println(Arrays.toString(bestRoute));
    }
    //array to list
    public static int[] arrayToList (ArrayList<Integer> array){
        int[] list = new int[array.size()];

        for (int i = 0; i < array.size(); i++){
            list[i] = array.get(i);
        }
        return list;
    }





    public static ArrayList<Integer> listToArray(int[] list){
        ArrayList<Integer> array = new ArrayList<Integer>();
        for(int i : list){
            array.add(i);
        }
        return  array;
    }
}
