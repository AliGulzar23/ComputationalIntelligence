/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computational.intelligence.lab.pkg1;
import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;


     
/**
 *
 * @author 180058062
 */
public class ComputationalIntelligenceLab1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        /*
        // TODO code application logic here
    int[][] edges = new int[4][4];
    //a is 0 d is 3
    //find way to automate this
    int[] edgeNumbers = {20,42,35,30,34,12};
    int counter = 0;
    for (int i = 0; i<4;i++){
       for(int j = 0; j<4;j++){
           if(i!=j && j<i){
               edges[j][i]=edgeNumbers[counter];
                counter++;

           }
       }
    }
    String path = "ADCB";
    String[] paths = new String[4]; //get legnth of the path
    //fill in paths with each possible path
    HashMap<Character,Integer> dictionary = new HashMap<>();
    char[] nodes = "ABCD".toCharArray();
        getCostOfRoute(nodes, dictionary, path, edges);
*/
        Nodes nodes = new Nodes("src\\computational\\intelligence\\lab\\pkg1\\ulysses16(1).csv");
        int cityNumber = nodes.getCities();
        Paths paths = new Paths(cityNumber,nodes);
        paths.printMatrix();
        int[] routePath = new int[cityNumber+1];//length +1 so that it loops back to the beggining
        routePath[0] = 0;
        routePath[cityNumber] = 0;
        ArrayList<Integer> pathToShuffle = new ArrayList<Integer>();
        for(int i =1;i<=cityNumber-1;i++){
            pathToShuffle.add(i);
        }
        int[] bestRoute = new int[cityNumber+1];
        double bestRouteWeight =10000 ;
         boolean leave = false;
         long startTime = System.currentTimeMillis();
       while(!leave){
        Collections.shuffle(pathToShuffle);
        System.out.println(pathToShuffle);
        for (int i = 1; i<cityNumber;i++){
            routePath[i] = pathToShuffle.get(i-1);
        }
      
                   double costOfRoute = getCostOfRoute(routePath,paths);
                   if(costOfRoute < bestRouteWeight){
                       bestRouteWeight = costOfRoute;
                       bestRoute = routePath;
                       
                   }
            long elapsedTime = System.currentTimeMillis() - startTime;
            if(elapsedTime/1000 == 300){
                leave = true;
            }
       }
        System.out.println("Best found route is:    " +bestRoute.toString() +"\nwith weight:   " + bestRouteWeight);
        
    }
    

    private static void getCostOfRoutefake(char[] nodes, HashMap<Character, Integer> dictionary, String path, int[][] edges) {
        int index = 0;
        for(char node :nodes){
            dictionary.put(node, index);
            index++;
        }
        System.out.println("dic "+dictionary.get('A'));
        int distance = 0;
        //get the path, get two nodes and use them to find the distatnce
        char previousNode = '\0';
   
        for(char node:path.toCharArray()){
            if(previousNode== '\0'){
                previousNode = node;
            }else{
                //check if path is in order
                int previousNodeIndex = dictionary.get(previousNode);
                int nodeIndex = dictionary.get(node);
                if(nodeIndex < previousNodeIndex){
                    //reverse the patht to correct it
                    
                    
                    distance += edges[nodeIndex][previousNodeIndex];
                } else{
                    //since I've used a upper trinage matrix, the wrong order will not yield a correct value. 
                    distance += edges[previousNodeIndex][nodeIndex];
                    
                }
                previousNode = node;
                
            }
        }
        //add the final distance
        distance += edges[0][dictionary.get(previousNode)];
        System.out.println("Distance    "+distance);
    }
    private static double getCostOfRoute(int[] routePath,Paths paths){
        double costOfRoute = 0;
        int previousNode= 0; //starting off with the first node
        int counter = 0;
        for(int node :routePath){
            if(node<previousNode){
                costOfRoute+= paths.getDistance(node,previousNode);
                
            }else{
                costOfRoute+= paths.getDistance(previousNode, node);

            }
            System.out.println(counter+" "+costOfRoute+ "   " + previousNode + "    " + node);
            previousNode = node;
            counter++;
        }
costOfRoute+= paths.getDistance(previousNode, 0);
return costOfRoute;
    }
    
    
}
