package game.controller;

import game.app;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hongbyeongsu on 2017. 6. 9..
 */
public class itemMenuController implements Initializable {

    @FXML
    private TabPane itemMenu;
    private final BorderPane wrapper;
    private final GridPane board;

    itemMenuController(BorderPane wrapper, GridPane board) {
        this.wrapper = wrapper;
        this.board = board;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader tokenViewLoader = new FXMLLoader(app.getContext().getClass().getResource("view/token.fxml"));
        FXMLLoader utokenViewLoader = new FXMLLoader(app.getContext().getClass().getResource("view/unknown.fxml"));

        tokenViewLoader.setController(new tokenController(wrapper, board));
        utokenViewLoader.setController(new tokenController(wrapper, board));

        GridPane tokenView;
        GridPane utokenView;

        try {
            tokenView = tokenViewLoader.load();
            utokenView = utokenViewLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Tab token = new Tab();
        token.setText("토큰");
        token.setContent(tokenView);
        itemMenu.getTabs().add(token);

        Tab utoken = new Tab();
        utoken.setText("물음표");
        utoken.setContent(utokenView);
        itemMenu.getTabs().add(utoken);
    }
}
