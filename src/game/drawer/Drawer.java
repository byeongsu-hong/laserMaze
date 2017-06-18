package game.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by hongbyeongsu on 2017. 6. 12..
 */
public class Drawer {

    private GraphicsContext context;
    private int[][] map;

    public Drawer(GraphicsContext context, int[][] map) {
        this.context = context;
        this.map = map;
    }

    public void draw() {
        context.setStroke(Color.RED);
        context.setLineWidth(5);

        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if(map[i][j] / 10 == 1)
                    startLaser(i, j);
    }

    // 레이저 시작 지점 x, y, 방향을 인자로 받음
    private void startLaser(int x, int y) {
        Tree b = new Tree(5*x+y, map, this);
        b.find();
        b.drawLine();
    }

    // 순수 0 ~ 5까지 좌표만으로도 선찍을수있게 변환
    void drawLine(int x1, int y1, int x2, int y2) {
        // 네모 한가운데부터 시작하기 위해서 위치 조정
        context.strokeLine((x1 * 50) + 25, (y1 * 50) + 25, (x2 * 50) + 25, (y2 * 50) + 25);
    }
}
