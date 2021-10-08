/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computational.intelligence.lab.pkg1;

/**
 *
 * @author 180058062
 */
public class Paths {
    double[][] paths ;
   
    public Paths(int pathLength,Nodes node){
        paths = new double[pathLength][pathLength];
        int counter = 0;
        for(int collumn = 0; collumn <pathLength; collumn ++){
            for (int row =0; row<pathLength;row++){
                if(collumn!=row&&collumn<row){
                paths[row][collumn] = node.getDistace(row, collumn);
    
                }
            }
        
    }
        
}
    public void printMatrix(){
        for(int i= 0;i<paths.length;i++){
            for(int j =0;j<paths.length;j++){
                System.out.print(paths[j][i]+"    ");
            }
            System.out.println();
        }
            
    }
    public void printDistance(int collumn,int row){
        System.out.println(paths[row][collumn]);
    }
    public double getDistance(int collumn, int row){
        return paths[row][collumn];
    }
}
