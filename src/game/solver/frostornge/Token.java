package game.solver.frostornge;

/**
 * Created by hongbyeongsu on 2017. 6. 13..
 */
public class Token {
    public static final int LASER_UP = 10;
    public static final int LASER_RT = 11;
    public static final int LASER_DN = 12;
    public static final int LASER_LF = 13;
    public static final int LASER_RD = 15;

    public static final int TARGET_UP = 20;
    public static final int TARGET_RT = 21;
    public static final int TARGET_DN = 22;
    public static final int TARGET_LF = 23;
    public static final int TARGET_RD = 25;

    public static final int MIRROR_UP = 30;
    public static final int MIRROR_RT = 31;
    public static final int MIRROR_DN = 32;
    public static final int MIRROR_LF = 33;
    public static final int MIRROR_RD = 35;

    public static final int SPLITER_LF = 40;
    public static final int SPLITER_RT = 41;
    public static final int SPLITER_RD = 45;

    public static final int DOUBLE_MIRROR_LF = 50;
    public static final int DOUBLE_MIRROR_RT = 51;
    public static final int DOUBLE_MIRROR_RD = 55;

    public static final int CHECKPOINT_LF = 60;
    public static final int CHECKPOINT_RT = 61;
    public static final int CHECKPOINT_RD = 65;

    public static final int BLOCKER = 70;

    private int     posX;
    private int     posY;
    private int     type;

    public Token(int x, int y, int type) {
        this.posX = x;
        this.posY = y;
        this.type = type;
    }

    
}