/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computational.intelligence.lab.pkg1;
import java.io.*;
import java.util.Scanner;
import java.lang.Math;

/**
 *
 * @author gulza
 */
public class Nodes {
    int cities;
    double[] xCoords;
    double[]yCoords;
    public Nodes(String file ) throws Exception{
       
        cities = calculateCitiesNumber(file);
        xCoords  = new double[cities];
        yCoords = new double[cities];
        boolean isData = false;
        Scanner sc = new Scanner(new File(file));

        while(sc.hasNext()){
           String line = sc.next();
            if(line.contains(",x,y")){
                
                isData = true;
                line = sc.next();
            }
            if(isData){
                
              String[] lineArr = line.split(",");
              int nodeIndex = Integer.parseInt(lineArr[0])-1; // have to be -1 since arrays start at 0
                xCoords[nodeIndex] = Double.parseDouble(lineArr[1]);
                yCoords[nodeIndex] = Double.parseDouble(lineArr[2]); 
            }
        }
    }
    public double getDistace (int first,int second){
        double xDiff = Math.pow((xCoords[second]-xCoords[first]),2); 
        double yDiff = Math.pow((yCoords[second]-yCoords[first]),2); 
        return  Math.sqrt(xDiff+yDiff);
    }
    public int calculateCitiesNumber(String file) throws FileNotFoundException{
        
        boolean isData = false;
        Scanner sc = new Scanner(new File(file));
        while(sc.hasNext()){
            String line = sc.next();
            if(line.contains(",x,y")){
                isData = true;
                line = sc.next();
            }
            if(isData){
                cities ++;
                
            }
        }
        return cities;
    }
    public int getCities(){return cities;}
    
}
