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

import static game.controller.boardController.getCellByColRow;
import static game.controller.mainController.CURSOR_IMAGE_LOCATION;

/**
 * Created by hongbyeongsu on 2017. 6. 10..
 */
public class tokenController implements Initializable {
    static final double TOKEN_WIDTH = 50.0;
    static final double TOKEN_HEIGHT = 50.0;

    @FXML
    private GridPane token;

    private final BorderPane wrapper;
    private final GridPane board;

    tokenController(BorderPane wrapper, GridPane board) {
        this.wrapper = wrapper;
        this.board = board;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Node node : token.getChildren())
            process(node);
    }

    private void process(Node node) {
        if (node instanceof ImageView) {
            ImageView token = (ImageView) node;

            token.setOnMousePressed(event -> {
                ImageView cursorImage = (ImageView) wrapper.getChildren().get(CURSOR_IMAGE_LOCATION);
                cursorImage.setImage(token.getImage());
                cursorImage.setFitWidth(TOKEN_WIDTH);
                cursorImage.setFitHeight(TOKEN_HEIGHT);
                cursorImage.setId(token.getId());
                cursorImage.toFront();

                cursorImage.setX(event.getSceneX() - (TOKEN_WIDTH / 2));
                cursorImage.setY(event.getSceneY() - (TOKEN_HEIGHT / 2));
            });

            token.setOnMouseDragged(event -> {
                ImageView cursorImage = (ImageView) wrapper.getChildren().get(CURSOR_IMAGE_LOCATION);

                cursorImage.setX(event.getSceneX() - (TOKEN_WIDTH / 2));
                cursorImage.setY(event.getSceneY() - (TOKEN_HEIGHT / 2));
            });

            token.setOnMouseReleased(event -> {

                ImageView cursorImage = (ImageView) wrapper.getChildren().get(CURSOR_IMAGE_LOCATION);

                // 40, 90 start
                double x = event.getSceneX() - 40.0;
                double y = event.getSceneY() - 90.0;

                if((x > 0 && y > 0) && (x < TOKEN_WIDTH * 5 && y < TOKEN_HEIGHT * 5))
                    for(int i = 0; i < 5; i++)
                        for(int j = 0; j < 5; j++)
                            if((x > (i * TOKEN_WIDTH) && y > (j * TOKEN_HEIGHT))
                                && (x < (i + 1) * TOKEN_WIDTH && y < (j + 1) * TOKEN_HEIGHT)) {
                                ImageView cellImage = new ImageView(cursorImage.getImage());
                                cellImage.setFitWidth(TOKEN_WIDTH);
                                cellImage.setFitHeight(TOKEN_HEIGHT);
                                cellImage.setId(cursorImage.getId());

                                VBox cell = getCellByColRow(board, i, j);
                                if (cell != null)
                                    cell.getChildren().setAll(cellImage);
                            }

                cursorImage.setFitWidth(0);
                cursorImage.setFitHeight(0);
                cursorImage.setImage(null);
            });
        }
    }
}
