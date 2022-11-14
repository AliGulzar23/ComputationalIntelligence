import  java.util.Random;

public class Particle {
    //Vectors on each particle
    private  int dimensions;
  private  VectorC position;
    private  VectorC velocity;
    private double velMax = 0.3;
    public  VectorC bestPosition;

    public  double personalBest;
    public double fitness;
    AntennaArray antennaArray;

    //parameter sets that are used in randomising the movement of the paritcles
    private  final double inertial = 1/(2*(Math.log(2)));
    private  final double cognitive = 0.5 + Math.log(2);
    private  final double social = 0.5 + Math.log(2);
    public Particle(int dimensions,AntennaArray aa){
        this.antennaArray = aa;
        this.dimensions = dimensions;
        position = new VectorC(dimensions);
        bestPosition = new VectorC(dimensions);
        velocity = new VectorC(dimensions);
    }

    public void initialise(double[][] bounds){
        //better way to generate random particles
       this.position.randomiseVector(bounds);
        this.bestPosition = position;
        this.personalBest = antennaArray.evaluate(this.bestPosition.getVector());
       VectorC temp = new VectorC(dimensions);
       temp.randomiseVector(bounds);
       //set up velocity
        for (int i = 0; i < dimensions; i++){
            double vel = temp.get(i) - position.get(i);
            velocity.set(i, getClampedVelocity(vel));
        }

    }
    public double getClampedVelocity(double velocity){
        if (velocity > velMax){
            return velMax;
        }
        else if (velocity < -velMax){
            return -velMax;
        }
        else {
            return velocity;
        }
    }
    private VectorC  VectorInBounds(double[][] bounds) {
        //returns a random vector in bounds
        VectorC vector = new VectorC(dimensions);

        for (int i = 0 ; i < dimensions ; i ++){
            double min = bounds[i][0];
            double max = bounds[i][1];
            //gets the correct bounds of the vector

            double random = min + Math.random() * (max - min);
            //gets the values of the position at the correct bounds
            vector.set(i,random);
        }


        return vector;
    }

    //create uniform random vectors
   public VectorC RandomVector(){
   //returns a random vector

       Random r = new Random();
       VectorC vector = new VectorC(dimensions);

       for (int i = 0; i < dimensions; i++){
           vector.set(i, r.nextDouble());
       }
       return vector;
    }

    public VectorC CognitiveAttraction(){
        VectorC cognitiveAttraction = new VectorC(dimensions);
        VectorC cognitiveAttractionRand = RandomVector();
        for(int i = 0; i < dimensions; i++){
            //calculates the cognitive attraction of the paritcle using its best position and the random vector
            cognitiveAttraction.set(i, cognitiveAttractionRand.get(i) * (bestPosition.get(i) - position.get(i)));
        }
        return cognitiveAttraction;
    }
    public VectorC SocialAtrraction(VectorC globalBest){
        VectorC socialAttraction = new VectorC(dimensions);
        VectorC socialAttractionRand = RandomVector();
        for(int i = 0; i < dimensions; i++){
            //calculates the social attraction of the paritcle using the global best position and the random vector
            socialAttraction.set(i, socialAttractionRand.get(i) * (globalBest.get(i) - position.get(i)));
            }
        return socialAttraction;
    }
    private  void updateVelocity(VectorC globalBest) {
        //updates the velocity of the particle

        //gets new random velocities for the vectors
        VectorC cognitiveAttraction = CognitiveAttraction();
        VectorC socialAttraction = SocialAtrraction(globalBest);
        for (int i = 0; i < dimensions; i++) {
            //calculates the new velocity of the particle
            double velocityValue = (inertial * velocity.get(i)) + (cognitive * cognitiveAttraction.get(i)) + (social * socialAttraction.get(i));
            velocity.set(i, getClampedVelocity(velocityValue));

        }
    }
    public  void updatePosition(VectorC globalBest){
        //updates the position of the particle
        updateVelocity(globalBest);
        //gets the new velocity of the paritcle
        for(int i = 0; i < dimensions; i++){
            //adds the position elemens to the velocity elements
            double value = position.get(i) + velocity.get(i);
            //checks if the value is in bounds
            if (value > 1.5){
                value = 1;
            }
            else if (value < 0){
                value = 0;
            }
           value = Math.abs(Math.round(value * 10.0) / 10.0);
            position.set(i, value);
        }


    }



    public double GetPersonalBest() {
        return personalBest;
    }
    public void SetPersonalBest(double personalBest) {
        this.personalBest = personalBest;
    } //they will be dealt with in swarm class
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
    public double getFitness() {
        return fitness;
    }
    public VectorC GetBestPosition() {
        return bestPosition;
    }
    public VectorC getPosition() {
        return position;
    }
    public void evaluate(){
        //compares the fitness to the pb and sets the pb if the fitness is better
        if(fitness < personalBest){
            personalBest = fitness;
            bestPosition = position;
        }
    }
}
