package game.solver;

import java.util.ArrayList;
import java.util.Comparator;

public class Validator {
    public int blockCount, blockNumber;
    public int goalCount, goalNumber;
    public boolean targetAllReached = true;
    public ArrayList<Integer> fixedRed = new ArrayList<Integer>(), currentRed = new ArrayList<Integer>();

    public Validator(int blockNumber, int goalNumber) {
        this.blockCount = 1;
        this.blockNumber = blockNumber;
        this.goalCount = 0;
        this.goalNumber = goalNumber;
    }

    public boolean validate() {
        if(blockNumber > blockCount) return false;
        if(goalCount != goalNumber) return false;
        if(!targetAllReached) return false;
        for(int i=0; i<fixedRed.size(); i++) 
        	if(!currentRed.contains(fixedRed.get(i))) return false;
       
        return true;
    }

    public void clear() {
        blockCount= 1; goalCount = 0;
        targetAllReached = true;
        fixedRed.clear();
        currentRed.clear();
    }

}