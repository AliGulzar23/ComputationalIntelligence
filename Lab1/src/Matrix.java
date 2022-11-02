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


    public static double GetCostOfRoute(ArrayList<Integer> intRoute){
        double distance = 0 ;
        int previousNode = intRoute.get(0);
        int currentNode ;
        for(int node : intRoute){
            currentNode = node;
            if(currentNode != previousNode){
                int row = previousNode;
                int column = currentNode;
                if(column> row){
                    row = currentNode;
                    column = previousNode;
                }
                distance += CalculateDistance(xPositions[row],xPositions[column],yPositions[row],yPositions[column]);
            }

        }
        int row = intRoute.get(intRoute.size() - 1);
        int column = intRoute.get(0);
        if(row < column){
            row = intRoute.get(0);
            column= intRoute.get(intRoute.size() - 1);

        }
        distance += CalculateDistance(xPositions[row],xPositions[column],yPositions[row],yPositions[column]);
        return distance;
    }

    private static double CalculateDistance(float x1, float x2, float y1, float y2)
    {
        return Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
    }

}
