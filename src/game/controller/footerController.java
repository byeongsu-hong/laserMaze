package game.controller;

import game.drawer.Drawer;
import game.solver.Block;
import game.solver.Checker;
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

    // Canvas 객체
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

        // Canvas 에 그림을 그리게 할 수 있는 그래픽 컨텍스트
        GraphicsContext context = canvas.getGraphicsContext2D();

        start.setOnMouseClicked(event -> {
            // 처리 알고리즘에 쏴주세요

            // 맵 데이터 받아오는 부분, 로그
            int[][] mapBoard = makeBoardData();
            System.out.println(Arrays.deepToString(mapBoard));

            // Add to Grid 목록 불러오기, 로그
            HashMap<Integer, Integer> mapRandom = makeRandomList();
            System.out.println(mapRandom.toString());

            Block block = null;
            int goal = 0;

            try {
                // 목표 갯수 파싱
                goal = Integer.parseInt(goalNumber.getText());

                Checker checker = new Checker(mapBoard, mapRandom, goal);
                block = checker.run();

                // TODO: 맵 데이터랑 Add to Grid, 목표 갯수 체커에 넣어서 리턴타입이 뭐든간에 결과 뽑아내기.
            } catch (NumberFormatException e) {
                goalNumber.setText("숫자가 아니잖아요!");
            }

            if(block != null) {
                printTree(block);
            }

            // 그려 주세요
            // TODO: canvas에 받아온 맵 데이터를 바탕으로 레이저 그려주기

            // canvas 앞으로 빼고 레이저 그리기
            canvas.toFront();
            (new Drawer(context, mapBoard)).drawLaser(); // TODO: mapBoard -> 받아온 맵 데이터로 교체

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

            // canvas 뒤로 빼고 레이저 그리기
            canvas.toBack();
            context.clearRect(0, 0, 250, 250);
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
