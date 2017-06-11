package game.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

import static game.controller.boardController.getCellByColRow;
import static game.controller.mainController.CURSOR_IMAGE_LOCATION;
import static game.controller.tokenController.TOKEN_HEIGHT;
import static game.controller.tokenController.TOKEN_WIDTH;

/**
 * Created by hongbyeongsu on 2017. 6. 10..
 */
public class addToGridController implements Initializable {

    @FXML
    private GridPane addToGrid;
    private final BorderPane wrapper;
    private boolean isPressed = false;

    addToGridController(BorderPane wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 5; i++) {
            VBox cell = new VBox();
            cell.setStyle("" +
                    "-fx-max-width: 50px;" +
                    "-fx-min-width: 50px;" +
                    "-fx-max-height: 50px;" +
                    "-fx-min-height: 50px;" +
                    "-fx-border-color: #333;" +
                    "-fx-border-width: 1;");

            cell.setOnMousePressed(event -> {
                System.out.println(event.getSceneX() + "," + event.getSceneY());

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
                    double mx = event.getSceneX() - 450.0;
                    double my = event.getSceneY() - 20.0;

                    if((mx > 0 && my > 0) && (mx < TOKEN_WIDTH * 5 && my < TOKEN_HEIGHT * 2))
                        for(int x = 0; x < 5; x++)
                            if((mx > (x * TOKEN_WIDTH) && mx < (x + 1) * TOKEN_WIDTH)) {
                                ImageView cellImage = new ImageView(cursorImage.getImage());
                                cellImage.setFitWidth(TOKEN_WIDTH);
                                cellImage.setFitHeight(TOKEN_HEIGHT);
                                cellImage.setId(cursorImage.getId());

                                VBox tcell = getCellByColRow(addToGrid, 0, x);
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

            addToGrid.add(cell, i, 0);
        }
    }
}
