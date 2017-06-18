package game.solver;

public class Block {
    private int location;
    private int color;
    private int type;
    private int laserFrom = -1; //if laserFrom is -1, StartBlock(RED)
    private int[][] blockPosition;

    private boolean[] laserTable = new boolean[25];

    private Validator validator;

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

    public static class Mov {
        int x, y;

        Mov(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(int x, int y) {
            return this.x == x && this.y == y;
        }

        public boolean equals(Mov mov) {
            return this.x == mov.x && this.y == mov.y;
        }
    }

    public Block(int laserFrom, int location, int[][] blockPosition, Validator validator) {
        this(location, blockPosition, validator);
        this.laserFrom = laserFrom;
    }

    Block(int location, int[][] blockPosition, Validator validator) {
        this.blockPosition = blockPosition;
        this.location = location;
        this.convert(blockPosition[location/5][location%5]);
        this.validator = validator;
        
        for(int i=0; i<5; i++)
        	for(int j=0; j<5; j++)
        		if(blockPosition[i][j]/10 == 6) this.validator.fixedRed.add(i*5+j);
    }

    private void convert(int x) {
        this.color = x/10;
        this.type = x%10;
    }

    private void search(int x, int y, Mov mov) {
        Mov[] moveArray;
        boolean flag = true;
        laserTable[5*x+y] = true;

        while(true) {
            x += mov.x;
            y += mov.y;

            if(x < 0 || x >= 5 || y < 0 || y >= 5) return;

            if(blockPosition[x][y] != 0) {
                if(!laserTable[x * 5 + y]) validator.blockCount++;
                color = blockPosition[x][y] / 10; type =  blockPosition[x][y] % 10;

                for(int i=0; i<movement.length; i++) {
                    if(mov.x == movement[i].x && mov.y == movement[i].y && flag) {
                        flag = false;
                        laserFrom = i;
                        if(color == 3 && laserTable[5*x+y]) { return; }

                        switch(color) {
                            case 1 : //red
                                mov = shot[type];
                                if(validator.blockCount > 1) {
                                	validator.blockCount--;
                                	return;
                                }
                                break;
                            case 2 : //blue
                                if(type == 0) moveArray = new Mov[]{movement[1], movement[0], movement[3], movement[2]};
                                else moveArray = new Mov[]{movement[3], movement[2], movement[1], movement[0]};
                                mov = moveArray[laserFrom];
                                break;
                            case 3: //Green
                                switch(type) {
                                    case 0:
                                        if(laserFrom == 0) { search(x, y, movement[1]); mov = movement[0]; }
                                        else if(laserFrom == 1) { search(x, y, movement[1]); mov = movement[0]; }
                                        else if(laserFrom == 2) { search(x, y, movement[3]); mov = movement[2]; }
                                        else { search(x, y, movement[3]); mov = movement[2]; }
                                        break;
                                    case 1:
                                        if(laserFrom == 0) { mov = movement[0]; search(x, y, movement[3]); }
                                        else if(laserFrom == 1) { mov = movement[2]; search(x, y, movement[1]); }
                                        else if(laserFrom == 2) { mov = movement[2]; search(x, y, movement[1]); }
                                        else { mov = movement[0]; search(x, y, movement[3]); }
                                        break;
                                }
                                break;
                            case 5 : //Yellow
                                if(type==1 && (laserFrom == 0 || laserFrom == 2))
                                    mov = (laserFrom == 0 ? movement[0] : movement[2]);
                                else if(type == 0 && (laserFrom == 1 || laserFrom == 3))
                                    mov = (laserFrom == 1 ? movement[1] : movement[3]);
                                else if(!laserTable[5 * x + y]){ validator.blockCount--; return; }
                                break;
                            case 4:
                            case 6:
                                if(type == laserFrom) {
                                    validator.goalCount++;
                                    validator.currentRed.add(x*5+y);
                                    return;
                                }
                                else if(type==0 && (laserFrom == 2 || laserFrom == 3))
                                    mov = (laserFrom==2 ? movement[1]: movement[0]);
                                else if(type==1 && (laserFrom == 0 || laserFrom == 3))
                                    mov = (laserFrom==0 ? movement[1] : movement[2]);
                                else if(type==2 && (laserFrom == 0 || laserFrom == 1))
                                    mov = (laserFrom == 0 ? movement[3] : new Mov(-1 ,0));
                                else if(type==3 && (laserFrom == 1 || laserFrom == 2))
                                    mov = (laserFrom == 1 ? movement[0] : movement[3]);
                                else { validator.blockCount--; return; }
                                break;
                            case 7:
                                mov = movement[laserFrom];
                                if(!laserTable[5 * x + y]) validator.blockCount--;
                        }
                    }
                }
                flag = true;
                laserTable[5*x+y] = true;
            }
        }
    }
    void find() {
        search(location/5, location%5, shot[type]);
    }

    public static boolean isToken(int code) {
        int type = code / 10;
        return type >= 1 && type <= 7;
    }
}
