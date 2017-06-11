package game.solver;

/**
 * Created by juungbae on 2017. 6. 11..
 */
public class Validator {
    private int blockCount, blockNumber;
    private int goalCount, goalNumber;
    private boolean targetAllReached = true;

    public Validator(int blockNumber, int goalNumber) {
        this.blockCount = 0;
        this.blockNumber = blockNumber;
        this.goalCount = 0;
        this.goalNumber = goalNumber;
    }

    public boolean validate() {
        if(blockNumber > blockCount) return false;
        if(goalCount != goalNumber) return false;
        if(!targetAllReached) return false;
        return true;
    }

    public void addGoal() {
        this.goalCount++;
    }

    public void addBlock() {
        this.blockCount++;
    }

    public void targetOff() {
        this.targetAllReached = false;
    }


}
