package cz.cvut.fel.doudamar.localmultiplayergame.core;

public class Const {
    public enum NetWorkerType{SERVER, CLIENT}
    public enum WallType {VERTICAL, HORIZONTAL}

    public static final int FPS = 60;
    public static final int PORT = 9999;

    public static final double SCALE = 1.5d;

    public static final int SCREEN_SIZE_X = (int)(800*SCALE);
    public static final int SCREEN_SIZE_Y = (int)(450*SCALE);

    public static final double ROTATION_SPEED = .05d;
    public static final double MOVEMENT_SPEED = 2*SCALE;

    // PROJECTILE
    public static final double RELOAD_TIME = .2d;
    public static final double PROJECTILE_SPEED = 4*SCALE;
    public static final double PROJECTILE_TTL = 5d;
    public static final int PROJECTILE_RADIUS = (int)(6*SCALE);

    // TANK
    public static final int TANK_HIT_BOX_RADIUS = (int)(18*SCALE);
    public static final int TANK_WIDTH = 32;
    public static final int TANK_HEIGHT = 32;
    public static final double TANK_SCALE = 3*SCALE;

    // ARENA
    public static final int WALL_WIDTH = (int)(12*SCALE);
    public static final int TILE_SIZE = (int)(100*SCALE);


    /* UI */
    public static final int FONT_SIZE = (int)(16*SCALE);
    public static final int BUTTON_ARC_SIZE = (int)(20*SCALE);
    // GAME
    public static final int BAR_HEIGHT = (int)(50*SCALE);
    public static final int BAR_SPACING = (int)(8*SCALE);

    // MENU
    public static final int MENU_BUTTON_WIDTH = (int)(150*SCALE);
    public static final int MENU_BUTTON_HEIGHT = (int)(50*SCALE);
    public static final int MENU_BUTTON_SPACING = (int)(50*SCALE);
}
