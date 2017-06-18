package game.controller.card;

import game.drawer.Drawer;
import game.solver.Checker;
import game.solver.Problem;
import game.test.Case;
import game.types.Grid;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hongbyeongsu on 2017. 6. 12..
 */
public class LaunchWrapper implements Initializable {
    @FXML
    private BorderPane launchWrapper;

    private final VBox card;

    // Top Menu
    private TextField goalInput;
    private GridPane addToGrid;

    // Center Board
    private Canvas canvas;
    private GridPane board;

    public LaunchWrapper(VBox card) {
        this.card = card;

        BorderPane menu = (BorderPane)card.getChildren().get(0);

        if(menu.getLeft() instanceof GridPane)
            this.addToGrid = (GridPane)menu.getLeft();

        if(menu.getRight() instanceof TextField)
            this.goalInput = (TextField)menu.getRight();

        StackPane stage = (StackPane)card.getChildren().get(1);

        if(stage.getChildren().get(0) instanceof Canvas)
            this.canvas = (Canvas)stage.getChildren().get(0);

        if(stage.getChildren().get(1) instanceof GridPane)
            this.board = (GridPane)stage.getChildren().get(1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Button start = new Button("Game start");
        Button reset = new Button("Game Reset");

        start.setPrefWidth(150);
        reset.setPrefWidth(150);

        start.setOnMouseClicked(event -> {
            Problem problem = new Problem(board, addToGrid, goalInput);

            if(!goalInput.getText().equals(" ")) {
                int[][] result = (new Checker(problem)).run();
                Grid.ChangeGrid(board, result);

                canvas.toFront();
                (new Drawer(canvas.getGraphicsContext2D(), result)).draw();
            } else
                System.out.println("No Targets!");
        });

        reset.setOnMouseClicked(event -> {
            board.getChildren().setAll();

            for(int i = 0; i < 5; i++ ){
                for(int j = 0; j < 5; j++){
                    VBox cell = new VBox();
                    cell.setPrefWidth(50);
                    cell.setPrefHeight(50);
                    cell.setStyle("-fx-border-color: #333;");
                    board.add(cell, i, j);
                }
            }

            addToGrid.getChildren().setAll();

            for(int i = 0; i < 5; i++) {
                VBox cell = new VBox();
                cell.setPrefWidth(50);
                cell.setPrefHeight(50);
                cell.setStyle("-fx-border-color: #333;");
                addToGrid.add(cell, i, 0);
            }

            canvas.toBack();

            GraphicsContext context = canvas.getGraphicsContext2D();
            context.clearRect(0, 0, 250, 250);

            goalInput.setText("");
        });

        launchWrapper.setLeft(start);
        launchWrapper.setRight(reset);
    }
}
