package game.solver;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Case c = new Case();
        Checker checker = new Checker(c.problem_48.getMap(), c.problem_48.getAddToGrid(), c.problem_48.getGoalCount());

        ArrayList<Integer> testing = new ArrayList<Integer>();
        testing.add(0);
        testing.add(10);
        testing.add(14);
        testing.add(20);
        testing.add(24);
        Block b = checker.run();

        int[][] arr = new int[5][5];

        b.toArray(arr);

        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                System.out.print(String.format("%2d ", arr[i][j]));
            }
            System.out.println();
        }
    }
}
