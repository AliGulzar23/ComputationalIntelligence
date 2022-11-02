import java.util.ArrayList;
import java.util.Collections;

public class LocalSearch {

    public static ArrayList<Integer> TwoOptSwap(int[] route, int nodeIndex1, int nodeIndex2){


        //split int array
        ArrayList<Integer> before = new ArrayList<Integer>();
        ArrayList<Integer> middle = new ArrayList<Integer>();
        ArrayList<Integer> remaining = new ArrayList<Integer>();
        for (int index = 0 ; index < route.length; index ++){
            if(index <= nodeIndex1){
                before.add(route[index]);
            } else if (index> nodeIndex2){
                remaining.add(route[index]);
            } else {
                middle.add(route[index]);
            }
        }

        Collections.reverse(middle);
        before.addAll(middle);
        before.addAll(remaining);


        return before;
    }
}
