package game.controller.menu;

import game.app;
import game.types.Grid;
import game.solver.Block;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static game.app.IMAGE_LOCATION;
import static game.controller.MainWrapper.TOKEN_HEIGHT;
import static game.controller.MainWrapper.TOKEN_WIDTH;

/**
 * Created by hongbyeongsu on 2017. 6. 12..
 */
public class MenuWrapper implements Initializable {
    @FXML
    private GridPane menuWrapper;

    private int[][] imageList = new int[][]{
            {15, 10, 11, 12, 13},
            {25, 20, 21},
            {35, 30, 31},
            {45, 40, 41, 42, 43},
            {55, 50, 51},
            {65, 60, 61, 62, 63},
            {70, 0},
    };

    private final BorderPane wrapper;

    private final GridPane board;
    private final GridPane addToGrid;

    private final ImageView cursorImage;

    public MenuWrapper(BorderPane wrapper) {
        this.wrapper = wrapper;

        VBox card = (VBox) wrapper.getLeft();
        this.board = (GridPane)((StackPane) card.getChildren().get(1)).getChildren().get(1);
        this.addToGrid = (GridPane)((BorderPane) card.getChildren().get(0)).getLeft();

        this.cursorImage = (ImageView) wrapper.getChildren().get(1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int i, j;

        for(i = 0; i < imageList.length; i++)
            for (j = 0; j < imageList[i].length; j++)
                menuWrapper.add(makeCell(imageList[i][j]), j, i);

        menuWrapper.setHgap(10);
        menuWrapper.setVgap(20);
        menuWrapper.setPadding(new Insets(70));
    }

    private ImageView makeCell(int code) {
        ImageView cell = new ImageView(
                new Image(String.valueOf(
                    app.getResource(String.format("res/%s/%d.png", IMAGE_LOCATION, code)))));

        cell.setFitWidth(50);
        cell.setFitHeight(50);
        cell.setId(String.valueOf(code));

        cell.setOnMousePressed(event -> {
            // 마우스를 따라다닐 이미지 설정
            cursorImage.setImage(cell.getImage());

            // 가로 세로 높이 맞추기
            cursorImage.setFitWidth(TOKEN_WIDTH);
            cursorImage.setFitHeight(TOKEN_HEIGHT);

            // ID값도 복사하자
            cursorImage.setId(cell.getId());
            cursorImage.toFront();

            // 마우스를 따라다니게
            cursorImage.setX(event.getSceneX() - (TOKEN_WIDTH / 2));
            cursorImage.setY(event.getSceneY() - (TOKEN_HEIGHT / 2));
        });

        cell.setOnMouseDragged(event -> {
            // 마우스를 따라다니게
            cursorImage.setX(event.getSceneX() - (TOKEN_WIDTH / 2));
            cursorImage.setY(event.getSceneY() - (TOKEN_HEIGHT / 2));
        });

        cell.setOnMouseReleased(event -> {

            // 마우스가 그리드 위에 있는지 판별하고,
            // 현재 커서를 따라다니는 이미지의 정보를 그리드의 한 셀에 넣어준다.
            checkIsMouseOnGrid(event, board, 5, 5, false);
            checkIsMouseOnGrid(event, addToGrid, 5, 1, true);

            cursorImage.setFitWidth(0);
            cursorImage.setFitHeight(0);
            cursorImage.setImage(null);
        });

        return cell;
    }

    // 마우스가 그리드 위에 있는지 파악하는 함수
    private void checkIsMouseOnGrid(
            MouseEvent event, GridPane grid, int col, int row, boolean isAddToGrid) {
        Bounds gridBounds = grid.localToScene(grid.getBoundsInLocal());

        double x = event.getSceneX() - gridBounds.getMinX();
        double y = event.getSceneY() - gridBounds.getMinY();

        if((x > 0 && y > 0) && (x < TOKEN_WIDTH * 5 && y < TOKEN_HEIGHT * 5))
            for(int i = 0; i < col; i++)
                for(int j = 0; j < row; j++)
                    if((x > (i * TOKEN_WIDTH) && y > (j * TOKEN_HEIGHT))
                        && (x < (i + 1) * TOKEN_WIDTH && y < (j + 1) * TOKEN_HEIGHT))
                        placeImageViewOnGrid(grid, cursorImage, i, j, isAddToGrid);
    }

    // 그리드의 한 셀에 커서 이미지의 정보를 넣어주는 함수.
    // 실제 넣어주는 동작은 placeImageViewOnGridUtil에서 하고,
    // 이 함수에서는 마우가 떼진 위치가 AddToGrid에 있는지와 Board위에 있는지를 구분한다.
    private void placeImageViewOnGrid(
            GridPane grid, ImageView cursorImage, int i, int j, boolean type) {
        int code = Integer.parseInt(cursorImage.getId());

        if(type)
            if(code % 10 == 5 || code == 0)
                placeImageViewOnGridUtil(grid, cursorImage, i, j);
            else
                System.out.print(""); // Do Nothing. Just for fun!
        else
            placeImageViewOnGridUtil(grid, cursorImage, i, j);
    }

    private void placeImageViewOnGridUtil(GridPane grid, ImageView cursorImage, int i, int j) {
        ImageView cellImage = new ImageView(cursorImage.getImage());
        cellImage.setFitWidth(TOKEN_WIDTH);
        cellImage.setFitHeight(TOKEN_HEIGHT);
        cellImage.setId(cursorImage.getId());

        VBox cell = Grid.GetCellByColRow(grid, i, j);
        if (cell != null)
            if(Objects.equals(cursorImage.getId(), "0"))
                cell.getChildren().setAll();
            else
                cell.getChildren().setAll(cellImage);
    }
}
