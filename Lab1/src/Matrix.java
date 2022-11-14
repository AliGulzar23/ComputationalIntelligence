import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Matrix {

    //reads file and crates a matrix from it
  private  static float[] xPositions ; //this will represent the row of the matrix;

    private static float[] yPositions;  //this will represent the column of the matrix;
    private   static int matrixSize ;
    private static ArrayList<String[]> data;

    public static void ReadFile(String path){

        try {
            Scanner scanner = new Scanner(new File("src/ulysses16(1).csv"));
            data  = new ArrayList<String[]>();
            boolean collecting = false;
            while (scanner.hasNext()) {
                String line = scanner.next();
                if (line.contains(",x,y")) {
                    collecting = true;
                    line = scanner.next();
                }
                if (collecting) {
                    data.add(line.split(","));

                }
            }
            //make a node matrix in the size of this
            matrixSize = data.size();
            xPositions = new float[matrixSize];
            yPositions = new float[matrixSize];
            for(int i = 0 ; i < data.size(); i ++ ){
                xPositions[i] = Float.valueOf(data.get(i)[1]);
                yPositions[i] = Float.valueOf(data.get(i)[2]);

            }

        }  catch (FileNotFoundException ex) {
            System.out.println("FILE NOT FOUND");
        }
    }
    public int GetMatrixSize(){
        return matrixSize;
    }


    public double GetCostOfRoute(ArrayList<Integer> route){
        float cost = 0;
        for(int i = 0 ; i < route.size() - 1; i ++){
            cost += GetCostOfTwoNodes(route.get(i),route.get(i+1));
        }
        cost += GetCostOfTwoNodes(route.get(route.size()-1),route.get(0));
        return cost;
    }
    //gets the cost of two nodes
    public float GetCostOfTwoNodes(int node1, int node2){
        float x1 = xPositions[node1];
        float y1 = yPositions[node1];
        float x2 = xPositions[node2];
        float y2 = yPositions[node2];
        return (float) Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
    }



}
