package game.test;

import java.util.HashMap;

/**
 * Created by hongbyeongsu on 2017. 6. 11..
 */
public class Case {
    public static TestCase problem_60 = new TestCase(new int[][]{
            {0, 0, 0, 61, 0},
            {42, 0, 0, 0, 0},
            {0, 70, 55, 0, 0},
            {0, 0, 0, 0, 60},
            {0, 25, 0, 0, 0},
        }, new HashMap<Integer, Integer>(){{
            put(1, 1);
            put(2, 0);
            put(3, 2);
            put(4, 2);
            put(5, 0);
            put(6, 0);
        }}, 3);

    public static TestCase problem_48 = new TestCase(new int[][]{
            {0, 0, 0, 0, 12},
            {70, 62, 0, 0, 0},
            {0, 20, 0, 0, 0},
            {0, 60, 0, 0, 0},
            {0, 0, 0, 0, 0},
        }, new HashMap<Integer, Integer>(){{
            put(1, 0);
            put(2, 0);
            put(3, 2);
            put(4, 3);
            put(5, 0);
            put(6, 0);
        }}, 3);

    public static class TestCase {
        private final int[][] map;
        private final HashMap<Integer, Integer> addToGrid;
        private final int goalCount;

        TestCase(int[][] map, HashMap<Integer, Integer> addToGrid, int goalCount) {
            this.map = map;
            this.addToGrid = addToGrid;
            this.goalCount = goalCount;
        }

        public int[][] getMap() {
            return map;
        }

        public HashMap<Integer, Integer> getAddToGrid() {
            return addToGrid;
        }

        public int getGoalCount() {
            return goalCount;
        }
    }
}
