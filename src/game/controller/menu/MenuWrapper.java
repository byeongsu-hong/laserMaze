package game.controller.menu;

import game.app;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hongbyeongsu on 2017. 6. 12..
 */
public class MenuWrapper implements Initializable {
    @FXML
    private GridPane menuWrapper;

    private int[][] imageList = new int[][]{
            {15, 10, 11, 12, 13},
            {25, 20, 21, 22, 23},
            {35, 30, 31, 32, 33},
            {45, 40, 41},
            {55, 50, 51},
            {65, 60, 61},
            {70},
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int i, j;

        i = 0;
        for(int[] list: imageList) {
            j = 0;

            for(int node: list) {
                ImageView cell = new ImageView(new Image(String.valueOf(app.getResource(String.format("res/images/%d.png", node)))));
                cell.setFitWidth(50);
                cell.setFitHeight(50);

                menuWrapper.add(cell, j, i);
                j++;
            }

            i++;
        }

        menuWrapper.setHgap(10);
        menuWrapper.setVgap(20);
        menuWrapper.setPadding(new Insets(70));
    }
}
