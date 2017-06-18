package game.types;

import game.app;
import game.solver.Block;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.HashMap;

import static game.app.IMAGE_LOCATION;
import static game.controller.MainWrapper.TOKEN_HEIGHT;
import static game.controller.MainWrapper.TOKEN_WIDTH;

/**
 * Created by hongbyeongsu on 2017. 6. 17..
 */
public class Grid {
    public static VBox GetCellByColRow(GridPane board, int col, int row) {
        for(Node node: board.getChildren())
            if(GridPane.getColumnIndex(node) == col &&
                    GridPane.getRowIndex(node) == row)
                return (VBox)node;

        return null;
    }

    public static int[][] ExportToArray(GridPane grid) {
        int[][] map = new int[5][5];

        for(Node node: grid.getChildren()) {
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

    public static HashMap<Integer, Integer> ExportToHash(GridPane grid) {
        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(1, 0);
        map.put(2, 0);
        map.put(3, 0);
        map.put(4, 0);
        map.put(5, 0);
        map.put(6, 0);

        for(Node node: grid.getChildren()) {
            VBox cell = (VBox)node;

            if(cell.getChildren().size() == 1 && cell.getChildren().get(0) instanceof ImageView) {
                ImageView cellImage = (ImageView)cell.getChildren().get(0);

                int key = Integer.parseInt(cellImage.getId());

                if(map.containsKey(key / 10)) {
                    int value = map.get(key / 10);
                    map.replace(key / 10, value, ++value);
                } else {
                    map.put(key / 10, 1);
                }
            }
        }

        return map;
    }

    public static void ChangeGrid(GridPane grid, int[][] result) {
        // 그리드 초기화
        grid.getChildren().setAll();

        int x, y;

        for(y = 0; y < 5; y++) {
            for(x = 0; x < 5; x++) {
                VBox cell = new VBox();
                cell.setPrefWidth(50);
                cell.setPrefHeight(50);
                cell.setStyle("-fx-border-color: #333;");

                if(Block.isToken(result[y][x])) {
                    ImageView cellImage = new ImageView(
                            new Image(String.valueOf(
                                    app.getResource(String.format("res/%s/%d.png", IMAGE_LOCATION, result[y][x])))));

                    cellImage.setFitWidth(TOKEN_WIDTH);
                    cellImage.setFitHeight(TOKEN_HEIGHT);
                    cellImage.setId(String.valueOf(result[y][x]));

                    cell.getChildren().setAll(cellImage);
                }

                grid.add(cell, x, y);
            }
        }

    }
}
