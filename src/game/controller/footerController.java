package game.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by hongbyeongsu on 2017. 6. 10..
 */
public class footerController implements Initializable {

    static final int CANVAS_LOCATION = 2;

    @FXML
    private HBox footer;
    private final Canvas canvas;
    private final GridPane board;
    private final GridPane addToGrid;
    private final TextArea goalNumber;

    footerController(BorderPane wrapper) {
        StackPane stage = (StackPane) wrapper.getCenter();
        this.canvas = (Canvas)(stage.getChildren().get(0));
        this.board = (GridPane)(stage.getChildren().get(1));
        VBox tools = (VBox) wrapper.getRight();
        this.addToGrid = (GridPane) tools.getChildren().get(0);
        this.goalNumber = (TextArea) tools.getChildren().get(2);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        footer.setPadding(new Insets(0, 0, 50, 40));
        footer.setSpacing(10);

        Button start = new Button("게임 시작");
        start.setPrefWidth(100);

        Button reset = new Button("게임 리셋");
        reset.setPrefWidth(100);

        GraphicsContext context = canvas.getGraphicsContext2D();

        start.setOnMouseClicked(event -> {
            // 처리 알고리즘에 쏴주세요
            int[][] mapBoard = makeBoardData();
            System.out.println(Arrays.deepToString(mapBoard));

            HashMap<Integer, Integer> mapRandom = makeRandomList();
            System.out.println(mapRandom.toString());

            Block tree = null;

            int goal = 0;

            try {
                goal = Integer.parseInt(goalNumber.getText());

//                Checker checker = new Checker(mapBoard, mapRandom, goal);
//                tree = checker.run();
            } catch (NumberFormatException e) {
                goalNumber.setText("숫자가 아니잖아요!");
            }

            if(tree != null)
                printTree(tree);

            // 그려 주세요

        });
        reset.setOnMouseClicked(event -> {
            // 리셋시켜 주세요
            for(Node node: board.getChildren()) {
                VBox cell = (VBox)node;

                cell.getChildren().setAll();
            }

            for(Node node: addToGrid.getChildren()) {
                VBox cell = (VBox)node;

                cell.getChildren().setAll();
            }

            context.setFill(Color.WHITE);
            context.fillRect(0, 0, 250, 250);
        });

        footer.getChildren().addAll(start, reset);
    }

    private int[][] makeBoardData() {
        int[][] map = new int[5][5];

        for(Node node: board.getChildren()) {
            VBox cell = (VBox)node;

            int x = GridPane.getColumnIndex(cell);
            int y = GridPane.getRowIndex(cell);

            if(cell.getChildren().size() == 1 && cell.getChildren().get(0) instanceof ImageView) {
                ImageView cellImage = (ImageView)cell.getChildren().get(0);
                map[y][x] = Integer.parseInt(cellImage.getId());
            } else {
                map[y][x] = 0;
            }
        }

        return map;
    }

    private HashMap<Integer, Integer> makeRandomList() {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(Node node: addToGrid.getChildren()) {
            VBox cell = (VBox)node;

            if(cell.getChildren().size() == 1 && cell.getChildren().get(0) instanceof ImageView) {
                ImageView cellImage = (ImageView)cell.getChildren().get(0);

                int key = Integer.parseInt(cellImage.getId());

                if(map.containsKey(key)) {
                    int value = map.get(key);
                    map.replace(key, value, ++value);
                } else {
                    map.put(key, 1);
                }
            }
        }

        return map;
    }

    private void printTree(Block treeNode) {
        System.out.println("[" + treeNode.getLocation() + "," + treeNode.getType() + "]");
        for(Block node: treeNode.conn) {
            if(node != null) {
                printTree(node);
            }
        }
    }
}
