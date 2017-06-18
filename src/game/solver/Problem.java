package game.solver;

import game.types.Grid;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.HashMap;

/**
 * Created by hongbyeongsu on 2017. 6. 17..
 */
public class Problem {

    private final GridPane map;
    private final GridPane random;
    private final TextField goalCount;

    public int[][] Map;
    public HashMap<Integer, Integer> AddToGrid;
    public int GoalCount;

    public Problem(GridPane map, GridPane random, TextField goalCount) {
        this.map = map;
        this.random = random;
        this.goalCount = goalCount;

        init();
    }

    private void init() {
        try {
            Map = Grid.ExportToArray(map);
            AddToGrid = Grid.ExportToHash(random);
            GoalCount = Integer.parseInt(goalCount.getText());
        } catch(NumberFormatException e) {
            goalCount.setText("");
        }
    }

}
