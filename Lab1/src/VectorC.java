import java.util.Random;
public class VectorC {
    private  int dimensions;
    private   double[] vector;
    public VectorC(int dimensions){
        this.dimensions = dimensions;
        vector = new double[dimensions];
    }
    public  void set(int index, double value){
        vector[index] = value;
    }
    public  double get(int index){
        return vector[index];
    }
    public double[] getVector(){
        return vector;
    }
    public  void randomiseVector(double[][] bounds){
        Random r = new Random();
        double[] randomVector = new double[dimensions];
        for (int i = 0; i < dimensions; i++){
         double min = bounds[i][0];
          double max = bounds[i][1];
            double random = min + r.nextDouble() * (max - min);
            //rounds the random number to 2 decimal places
          //  random = Math.round(random * 100.00) / 100.00;
            randomVector[i] = random;
        }
        vector = randomVector;
    }
    public  void randomVector(){
        //returns a random vector that is between 0 and 1
        Random r = new Random();
        double[] randomVector = new double[dimensions];
        for (int i = 0; i < dimensions; i++){
            randomVector[i] = r.nextDouble();
        }
        vector = randomVector;
    }

}
