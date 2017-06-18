package game.drawer;

public class Tree {

    private int location;
    private int color;
    private int type;
    private int laserInput = -1;
    private int[][] blockPosition;

    private Tree[] c;
    private Mov[] movement = new Mov[]{
        new Mov(1, 0),
        new Mov(0, -1),
        new Mov(-1, 0),
        new Mov(0, 1),
    };

    private Mov[] shot = new Mov[]{
        new Mov(-1, 0),
        new Mov(0, 1),
        new Mov(1, 0),
        new Mov(0, -1),
    };

    private int[] array = new int[]{2, 3, 0, 1};
    private Drawer drawer;

    public static class Mov {
        int x, y;

        Mov(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private Tree(int laserInput, int location, int[][] blockPosition, Drawer drawer) {
        this(location, blockPosition, drawer);
        this.laserInput = laserInput;
    }

    Tree(int location, int[][] blockPosition, Drawer drawer) {
        this.c = new Tree[4];
        this.blockPosition = blockPosition;
        this.location = location;
        this.convert(blockPosition[location/5][location%5]);
        this.drawer = drawer;
    }

    private void convert(int x) {
        this.color = x/10;
        this.type = x%10;
    }

    private void search(int x, int y, Mov mov) {
        while(true) {
            x += mov.x;
            y += mov.y;
            if(x < 0 || x >= 5 || y < 0 || y >= 5) {
                drawer.drawLine(location%5, location/5, y-mov.y, x-mov.x);
                return;
            }

            if(blockPosition[x][y] != 0)
                for(int i=0; i<movement.length; i++)
                    if(mov.x == movement[i].x && mov.y == movement[i].y) {
                        c[array[i]] = new Tree(i,x*5+y, blockPosition, drawer);
                        c[array[i]].find();
                        drawer.drawLine(location%5, location/5, c[array[i]].location%5, c[array[i]].location/5);

                        return;
                    }
        }
    }

    void find() {
        Mov[] moveArray;
        int x = location/5, y = location%5;

        switch(color) {
            case 1 :
                search(x, y, shot[type]);
                break;
            case 2 :
                if(type == 0) moveArray = new Mov[]{movement[1], movement[0], movement[3], movement[2]};
                else moveArray = new Mov[]{movement[3], movement[2], movement[1], movement[0]};
                search(x, y, moveArray[laserInput]);
                break;
            case 3:
                switch(type) {
                    case 0:
                        if(laserInput == 0) { search(x, y, movement[0]); search(x, y, movement[1]); }
                        else if(laserInput == 1) { search(x, y, movement[0]); search(x, y, movement[1]); }
                        else if(laserInput == 2) { search(x, y, movement[2]); search(x, y, movement[3]); }
                        else { search(x, y, movement[2]); search(x, y, movement[3]); }
                        break;
                    case 1:
                        if(laserInput == 0) { search(x, y, movement[0]); search(x, y, movement[3]); }
                        else if(laserInput == 1) { search(x, y, movement[2]); search(x, y, movement[1]); }
                        else if(laserInput == 2) { search(x, y, movement[2]); search(x, y, movement[1]); }
                        else { search(x, y, movement[0]); search(x, y, movement[3]); }
                        break;
                }
                break;
            case 5 : //Yellow
                if(type==1 && (laserInput == 0 || laserInput == 2))  //ㅡ
                    search(x, y, (laserInput == 0 ? movement[0] : movement[2]));
                else if(type == 0 && (laserInput == 1 || laserInput == 3)) //|
                    search(x, y, (laserInput == 1 ? movement[1] : movement[3]));
                break;
            case 4:
            case 6:
                if(type==0 && (laserInput == 2 || laserInput == 3))
                    search(x, y, (laserInput==2 ? movement[1]: movement[0]));
                else if(type==1 && (laserInput == 0 || laserInput == 3))
                    search(x, y, (laserInput==0 ? movement[1] : movement[2]));
                else if(type==2 && (laserInput == 0 || laserInput == 1))
                    search(x, y, (laserInput == 0 ? movement[3] : new Mov(-1 ,0)));
                else if(type==3 && (laserInput == 1 || laserInput == 2))
                    search(x, y, (laserInput == 1 ? movement[0] : movement[3]));
                break;
            default :
                if(laserInput == 0) search(x, y, movement[0]);
                else if(laserInput == 1) search(x, y, movement[1]);
                else if(laserInput == 2) search(x, y, movement[2]);
                else search(x, y, movement[3]);
        }
    }

    void drawLine() {
        for(int i=0; i<4; i++)
            if(c[i] != null) {
                drawer.drawLine(location%5, location/5, c[i].location%5, c[i].location/5);
                c[i].drawLine();
            }
    }
}
