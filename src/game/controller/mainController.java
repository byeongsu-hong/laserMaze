package game.controller;

import game.app;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hongbyeongsu on 2017. 6. 10..
 */
public class mainController implements Initializable {

    static final int CURSOR_IMAGE_LOCATION = 3;

    @FXML
    private BorderPane wrapper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader itemMenuLoader = new FXMLLoader(app.getContext().getClass().getResource("view/itemMenu.fxml"));
        FXMLLoader boardLoader = new FXMLLoader(app.getContext().getClass().getResource("view/board.fxml"));
        FXMLLoader footerLoader = new FXMLLoader(app.getContext().getClass().getResource("view/footer.fxml"));

        try {
            boardLoader.setController(new boardController(wrapper));
            GridPane board = boardLoader.load();

            itemMenuLoader.setController(new itemMenuController(wrapper, board));

            wrapper.setRight(itemMenuLoader.load());
            wrapper.setCenter(board);
            wrapper.setBottom(footerLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageView cursorImage = new ImageView();
        wrapper.getChildren().add(CURSOR_IMAGE_LOCATION, cursorImage);
    }
}
