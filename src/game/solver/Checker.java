package game.solver;

import game.test.Case;
import org.paukov.combinatorics3.CombinationGenerator;
import org.paukov.combinatorics3.Generator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Checker {

    private Block b = null;

    private int[][] base;
    private int[] blockRange = null;

    private CombinationGenerator<Integer> generator;

    private int maxBlockCase = 1;
    private int goalCount= 0;
    private int blockCount = 0;
    private int startLocation = -1;
    private int randomBlocksSize = 0;

    private ArrayList<BlockStatus>  randomBlocksFixed = new ArrayList<>();
    private ArrayList<Integer>      randomBlocks = new ArrayList<>();

    private class BlockStatus {
        int color;
        int location;
        BlockStatus(int color, int location) { this.color = color; this.location = location;}
    }

    public Checker(Problem problem) {
        this.base = problem.Map;
        this.goalCount= problem.GoalCount;

        for(int i=1; i<=6; i++)
            for(int j=0; j<problem.AddToGrid.get(i); j++)
                randomBlocks.add(i);

        init();
    }

    public Checker(Case.TestCase test) {
        this.base = test.getMap();
        this.goalCount= test.getGoalCount();

        for(int i=1; i<=6; i++)
            for(int j=0; j<test.getAddToGrid().get(i); j++)
                randomBlocks.add(i);
    
        init();
    }

    private void init() {
        int[] range = new int[]{0, 4, 2, 2, 4, 2, 4, 1};

        ArrayList<Integer> minList = new ArrayList<>();
        for(int i=0; i<5; i++)
            for(int j=0; j<5; j++) {
                blockCount++;

                if (base[i][j] == 0)
                    { minList.add(i * 5 + j); blockCount--;}
                else if (base[i][j] % 10 == 5)
                    randomBlocksFixed.add(new BlockStatus(base[i][j] / 10, i * 5 + j));
                else if(base[i][j] / 10 == 7)
                    blockCount--;

                if(randomBlocks.get(0) != 1 && base[i][j] / 10 == 1)
                    startLocation = i*5+j;
            }
        //location combination
        randomBlocksSize = randomBlocks.size() + randomBlocksFixed.size();
        blockRange = new int[randomBlocksSize];

        this.generator = Generator.combination(minList);

        for(int i=0; i<randomBlocksSize; i++) {
            maxBlockCase *= (i < randomBlocks.size() ?
                                range[randomBlocks.get(i)] :
                                range[randomBlocksFixed.get(i - randomBlocks.size()).color]);

            blockRange[i] = (i < randomBlocks.size() ?
                                range[randomBlocks.get(i)] :
                                range[randomBlocksFixed.get(i - randomBlocks.size()).color]);
        }
    }

    public int[][] run() {
        Validator v = new Validator(blockCount + randomBlocks.size(), goalCount);

        long start = System.currentTimeMillis();

        int[][] result;
        int[] blockColors = new int[randomBlocksSize];

        for (Iterator<List<Integer>> it = generator.simple(randomBlocks.size()).stream().iterator(); it.hasNext(); )
            for (List<Integer> list : Generator.permutation(it.next()).simple())
                if((result = ignite(list, blockColors, v, start)) != null)
                    return result;

        printTime(start);
        return null;
    }

    private void printTime(long start) {
        long end = System.currentTimeMillis();

        long time = end - start;
        System.out.println("TIME : " + time + "(ms)");
    }

    private int[][] ignite(List<Integer> list, int[] blockColors, Validator v, long start) {
        for (int k = 0; k < maxBlockCase; k++) {
            setBlockColors(blockColors, k);

            setBase(list, blockColors);

            check(list, v);

            if (v.validate()) {
                printTime(start);
                return base;
            }
            v.clear();
            b = null;

            next(list);
        }

        return null;
    }

    private void setBlockColors(int[] blockColors, int k) {
        int temp    = maxBlockCase;
        int temp2   = k;

        for (int l = 0; l < randomBlocksSize; l++) {
            temp /= blockRange[l];
            blockColors[l] = temp2 / temp;
            temp2 %= temp;
        }
    }

    private void setBase(List<Integer> list, int[] blockColors) {
        for (int i = 0; i < randomBlocksSize; i++)
            if (i < randomBlocks.size())
                base[list.get(i) / 5]
                    [list.get(i) % 5] =
                        randomBlocks.get(i) * 10 + blockColors[i];
            else
                base[randomBlocksFixed.get(i - randomBlocks.size()).location / 5]
                    [randomBlocksFixed.get(i - randomBlocks.size()).location % 5] =
                        blockColors[i] + randomBlocksFixed.get(i - randomBlocks.size()).color * 10;
    }

    private void check(List<Integer> list, Validator v) {
        b = (startLocation != -1 ?
                new Block(startLocation, base, v) :
                new Block(list.get(0), base, v));

        b.find();
    }

    private void next(List<Integer> list) {
        for (int i = 0; i < randomBlocksSize; i++)
            if (i < randomBlocks.size())
                base[list.get(i) / 5]
                    [list.get(i) % 5] = 0;
            else
                base[randomBlocksFixed.get(i - randomBlocks.size()).location / 5]
                    [randomBlocksFixed.get(i - randomBlocks.size()).location % 5] = 0;
    }
}