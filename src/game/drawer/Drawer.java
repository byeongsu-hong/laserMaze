package game.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by hongbyeongsu on 2017. 6. 12..
 */
public class Drawer {

    private final int[] TOKEN = new int[]{
        40, 41, 42, 43,
        50, 51,
        30, 31,
        20, 21,
    };

    private GraphicsContext context;
    private int[][] map;

    public Drawer(GraphicsContext context, int[][] map) {
        this.context = context;
        this.map = map;
    }

    public void drawLaser() {
        context.setStroke(Color.RED);

        // from x, y -> to x, y
        // x1, y1 -> x2, y2
        // 라인 그리기
        drawLine(0, 0, 0, 3);

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(map[i][j] / 10 == 1) {
                    startLaser(i, j, map[i][j] % 10);
                }
            }
        }
    }

    // 레이저 시작 지점 x, y, 방향을 인자로 받음
    private void startLaser(int x, int y, int dest) {

        // 현재 레이저 위치와 방향
        int currentLaserX = x;
        int currentLaserY = y;
        int destination = dest;

        // TODO: 목표에 도착할때까지 계속 나아가기, 둘로 나뉘는 부분에서는 옆으로 가는 레이저 한번더 start해주기 (재귀함수로)

        
    }

    // 순수 0 ~ 5까지 좌표만으로도 선찍을수있게 변환
    private void drawLine(int x1, int y1, int x2, int y2) {
        // 네모 한가운데부터 시작하기 위해서 위치 조정
        context.strokeLine((x1 * 50) + 25, (y1 * 50) + 25, (x2 * 50) + 25, (y2 * 50) + 25);
    }
}
