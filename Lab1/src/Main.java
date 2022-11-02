import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Random ;
public class Main {

    private static final int iterations = 1000;
    public static void main(String[] args) {

        Matrix matrix  = new Matrix();
        matrix.ReadFile("src/ulysses16(1).csv");
     //makes a matrix to hold all values of the cities
        RandomSearch randomSearch = new RandomSearch(iterations, matrix);
       // randomSearch.Iterate();
        LocalSearch ls =new LocalSearch();

        System.out.println(ls.TwoOptSwap(new int[]{0,1,2,3},0,2).toString());

        }


}