public class Swarm {
    private static double globalBest ;
    private static VectorC globalBestPosition;
    private static int dimensions;
    private static int iterations;
    private static int particles;
    private static Particle[] swarm; //the swarm of particles
    AntennaArray aa ;
    public Swarm(int dimensions, int iterations, AntennaArray aa){
        this.dimensions = dimensions;
        this.iterations = iterations;
       this.particles = 20 + (int)(Math.sqrt(dimensions));
     //  this.particles = 1;
        this.aa  = aa; //the antenna array
        swarm = new Particle[particles];
        for(int i = 0; i < particles; i++){
            swarm[i] = new Particle(dimensions,aa);
            swarm[i].initialise(aa.bounds());
        }
        globalBestPosition = new VectorC(dimensions);
        globalBestPosition.randomiseVector(aa.bounds());
      //  double[] best = new double[] {0.5,1.0,1.5};
        //for (int i = 0; i < dimensions; i++){
        //    globalBestPosition.set(i, best[i]);
     //   }
        globalBest = aa.evaluate(globalBestPosition.getVector());

    }
    public void run() {
        for (int i = 0; i < iterations; i++) {
            for (int j = 0; j < particles; j++) {
                swarm[j].updatePosition(globalBestPosition);
                evaluate(j);
                if (swarm[j].GetPersonalBest() < globalBest) {
                    globalBest = swarm[j].GetPersonalBest();
                    globalBestPosition = swarm[j].GetBestPosition();
                    evaluate(j);
                    System.out.println( globalBest);
                }
            }

        }
        if(globalBest == Double.MAX_VALUE){
            System.out.println("No solution found");
        }
        else {

            System.out.println("Global Best: " + globalBest);
            for (double i : globalBestPosition.getVector()) {
                System.out.print(i + " ");
            }
        }
    }
    public void evaluate(int index){
    swarm[index].setFitness(aa.evaluate(swarm[index].getPosition().getVector()));
    //gets the fitness of the swarm
        swarm[index].evaluate();

    }
    }





