package game.controller.card;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.ResourceBundle;

import static game.controller.MainWrapper.TOKEN_HEIGHT;
import static game.controller.MainWrapper.TOKEN_WIDTH;

/**
 * Created by hongbyeongsu on 2017. 6. 12..
 */
public class MenuWrapper implements Initializable {
    @FXML
    private BorderPane cardMenuWrapper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cardMenuWrapper.setTop(getTop());
        cardMenuWrapper.setRight(getRight());
        cardMenuWrapper.setLeft(getLeft());
    }

    private BorderPane getTop() {
        BorderPane logoWrapper = new BorderPane();

        Text logo = new Text("LASER MAZE");
        logo.setStyle("" +
                "-fx-font-size: 45;" +
                "-fx-font-weight: bolder;" +
                "-fx-font-style: italic");

        logoWrapper.setTop(logo);
        BorderPane.setMargin(logo, new Insets(20, 0, 20, 0));
        BorderPane.setAlignment(logo, Pos.CENTER);

        BorderPane textWrapper = new BorderPane();

        Text grid = new Text("ADD TO GRID");
        Text target = new Text("# OF TARGETS");

        textWrapper.setLeft(grid);
        textWrapper.setRight(target);

        BorderPane.setAlignment(grid, Pos.CENTER);
        BorderPane.setAlignment(target, Pos.CENTER);

        logoWrapper.setCenter(textWrapper);

        return logoWrapper;
    }

    private TextField getRight() {
        TextField goalInput = new TextField();
        goalInput.setFocusTraversable(false);
        goalInput.setPromptText("#");
        goalInput.setStyle("" +
                "-fx-font-size: 25;" +
                "-fx-font-weight: 500;");
        goalInput.setPrefWidth(50);
        goalInput.setPrefHeight(50);
        BorderPane.setMargin(goalInput, new Insets(0, 0, 0, 50));

        return goalInput;
    }

    private GridPane getLeft() {
        GridPane addToGrid = new GridPane();
        addToGrid.setStyle("-fx-background-color: aliceblue");

        for(int i = 0; i < 5; i++) {
            VBox cell = new VBox();
            cell.setPrefWidth(TOKEN_WIDTH + 2);
            cell.setPrefHeight(TOKEN_HEIGHT + 2);
            cell.setStyle("-fx-border-color: #333;");
            addToGrid.add(cell, i, 0);
        }

        return addToGrid;
    }

}
