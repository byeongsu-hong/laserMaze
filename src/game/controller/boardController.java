package game.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

import static game.controller.mainController.CURSOR_IMAGE_LOCATION;
import static game.controller.tokenController.TOKEN_HEIGHT;
import static game.controller.tokenController.TOKEN_WIDTH;


/**
 * Created by hongbyeongsu on 2017. 6. 9..
 */
public class boardController implements Initializable {
    @FXML
    private GridPane board;
    private final BorderPane wrapper;
    private boolean isPressed = false;

    boardController(BorderPane wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                VBox cell = new VBox();
                cell.setStyle("" +
                        "-fx-max-width: 50px;" +
                        "-fx-min-width: 50px;" +
                        "-fx-max-height: 50px;" +
                        "-fx-min-height: 50px;" +
                        "-fx-border-color: #333;" +
                        "-fx-border-width: 1;");

                cell.setOnMousePressed(event -> {
                    if(cell.getChildren().size() == 1 && cell.getChildren().get(0) instanceof ImageView) {
                        ImageView cellImage = (ImageView) cell.getChildren().get(0);
                        ImageView cursorImage = (ImageView) wrapper.getChildren().get(CURSOR_IMAGE_LOCATION);
                        cursorImage.setImage(cellImage.getImage());
                        cursorImage.setFitWidth(TOKEN_WIDTH);
                        cursorImage.setFitHeight(TOKEN_HEIGHT);
                        cursorImage.setId(cellImage.getId());
                        cursorImage.toFront();

                        cursorImage.setX(event.getSceneX() - (TOKEN_WIDTH / 2));
                        cursorImage.setY(event.getSceneY() - (TOKEN_HEIGHT / 2));
                        cellImage.setImage(null);
                        isPressed = true;
                    }
                });

                cell.setOnMouseDragged(event -> {
                    if(isPressed) {
                        ImageView cursorImage = (ImageView) wrapper.getChildren().get(CURSOR_IMAGE_LOCATION);

                        cursorImage.setX(event.getSceneX() - (TOKEN_WIDTH / 2));
                        cursorImage.setY(event.getSceneY() - (TOKEN_HEIGHT / 2));
                    }
                });

                cell.setOnMouseReleased(event -> {
                    if(isPressed) {
                        ImageView cursorImage = (ImageView) wrapper.getChildren().get(CURSOR_IMAGE_LOCATION);

                        // 40, 90 start
                        double mx = event.getSceneX() - 40.0;
                        double my = event.getSceneY() - 90.0;

                        if((mx > 0 && my > 0) && (mx < TOKEN_WIDTH * 5 && my < TOKEN_HEIGHT * 5))
                            for(int x = 0; x < 5; x++)
                                for(int y = 0; y < 5; y++)
                                    if((mx > (x * TOKEN_WIDTH) && my > (y * TOKEN_HEIGHT))
                                            && (mx < (x + 1) * TOKEN_WIDTH && my < (y + 1) * TOKEN_HEIGHT)) {
                                        ImageView cellImage = new ImageView(cursorImage.getImage());
                                        cellImage.setFitWidth(TOKEN_WIDTH);
                                        cellImage.setFitHeight(TOKEN_HEIGHT);
                                        cellImage.setId(cursorImage.getId());

                                        VBox tcell = getCellByColRow(board, x, y);
                                        if (tcell != null)
                                            tcell.getChildren().setAll(cellImage);
                                    }

                        cursorImage.setFitWidth(0);
                        cursorImage.setFitHeight(0);
                        cursorImage.setImage(null);

                        cell.getChildren().setAll();
                        isPressed = false;
                    }
                });

                board.add(cell, j, i);
            }
        }
    }

    static VBox getCellByColRow(GridPane board, int col, int row) {
        for(Node node: board.getChildren()) {
            if(GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return (VBox)node;
            }
        }

        return null;
    }
}
